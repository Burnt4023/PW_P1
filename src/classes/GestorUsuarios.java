package classes;

/**
 * @brief Clase encargada de gestion de usuarios 
*/
public class GestorUsuarios {
    
    // Variables de la clase
    int Id;
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
    private GestorUsuarios(int Id_,
                            String Nombre_,
                            String Apellidos_,
                            String Dni_,
                            int Edad_) {

        Id = Id_;
        Nombre = Nombre_;
        Apellidos = Apellidos_;
        Dni = Dni_;
        Edad = Edad_;
    }

    /**
     * @brief Registra a un nuevo usuario
    */
    public void registrarUsuario() {}


    public void darAltaUsuario(){}


    public void modificarUsuario() {}

    /**
     * @brief Imprime por pantalla la informacion de los usuarios actualmente registrados
    */
    public void listarUsuariosRegistrados() {

        System.out.println("Usuario:" + " " + Id + " " + Nombre + " " + Apellidos + " " + Dni + " " + Edad);

    }
    
}
