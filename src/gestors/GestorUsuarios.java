package gestors;

import java.io.*; // Gestion de archivos
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Clase encargada de gestion de usuarios
*/
public class GestorUsuarios {
    
    // Variables de la clase
    String Nombre;
    String Apellidos;
    String Dni;
    int Edad;


    // Funciones de la clase


    /**
     * @brief Constructor default 
    */
    private GestorUsuarios() {}

    /**
     *  @brief Constructor de parametros
     * @param Nombre_ Nombre del usuario
     * @param Apellidos_ Apellidos del usuario
     * @param Dni_ Dni del usuario
     * @param Edad_ Edad al usuario
    */
    private GestorUsuarios( String Nombre_,
                            String Apellidos_,
                            String Dni_,
                            int Edad_) {

        Nombre = Nombre_;
        Apellidos = Apellidos_;
        Dni = Dni_;
        Edad = Edad_;
    }

    /**
     * @brief Registra a un nuevo usuario dentro del archivo 'users-list.txt'
    */
    private void registrarUsuario() {

        // NOMBRE DE ARCHIVO DE USUARIOS
        String ListaUsuarios = "../../data/users-list.txt";

        // Linea de registro de usuario
        String NuevoUsuario = this.Nombre + " " + this.Apellidos + " " + this.Dni + " " + this.Edad + "\n";

        // Excepcion
        try {
            
            // Lector del archivo
            BufferedWriter writer = new BufferedWriter(new FileWriter(ListaUsuarios, true)); // 'true' para añadir al archivo

            // Escribir el nuevo usuario al final del archivo
            writer.write(NuevoUsuario);  // Añadir el nuevo usuario en una nueva línea

            // Cerrar el flujo de salida
            writer.close();

            System.out.println("[OK] Usuario registrado: " + NuevoUsuario);

        } catch (Exception e) {

			System.out.println("[ERROR] No se ha podido crear el usuario.");
            e.printStackTrace();
        }
    }

    /**
     * @brief Da de alta a un usuario, lo resgistra si no lo está.
    */
    public void darAltaUsuario() {

        try {

            // Ubicacion del archivo
            String ListaUsuarios = "../../data/users-list.txt";

            // Apertura en modo lectura
            BufferedReader reader = new BufferedReader(new FileReader(ListaUsuarios));
            String linea;

            // Leer línea por línea el archivo
            while ((linea = reader.readLine()) != null) {

                // Dividir la línea en campos separados por espacios (" ")
                String[] campos = linea.split(" ");

                // Verificar que la línea tiene los campos necesarios
                if (campos.length == 4) {
                    String NombreUser = campos[0];
                    String ApellidosUser = campos[1];
                    String DniUser = campos[2];
                    int EdadUser = Integer.parseInt(campos[3]); // Convierte a INT

                    // El usuario ya esta en la lista
                    if (NombreUser == Nombre && ApellidosUser == Apellidos && DniUser == Dni && EdadUser == Edad) {
                        System.out.println("[INFO] El usuario actual ya se encuentra registrado.");
                        break;
                    }

                } else {
                    System.out.println("[ERROR] No se han obtenido todos los campos.");
                    break;
                }
            }
            reader.close();

            // El usuario no se encuentra en el archivo
            System.out.println("[INFO] El usuario actual no esta registrado, se registrara.");
            this.registrarUsuario();

        } catch (IOException e) {
            System.out.println("[ERROR] No se ha podido obtener informacion del archivo.");
            e.printStackTrace();
        }
    }

    /**
     * @brief Modifica los datos del usuario
     * @param Nombre_ El nombre del usuario
     * @param Apellidos_ Los apellidos del usuario
     * @param Dni_ El Dni del usuario
     * @param Edad_ La edad del usuario
    */
    public void modificarUsuario(final String Nombre_, final String Apellidos_, final String Dni_, final int Edad_) {

        try {

            Nombre = Nombre_;
            Apellidos = Apellidos_;
            Dni = Dni_;
            Edad = Edad_;

        } catch (Exception e) {
            System.out.println("[ERROR] No se han podido modificar los datos del usuario");
            return;
        }

    }

    /**
     * @brief Imprime por pantalla la informacion de los usuarios actualmente registrados
    */
    public void listarUsuariosRegistrados() {

        System.out.println("Usuario: " + Nombre + " " + Apellidos + " " + Dni + " " + Edad);
    }
    
}
