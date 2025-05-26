package com.bancobit.atm.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bancobit.atm.entity.Cliente;
import com.bancobit.atm.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente){
        cliente.setBloqueado(false);
        cliente.setIntentosFallidos(0);
        return clienteRepository.save(cliente);
    }

    public Optional <Cliente> buscarPorIdentificacion(String identificacion){
        return clienteRepository.findByIdentificacion(null);
    }

    public boolean validarPin(Cliente cliente, String pin){
        if (cliente.isBloqueado())return false;
        
        if (cliente.getPin().equals(pin)) {
            cliente.setIntentosFallidos(0);
            clienteRepository.save(cliente);
            return true;
            
        } else {
            int intentos = cliente.getIntentosFallidos() + 1;
            cliente.setIntentosFallidos(intentos);
            if (intentos >3) {
                cliente.setBloqueado(true);
            }
            clienteRepository.save(cliente);
            return false;
            
        }

            
        }
            
        public void desbloquearCliente(String identificacion, String nuevoPin){
            Optional<Cliente> optionalCliente = clienteRepository.findByIdentificacion(identificacion);
            if (optionalCliente.isPresent()){
                Cliente cliente = optionalCliente.get();
                cliente.setBloqueado(false);
                cliente.setIntentosFallidos(0);
                cliente.setPin(nuevoPin);
                clienteRepository.save(cliente);
            }
        }
        public void cambiarPin(Cliente cliente, String nuevoPin){
            cliente.setPin(nuevoPin);
            clienteRepository.save(cliente);
        }

        public void incrementarIntento(Cliente cliente){
            cliente.setIntentos(cliente.getIntentos() + 1);
            clienteRepository.save(cliente);
        }

        public void reiniciarIntentos(Cliente cliente){
            cliente.setIntentos(0);
            clienteRepository.save(cliente);
        }

        public void bloquearCliente(Cliente cliente){
            cliente.setBloqueado(true);
            clienteRepository.save(cliente);

        
        
    }
}


