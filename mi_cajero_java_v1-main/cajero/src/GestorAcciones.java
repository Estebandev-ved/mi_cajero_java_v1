import java.util.ArrayList;
import java.util.Scanner;

public class GestorAcciones {
    private Scanner scanner = new Scanner(System.in);

    public void consultarSaldo(Usuario usuario){
        System.out.println("💰 Su saldo actual es: $ "+  usuario.getCuenta().getSaldo());
    }

    public void retirarDinero(Usuario usuario){
        System.out.println("Digite el valor a retirar");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        boolean exito = usuario.getCuenta().retirar(monto);
        if (exito) {
            System.out.println("✅ Retiro exitoso. Nuevo saldo: $" + usuario.getCuenta().getSaldo());
        }else{
            System.out.println("❌ No se pudo retirar. Verifique el monto. ");
        }
    }

    public static void enviarDinero(Usuario origen, Scanner scanner, ArrayList<Usuario> usuarios) {
        System.out.print("Ingrese el número de cuenta destino: ");
        String numeroDestino = scanner.nextLine();

        Usuario destino = null;
        for (Usuario u : usuarios) {
            if (!u.equals(origen) && u.getCuenta().getNumeroCuenta().equals(numeroDestino)) {
                destino = u;
                break;
            }
        }

        if (destino == null) {
            System.out.println("❌ Cuenta destino no encontrada.");
            return;
        }

        System.out.print("Ingrese el monto a enviar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // limpiar buffer

        if (origen.getCuenta().retirar(monto)) {
            destino.getCuenta().depositar(monto);
            System.out.println("✅ Envío exitoso a " + destino.getNombre() +
                               ". Tu nuevo saldo es: $" + origen.getCuenta().getSaldo());
        } else {
            System.out.println("❌ No tienes suficiente saldo para enviar.");
        }
    }
}

