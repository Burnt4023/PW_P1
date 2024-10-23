import classes.Material;
import classes.Material.Estado;
import classes.Material.Tipo;
import classes.Pista;
import classes.Pista.TamanoPista;
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
                    subMenuPistas(scanner, gestorPistas);
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

    //* --- SUBMENUS ---

    /**
     * Muestra un SubMenu interactivo para gestionar usuarios.
     * @param scanner - Scanner heredado del menu principal.
     * @param gestorUsuarios - El gestor de usuarios.
    */
    private static void subMenuUsuarios(Scanner scanner, GestorUsuarios gestorUsuarios) {

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

    /**
     * Muestra un subMenu interectivo para gestionar pistas.
     * @param scanner - Scanner heredado del menu principal.
     * @param gestorpistas - El gestor de pistas.
     */
    private static void subMenuPistas(Scanner scanner, GestorPistas gestorPistas) {

        boolean running = true;
        String field1, field2, field3, field4, field5;

        // Bucle principal del menú
        while (running) {
            // Limpiar la pantalla (simulación imprimiendo muchas líneas en blanco)
            clearScreen();

            // Mostrar el menú
            System.out.println("--- GESTION DE PISTAS ---\n");
            System.out.println("1. Crear nueva pista");
            System.out.println("2. Crear nuevo material");
            System.out.println("3. Asociar material a pista");
            System.out.println("4. Listar pistas no disponibles");
            System.out.println("5. Listar pistas libres");
            System.out.println("6. Atras");
            System.out.print("\nSeleccione una opción: ");
            
            // Leer la opción del usuario
            String option = scanner.nextLine();

            // Procesar la opción seleccionada
            switch (option) {

                // Crear pista.
                case "1" -> {
                    
                    try {
                        clearScreen();
                        System.out.println("\n- [CREAR NUEVA PISTA] -\n");

                        // Obtener datos de la pista
                        System.out.print("- Nombre de la pista: ");
                        field1 = scanner.nextLine();

                        System.out.print("- Tipo pista (Exterior[0]/Interior[1]): ");
                        field2 = scanner.nextLine();
                        boolean tipoPista = Integer.parseInt(field2) == 1;  // Convertir a booleano (1 = Interior, 0 = Exterior)

                        System.out.print("- Tamaño de la pista (Minibasket[0]/Adultos[1]/3vs3[2]): ");
                        field3 = scanner.nextLine();
                        TamanoPista tamanoPista = switch (field3) {  // Convertir a enum
                            case "0" -> TamanoPista.MINIBASKET;
                            case "1" -> TamanoPista.ADULTOS;
                            case "2" -> TamanoPista.TRES_VS_TRES;
                            default -> throw new IllegalArgumentException("Tamaño de pista no válido");
                        };

                        System.out.print("- Número máximo de jugadores: ");
                        field4 = scanner.nextLine();
                        int maxJugadores = Integer.parseInt(field4);  // Convertir a entero

                        // Crear la nueva pista con los valores obtenidos
                        Pista pista = new Pista(field1, true, tipoPista, tamanoPista, maxJugadores);

                        // Aquí podrías añadir la pista a algún gestor de pistas o a una lista.
                        if (gestorPistas.crearPista(pista)) {
                            System.out.println("\n+ Pista creada exitosamente.\n");
                            pause();  // Pausar para que el usuario pueda ver el mensaje
                        } else {
                            System.out.println("\n+ Error al crear la pista.\n");
                            pause();  // Pausar para que el usuario pueda ver el mensaje
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println("+ Error en obtencion de informacion\n");
                        sleep(1);
                    }

                }

                // Crear Material.
                case "2" -> {
                    
                    clearScreen();
                    System.out.println("\n- [CREAR NUEVO MATERIAL] -\n");

                    System.out.print("- Tipo de material (Pelota[0]/Canasta[1]/Cono[2]): ");
                    field1 = scanner.nextLine();
                    Tipo tipoMaterial = switch (field1) {  // Convertir a enum
                        case "0" -> Tipo.PELOTA;
                        case "1" -> Tipo.CANASTA;
                        case "2" -> Tipo.CONO;
                        default -> throw new IllegalArgumentException("Tamaño de pista no válido");
                    };

                    System.out.print("- Uso del material (No usable[0]/usable[1]): ");
                    field2 = scanner.nextLine();
                    boolean usoMaterial = Boolean.parseBoolean(field2);

                    System.out.print("- Estado del material (Disponible[0]/Reservado[1]/Malo[2]): ");
                    field3 = scanner.nextLine();
                    Estado estadoMaterial = switch (field3) {  // Convertir a enum
                        case "0" -> Estado.DISPONIBLE;
                        case "1" -> Estado.RESERVADO;
                        case "2" -> Estado.MALO;
                        default -> throw new IllegalArgumentException("Tamaño de pista no válido");
                    };

                    if (gestorPistas.crearMaterial(tipoMaterial, usoMaterial, estadoMaterial)) {
                        System.out.println("\n+ Material creado exitosamente.\n");
                        pause();  // Pausar para que el usuario pueda ver el mensaje
                    } else {
                        System.out.println("\n+ Error al crear el material.\n");
                        pause();  // Pausar para que el usuario pueda ver el mensaje
                    }

                }

                // Asociar Material a pista.
                case "3" -> {

                    System.out.println("\n- [ESCOGE LA PISTA] -\n");

                    gestorPistas.listarListaPistas();
                    field1 = scanner.nextLine();
                    int posPista = Integer.parseInt(field1);


                    System.out.println("\n- [ESCOGE EL MATERIAL A ASOCIAR] -\n");

                    gestorPistas.listarListaMateriales();
                    field1 = scanner.nextLine();
                    int posMaterial = Integer.parseInt(field1);

                    // Obtenemos los materiales y la pista

                    Pista pistaObtenida = gestorPistas.getPista(posPista-1);
                    Material materialObtenido = gestorPistas.getMaterial(posMaterial-1);

                    // Comprobamos que exista la pista.
                    if (pistaObtenida != null) {
                        
                        // Comprobamos que exista el material.
                        if (materialObtenido != null) {

                            if (gestorPistas.AsociarMaterialAPistaDisponible(materialObtenido, pistaObtenida)) {
                                System.out.println("\n+ Se ha asociado el material a la pista correctamente.\n");
                                pause();
                            } else {
                                System.out.println("\n+ No se ha podido asociar el material con la pista indicada.\n");
                                pause();
                            }

                        } else {
                            System.err.println("[ERROR] Material " + posMaterial + " no localizable");
                            sleep(3);
                        }

                    } else {
                        System.err.println("[ERROR] Pista " + posPista + " no localizable");
                        sleep(3);
                    }

                }
                
                // Listar pistas no disponible.
                case "4" -> {

                    System.out.println("\n- [LISTA DE PISTAS NO DISPONIBLES] -\n");

                    gestorPistas.ListarPistasNoDisponibles();

                    pause();

                }

                // Listar pistas libres.
                case "5" -> {
                    
                    clearScreen();
                    System.out.println("\n- [LISTAR PISTAS DISPONIBLES] -\n");

                    System.out.print("- Tipo pista (Exterior[0]/Interior[1]): ");
                    field1 = scanner.nextLine();
                    boolean tipoPista = Integer.parseInt(field1) == 1;  // Convertir a booleano (1 = Interior, 0 = Exterior)

                    System.out.print("- Número minimo de jugadores: ");
                    field2 = scanner.nextLine();
                    int maxJugadores = Integer.parseInt(field2);
                    
                    System.out.println("\n- [LISTA DE PISTAS DISPONIBLES] -\n");

                    gestorPistas.ListarPistasLibres(maxJugadores, tipoPista);
                    
                    pause();
                }

                // Atras.
                case "6" -> {
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

    /**
     * Muestra un submenu interectivo para gestionar las reservas.
     * @param scanner - Scanner heredado del menu principal.
     * @param gestorReservas - El gestor de reservas.
     */
    private static void subMenuReservas(Scanner scanner, GestorReservas gestorReservas) {

        boolean running = true;
        String field1, field2, field3, field4, field5; // Utilizar para obtener variables por linea de comandos.

        // Bucle principal del menú
        while (running) {
            // Limpiar la pantalla (simulación imprimiendo muchas líneas en blanco)
            clearScreen();

            // Mostrar el menú
            System.out.println("--- GESTION DE PISTAS ---\n");
            System.out.println("1. Hacer reserva individual");
            System.out.println("2. Hacer reserva con bono");
            System.out.println("3. Modificar reserva");
            System.out.println("4. Eliminar reserva");
            System.out.println("5. Listar reservas futuras");
            System.out.println("6. Listar reservas en un dia determinado");
            System.out.println("7. Atras");
            System.out.print("\nSeleccione una opción: ");
            
            // Leer la opción del usuario
            String option = scanner.nextLine();

            // Procesar la opción seleccionada
            switch (option) {

                // Hacer reserva.
                case "1" -> {

                    System.out.println("Introduce una linea de caracteres: "); // Mostrar mensajes.
                    field1 = scanner.nextLine(); // Obtener datos del buffer de entrada.
                    int ejemplo = Integer.parseInt(field1); // Conversion de String a Integer.

                }

                // Hacer reserva con bono.
                case "2" -> {
                    

                }

                // Modificar reserva.
                case "3" -> {


                }
                
                // Eliminar reserva.
                case "4" -> {


                }

                // Listar reservas futuras.
                case "5" -> {
                    
                }

                // Listar reservas en dia determinado.
                case "6" -> {
                    
                }

                // Atras.
                case "7" -> {
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

    //* --- FUNCIONES AUXILIARES ---

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
