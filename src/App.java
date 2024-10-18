import classes.Usuario;
import gestors.*;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {

        // Iniciar el scanner.
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Iniciar gestores.
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorPistas gestorPistas = new GestorPistas();
        GestorReservas gestorReservas = new GestorReservas();

        // Bucle principal del menú
        while (running) {
            // Limpiar la pantalla (simulación imprimiendo muchas líneas en blanco)
            clearScreen();

            // Mostrar el menú
            System.out.println("--- GESTOR DE POLIDEPORTIVO ---\n");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestion de Reservas");
            System.out.println("3. Gestion de pistas");
            System.out.println("4. Salir");
            System.out.print("\nSeleccione una opción: ");

            // Leer la opción del usuario
            String option = scanner.nextLine();

            // Procesar la opción seleccionada
            switch (option) {

                // Usuarios.
                case "1" -> {
                    subMenuUsuarios(scanner, gestorUsuarios);  
                }

                // Reservas.
                case "2" -> {
                    System.out.println("Has seleccionado Gestión de Reservas.");
                    pause(); // Pausa para que el usuario vea el mensaje
                }

                // Pistas.
                case "3" -> {
                    System.out.println("Has seleccionado Gestión de Pistas.");
                    pause(); // Pausa para que el usuario vea el mensaje
                }

                // Salir.
                case "4" -> {
                    System.out.println("\n+ Saliendo del programa...");
                    running = false; // Terminar el bucle
                }
                
                default -> {
                    System.out.println("\n+ Opción no válida. Intente de nuevo.");
                    sleep(1); // Pausa de 1 segundo antes de mostrar el menú nuevamente
                }
            }
        }

        // Cerrar el scanner
        scanner.close();
    }


    //* SUBMENUS

    /**
     * Muestra un SubMenu interactivo para gestionar usuarios.
     * @param scanner - Scanner heredado del menu principal.
     * @param gestorUsuarios - El gestor de usuarios.
    */
    private static void subMenuUsuarios(Scanner scanner, GestorUsuarios gestorUsuarios) {

        // Iniciar el scanner.
        boolean running = true;
        String field1, field2, field3, field4, field5;

        // Bucle principal del menú
        while (running) {
            // Limpiar la pantalla (simulación imprimiendo muchas líneas en blanco)
            clearScreen();

            // Mostrar el menú
            System.out.println("--- GESTION DE USUARIOS ---\n");
            System.out.println("1. Dar de alta a un usuario");
            System.out.println("2. Modificar usuario");
            System.out.println("3. Ver usuarios");
            System.out.println("4. Atras");
            System.out.print("\nSeleccione una opción: ");
            
            // Leer la opción del usuario
            String option = scanner.nextLine();

            // Procesar la opción seleccionada
            switch (option) {

                // Dar de alta a un usuario.
                case "1" -> {
                    
                    // Obtener datos.

                    clearScreen();
                    System.out.println("\n- [DAR DE ALTA A UN NUEVO USUARIO] -\n");

                    System.out.print("- Nombre del usuario: ");
                    field1 = scanner.nextLine();

                    System.out.print("- Apellidos del usuario: ");
                    field2 = scanner.nextLine();

                    System.out.print("- DNI del usuario: ");
                    field3 = scanner.nextLine();

                    System.out.print("- Edad del usuario: ");
                    field4 = scanner.nextLine();

                    Usuario usuario = new Usuario(field1, field2, field3, Integer.parseInt(field4));

                    if (gestorUsuarios.darAltaUsuario(usuario)) {
                        System.out.println("\n+ El usuario se ha dado de alta correctamente en el sistema.\n");
                    } else {
                        System.out.println("\n+ El usuario con DNI:" + usuario.getDni() + " ya esta dado de alta.\n");
                    }

                    pause();
                }

                // Modificar usuario.
                case "2" -> {
                    
                    clearScreen();
                    System.out.println("\n- [MODIFICAR UN USUARIO] -\n");

                    System.out.print("- Para buscar el usuario a cambiar introduzca su DNI: ");
                    field1 = scanner.nextLine();

                    System.out.print("- Nuevo nombre del usuario: ");
                    field2 = scanner.nextLine();

                    System.out.print("- Nuevos apellidos del usuario: ");
                    field3 = scanner.nextLine();

                    System.out.print("- Nueva Edad del usuario: ");
                    field4 = scanner.nextLine();

                    if (gestorUsuarios.modificarUsuario(field1, field2, field3, Integer.parseInt(field4))) {
                        System.out.println("\n+ Se ha modificado correctamente la informacion del usuario.\n");
                    } else {
                        System.err.println("\n+ No se ha podido modificar la informacion del usuario.\n");
                    }

                    pause();
                }

                // Ver usuarios.
                case "3" -> {

                    System.out.println("\n-[LISTA DE USUARIOS]-\n");

                    gestorUsuarios.listarUsuariosRegistrados();
                    pause();
                }
                
                // Atras.
                case "4" -> {
                    running = false; // Terminar el bucle
                }

                // opcion no valida.
                default -> {
                    System.out.println("\n+ Opción no válida. Intente de nuevo.");
                    sleep(1);
                }
            }
        }

        //return;
    }


    //* FUNCIONES AUXILIARES

    // Método para simular la limpieza de pantalla imprimiendo 50 líneas en blanco
    private static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // Método para pausar brevemente la ejecución
    // Ignorar aviso del compilador (ya que se cierra externamente)
    @SuppressWarnings("resource")
    private static void pause() {
        System.out.println("Presione ENTER para continuar...");
        new Scanner(System.in).nextLine(); // Esperar que el usuario presione ENTER
    }

    // Para el programa X seg
    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);    
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }   
    }

}
