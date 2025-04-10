import java.util.Scanner;
import java.util.List;

public class GestorUsuarios {

    public Usuario registrarUsuario(Scanner scanner) {
        System.out.println("\n--- Registro de Usuario ---");
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Cree un número de cuenta (ej: 001): ");
        String numeroCuenta = scanner.nextLine();

        System.out.print("Ingrese su PIN: ");
        String pin = scanner.nextLine();

        System.out.print("Confirme su PIN: ");
        String confirmarPin = scanner.nextLine();

        if (!pin.equals(confirmarPin)) {
            System.out.println("❌ Los PINs no coinciden. Registro cancelado.");
            return null;
        }

        System.out.println("Tipo de cuenta:");
        System.out.println("1. Ahorros");
        System.out.println("2. Corriente");
        System.out.println("3. Nómina");
        System.out.print("Elige una opción: ");
        int tipoOpcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        TipoDeCuenta tipo = switch (tipoOpcion) {
            case 2 -> TipoDeCuenta.CORRIENTE;
            case 3 -> TipoDeCuenta.NOMINA;
            default -> TipoDeCuenta.AHORROS;
        };

        Cuenta nuevaCuenta = new Cuenta(numeroCuenta, 0.0, pin, 0, tipo);
        Usuario nuevoUsuario = new Usuario(nombre, nuevaCuenta);

        System.out.println("✅ Registro exitoso. Ahora puede iniciar sesión.");
        return nuevoUsuario;
    }

    public Usuario iniciarSesion(Scanner scanner, List<Usuario> usuarios) {
        System.out.println("\n--- Iniciar Sesión ---");
        System.out.print("Ingrese número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        System.out.print("Ingrese su PIN: ");
        String pin = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getCuenta().getNumeroCuenta().equals(numeroCuenta)
                    && usuario.getCuenta().getPin().equals(pin)) {
                System.out.println("✅ Inicio de sesión exitoso. ¡Bienvenido, " + usuario.getNombre() + "!");
                return usuario;
            }
        }

        System.out.println("❌ Cuenta o PIN incorrectos.");
        return null;
    }
}
