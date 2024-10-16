package gestors;

import classes.Usuario; // Gestion de archivos
import java.io.*;
import java.util.ArrayList;

public class GestorUsuarios extends Usuario{
    
    //* --- VARIABLES DE LA CLASE ---

    ArrayList<Usuario> usuarios; // Lista de usuarios


    //* --- FUNCIONES DE LA CLASE PRIVADAS ---

    /**
     * Load ArrayList data from 'users-list.txt'. Located in '/data'.
    */
    private void loadData() {

        try {

            usuarios.clear();

            String ListaUsuarios = "data/users-list.txt";

            BufferedReader reader = new BufferedReader(new FileReader(ListaUsuarios));
            String linea;

            // Leer línea por línea el archivo
            while ((linea = reader.readLine()) != null) {

                // Dividir la línea en campos separados por espacios (" ")
                String[] campos = linea.split(",");

                // Verificar que la línea tiene los campos necesarios
                if (campos.length == 5) {
                    String NombreUser = campos[0];
                    String ApellidosUser = campos[1];
                    String DniUser = campos[2];
                    int EdadUser = Integer.parseInt(campos[3]); // Convierte a INT
                    String FechaInscripcionString = campos[4];

                    // Creamos el usuario guardado
                    Usuario UsuarioCargado = new Usuario(NombreUser, ApellidosUser, DniUser, EdadUser, convertString2Date(FechaInscripcionString));

                    // Anadir al vector
                    usuarios.add(UsuarioCargado);

                } else {

                    System.err.println("[ERROR] No se han obtenido todos los campos.\n");
                    break;
                }
            }

            reader.close();

            System.out.println("[INFO] Se han cargado todos los usuarios correctamente.\n");

        } catch (Exception e) {
            
            System.err.println("[ERROR] No se han podido cargar los usuarios correctamente.\n");
            e.printStackTrace();
        }
    }

    /**
     * Saves ArrayList data from 'users-list.txt'. Located in '/data'.
    */
    private void saveData() {
        try {

            String ListaUsuarios = "data/users-list.txt";
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(ListaUsuarios, false)); // false para sobreescribir el archivo

            for (int i = 0; i < usuarios.size(); i++) {

                
                
                Usuario UsuarioARegistrar = usuarios.get(i);

                // Linea de registro de usuario
                String NuevoUsuario = UsuarioARegistrar.getNombre() + "," + UsuarioARegistrar.getApellidos() + "," +
                                        UsuarioARegistrar.getDni() + "," + UsuarioARegistrar.getEdad() + "," + 
                                        UsuarioARegistrar.getStringFormatedFechaInscripcion() + "\n";

                writer.write(NuevoUsuario);  // Añadir el nuevo usuario en una nueva línea
            }

            writer.close();

            System.out.println("[INFO] Usuarios guardados correctamente.\n");

        } catch (Exception e) {

			System.err.println("[ERROR] No se han podido guardar los usuarios.\n");
            e.printStackTrace();
        }
    }

    
    //* --- FUNCIONES DE LA CLASE PUBLICAS ---

    /**
     * @brief Default constructor.
     * @warning This constructor uses 'users-list.txt' located in '/data'.
     */
    public GestorUsuarios() {

        // Iniciacion del ArrayList
        usuarios = new ArrayList<>();

        // Cargamos inicialmente un usuario vacio para evitar error en .clear().
        Usuario dummy = new Usuario("null", "null", "null", 0);

        usuarios.add(dummy);

        // Carga los usuarios de los almacenados.
        loadData();
    }
    
    /**
     * @brief Registra a un nuevo usuario dentro del archivo 'users-list.txt'.
     * @param UsuarioARegistrar Usuario que se registrara en el sistema.
     * @implNote Cada vez que se registra a un usuario, este actualiza el fichero.
    */
    private void registrarUsuario(final Usuario UsuarioARegistrar) {

        try {

            // Add el nuevo usuario.
            usuarios.add(UsuarioARegistrar);

            // Guardamos la lista actualizada
            saveData();

            System.out.println("[INFO] Usuario registrado correctamente.");

        } catch (Exception e) {
            System.err.println("[ERROR] El usuario no ha podido registrarse correctamente.\n");
            e.getCause();
        }
    }

    /**
     * @brief Da de alta a un usuario, lo resgistra si no lo está.
     * @param UsuarioAComproabar Usuario al que se registara.
     * @return TRUE - Si se dio de alta al usuario.
     * @return FALSE - Si ya estaba registrado.
     * @warning Carga previamente los usuario dentro del archivo '/data/users-list.txt'
    */
    public boolean darAltaUsuario(final Usuario UsuarioAComprobar) {

        try {

            // Comprobar si el usuario nuevo ya esta en la lista.
            for (int i=0; i < usuarios.size(); i++) {

                Usuario aux = usuarios.get(i);
    
                if (UsuarioAComprobar.getDni().equals(aux.getDni())) {
                    System.out.println("[INFO] El usuario ya esta registrado.\n");
                    return false;
                }
            }

            System.out.println("[INFO] El usuario no se ha encontrado en la lista de registrados, se registrara.\n");

            // Guarda la lista cada vez que se registra un usuario.
            registrarUsuario(UsuarioAComprobar);

            
        } catch (Exception e) {
            
            System.err.println("[ERROR] Error al dar alta al usuario.\n");
            e.getCause();
        }

        return true;
    }

    /**
     * @brief Imprime por pantalla la informacion de los usuarios actualmente registrados
    */
    public void listarUsuariosRegistrados() {

        try {
            
            for (int i=0; i < usuarios.size(); i++) {

                Usuario aux = usuarios.get(i);

                System.out.println("Usuario: " + aux.getNombre() + " " + aux.getApellidos() + " " + aux.getDni()+ " " + aux.getEdad() + " " + aux.getStringFormatedFechaInscripcion());
            }

        } catch (Exception e) {
            System.err.println("[ERROR] Error al imprimir la lista de usuarios.\n");
        }
    }
 
    /**
     * @brief Modifica los datos del usuario, buscandolo por su Dni.
     * @param Dni_ El Dni del usuario.
     * @param Nombre_ El nombre del usuario.
     * @param Apellidos_ Los apellidos del usuario.
     * @param Edad_ La edad del usuario.
     * @return TRUE - Si se ha modificado el usuario.
     * @return FALSE - Si no se ha podido modificar el usuario.
    */
    public boolean modificarUsuario(final String Dni_, final String Nombre_, final String Apellidos_, final int Edad_) {

        try {
            
            for (int i=0; i < usuarios.size(); i++) {

                Usuario UsuarioAComprobar = usuarios.get(i);

                if (Dni_.equals(UsuarioAComprobar.getDni())) {
                    // Limpia de la lista el usuario que se va a cambiar.
                    usuarios.remove(i);

                    UsuarioAComprobar.setNombre(Nombre_);
                    UsuarioAComprobar.setApellidos(Apellidos_);
                    UsuarioAComprobar.setEdad(Edad_);

                    // Add el usuario cambiado
                    usuarios.add(UsuarioAComprobar);
                    break;
                }
            } 

            saveData();

            System.out.println("[INFO] Se ha modificado el usuario correctamente");
            

        } catch (Exception e) {
        
            System.err.println("[ERROR] No se han podido modificar los datos del usuario");
            return false;
        }
        return true;
    }
}
