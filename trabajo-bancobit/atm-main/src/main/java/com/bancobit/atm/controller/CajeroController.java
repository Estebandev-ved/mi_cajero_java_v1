package com.bancobit.atm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bancobit.atm.dto.TransferenciaForm;
import com.bancobit.atm.entity.Cliente;
import com.bancobit.atm.entity.Cuenta;
import com.bancobit.atm.repository.CuentaRepository;
import com.bancobit.atm.services.ClienteService;
import com.bancobit.atm.services.CuentaService;
import com.bancobit.atm.services.MovimientoService;
import com.bancobit.atm.services.RetiroService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cajero")
public class CajeroController {
    private final ClienteService clienteService;
    private final CuentaService cuentaService;
    private final CuentaRepository cuentaRepository;
    private final MovimientoService movimientoService;
    private final RetiroService retiroService;

    @GetMapping
    public String loginForm() {
        return "cajero/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String numeroCuenta,
                        @RequestParam String pin,
                        HttpSession session,
                        Model model) {
        var cuenta = cuentaService.buscarPorNumero(numeroCuenta);
        if (cuenta.isEmpty()) {
            model.addAttribute("error", "Cuenta no encontrada.");
            return "cajero/login";
        }

        Cliente cliente = cuenta.get().getCliente();

        if (cliente == null) {
            model.addAttribute("error", "La cuenta no tiene cliente asociado.");
            return "cajero/login";
        }

        if (cliente.isBloqueado()) {
            model.addAttribute("error", "Cuenta bloqueada.");
            return "cajero/login";
        }

        if (!cliente.getPin().equals(pin)) {
            clienteService.incrementarIntento(cliente);
            if (cliente.getIntentos() >= 3) {
                clienteService.bloquearCliente(cliente);
                model.addAttribute("error", "Cuenta bloqueada por intentos fallidos.");
            } else {
                model.addAttribute("error", "PIN incorrecto.");
            }
            return "cajero/login";
        }

        clienteService.reiniciarIntentos(cliente);
        session.setAttribute("cliente", cliente);
        return "redirect:/cajero/menu";
    }

    @GetMapping("/menu")
    public String menu(HttpSession session, Model model) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) return "redirect:/cajero";

        model.addAttribute("cliente", cliente);
        model.addAttribute("cuentas", cuentaService.buscarPorCliente(cliente));
        return "cajero/menu";
    }

    @GetMapping("/consultas")
    public String consultas(Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        model.addAttribute("cuentas", cuentaService.buscarPorCliente(cliente));
        return "cajero/consultas";
    }

    @GetMapping("/movimientos/{numero}")
    public String movimientos(@PathVariable String numero, Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) return "redirect:/cajero";

        try {
            var movimientos = movimientoService.buscarPorCuenta(numero);
            model.addAttribute("movimientos", movimientos);
            return "cajero/movimientos";
        } catch (Exception e) {
            model.addAttribute("error","No fue posible obtener los movimientos: " + e.getMessage());
            return "cajero/consultas";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/cajero";
    }

    @GetMapping("/retiro")
    public String mostrarFormularioRetiro(Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            return "redirect:/cajero";
        }

        List<Cuenta> cuentas = cuentaService.buscarPorCliente(cliente);
        if (cuentas.isEmpty()) {
            model.addAttribute("error", "No tienes cuentas disponibles para realizar retiros");
            return "redirect:/cajero/menu";
        }

        model.addAttribute("cuentas", cuentas);
        model.addAttribute("identificacion", cliente.getIdentificacion());
        return "cajero/retiro";
    }

    @PostMapping("/retiro")
    public String realizarRetiro(@RequestParam String identificacion,
                                 @RequestParam String numeroCuenta,
                                 @RequestParam double monto,
                                 RedirectAttributes redirectAttributes){
        try {
            retiroService.realizarRetiro(identificacion, numeroCuenta, monto);
            redirectAttributes.addFlashAttribute("mensaje", "Retiro exitoso");
            return "redirect:/cajero/menu";
        } catch(RuntimeException e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/cajero/retiro";
        }
    }


    @GetMapping("/consignar")
    public String mostrarFormularioConsignacion(HttpSession session, Model model) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            return "redirect:/cajero";
        }
        return "cajero/consignar";
    }

    @PostMapping("/consignar")
    public String consignar(@RequestParam String numeroCuenta,
                            @RequestParam double monto,
                            Model model) {
        try {
            Cuenta cuenta = cuentaRepository.findByNumero(numeroCuenta)
                    .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

            movimientoService.realizarConsignacion(cuenta, monto);
            model.addAttribute("mensaje", "Consignación exitosa. Nuevo saldo: " + cuenta.getSaldo());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "cajero/consignar";
    }

    @GetMapping("/transferir")
    public String mostrarFormularioTransferencia(Model model) {
        model.addAttribute("transferenciaForm", new TransferenciaForm());
        return "cajero/transferir";
    }

    @PostMapping("/transferir")
    public String transferir(@RequestParam String numeroCuentaDestino,
                             @RequestParam double monto,
                             HttpSession session,
                             Model model) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) return "redirect:/cajero";

        Cuenta origen = cuentaService.buscarPorCliente(cliente)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró cuenta origen"));

        try {
            Cuenta destino = cuentaService.buscarPorNumero(numeroCuentaDestino)
                    .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

            if (movimientoService.realizarTransferencia(origen, destino, monto)) {
                model.addAttribute("mensaje", "Transferencia realizada con éxito");
            } else {
                model.addAttribute("error", "Saldo insuficiente para realizar la transferencia");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }

        return "cajero/transferir";
    }

    @GetMapping("/titular")
    @ResponseBody
    public Map<String, String> obtenerTitular(@RequestParam String numero){
        return cuentaService.buscarPorNumero(numero)
                .map(cuenta -> Map.of("nombre", cuenta.getCliente().getNombreCompleto()))
                .orElse(Map.of());
    }

    @GetMapping("/cambiar-clave")
    public String mostrarFormularioCambioClave() {
        return "cajero/cambiar-clave";
    }

    @PostMapping("/cambiar-clave")
    public String cambiarClave(@RequestParam String claveActual,
                               @RequestParam String nuevaClave,
                               @RequestParam String confirmarClave,
                               HttpSession session,
                               Model model){
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null){
            return "redirect:/cajero";
        }

        if (!cliente.getPin().equals(claveActual)) {
            model.addAttribute("error", "Clave actual incorrecta.");
            return "cajero/cambiar-clave";
        }

        if (!nuevaClave.equals(confirmarClave)) {
            model.addAttribute("error", "Las nuevas claves no coinciden.");
            return "cajero/cambiar-clave";
        }

        clienteService.cambiarPin(cliente, nuevaClave);
        session.setAttribute("cliente", cliente);
        model.addAttribute("mensaje", "Clave cambiada exitosamente.");
        return "cajero/cambiar-clave";
    }
}
