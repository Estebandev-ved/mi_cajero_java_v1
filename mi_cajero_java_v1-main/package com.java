package com.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
       Scanner scanner = new Scanner(System.in);
        
       // variables y constantes
       final int PIN_CORRECTO = 1234;
       double saldo = 50000;
       int intentos = 0;
       boolean cuentaBloqueada = false;
       
       // autenticacion segun los intentos
       while (intentos <3) {
            System.out.println("Ingrese su usuario") ;
            String Usuario = scanner.next(); 
            System.out.println("Ingrese su PIN: ");
            int pinIngresado = scanner.nextInt();

            if (pinIngresado == PIN_CORRECTO) {
                System.out.println("!Bienvenido" + Usuario);
                break;
            } else {
                intentos ++;
                System.out.println("PIN incorrecto Mosca pues!!!" + intentos);
            }
        
            if (intentos == 3) {
                cuentaBloqueada = true;
                System.out.println("Mera G*** cuenta bloqueada");
                
            }
       }

       //Si l acuenta esta bloqueada finalizara el programa
       if (cuentaBloqueada) {
            scanner.close();
            return;
        
       }

       //Menu del cajero
       int opcion;
       do {
            System.out.println("Bienvenido a si cajero Groserón!!! ");
            System.out.println("1. Consultar chichigua ");
            System.out.println("2. Retirar menuda ");
            System.out.println("3. Depositar money ");
            System.out.println("4. Abrirse del parche");
            System.out.println("Seleccione puse mijo!!" );
            opcion = scanner.nextInt();

            //manejo de opciones

            switch (opcion) {
                case 1:
                    System.out.println("Ay mar** ténes apenas: $  +" + saldo);
                    break;
                case 2: 
                    System.out.println("Cuanta chichigua vas a sacar?");
                    double retiro = scanner.nextDouble();
                    if (retiro > 0 && retiro <= saldo) {
                        saldo  -= retiro;
                        System.out.println("Retiro exitoso!!, te quedan apenas $" + saldo);

                    } else {
                        System.out.println("Despierte pues !!! su saldo apenas es: " + saldo);
                    }
                    saldo -= retiro;
                    System.out.println("Que chimb* sacasate platica tu nuevo saldo es: "+ saldo);
                    break;
                case 3:
                    System.out.println("Cuanta money va a depositar");
                    double deposito = scanner.nextDouble();
                    if(deposito > 0){
                        saldo -= deposito;
                        System.out.println("Consignacion exitosa cucho!!! su nuevo saldo mi niño es: " +  saldo);
                    }else{
                        System.out.println("Despierte pues ague*** ese valor no se puede");
                    }
                    break;
                case 4:
                    System.out.println("Suerte gono** nos vemos!!");
                    break;
                default:
                    System.out.println("Despierte pues dormido!!! esa no es una opcion");
                    break;
            }

       } while (opcion != 4);
       scanner.close();
    }

    @Override
    public String toString() {
        return "Main []";
    }     
}