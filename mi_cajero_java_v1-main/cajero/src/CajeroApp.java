import java.util.ArrayList;
import java.util.Scanner;

public class CajeroApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorAcciones gestorAcciones = new GestorAcciones();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario usuarioActual = null;

        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Bienvenido al Cajero ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    GestorUsuarios GestorUsuarios = new GestorUsuarios();
                    Usuario nuevo = GestorUsuarios.registrarUsuario(scanner);
                    usuarios.add(nuevo);
                    break;
                case 2:
                    usuarioActual = gestorUsuarios.iniciarSesion(scanner, usuarios);
                    if (usuarioActual != null) {
                        boolean volver = false;
                        while (!volver) {
                            System.out.println("\n--- Menú Cajero ---");
                            System.out.println("1. Consultar saldo");
                            System.out.println("2. Retirar dinero");
                            System.out.println("3. Enviar dinero");
                            System.out.println("4. Cerrar sesión");
                            System.out.print("Elige una opción: ");
                            int opcionSesion = scanner.nextInt();
                            scanner.nextLine();

                            switch (opcionSesion) {
                                case 1:
                                    gestorAcciones.consultarSaldo(usuarioActual);
                                    break;
                                case 2:
                                    gestorAcciones.retirarDinero(usuarioActual);
                                    break;
                                case 3:
                                    GestorAcciones.enviarDinero(usuarioActual, scanner, usuarios);
                                    break;
                                case 4:
                                    volver = true;
                                    break;
                                default:
                                    System.out.println("❌ Opción inválida.");
                            }
                        }
                    }
                    break;
                case 3:
                    salir = true;
                    System.out.println("Gracias por usar el cajero.");
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
            }
        }

        scanner.close();
    }
}