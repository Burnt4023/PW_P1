package classes;


public class Usuario {
    
    // Variables
    private String Nombre;
    private String Apellidos;
    private String Dni;
    private int Edad;

    // Funciones
    
    /**
     * Default Constructor.
    */
    public Usuario() {}

    /**
     * Parameters constructor.
     * @param Nombre_ Nombre del usuario
     * @param Apellidos_ Apellidos del usuario
     * @param Dni_ Dni del usuario
     * @param Edad_ Edad al usuario
    */
    public Usuario( String Nombre_,
                            String Apellidos_,
                            String Dni_,
                            int Edad_) {

        Nombre = Nombre_;
        Apellidos = Apellidos_;
        Dni = Dni_;
        Edad = Edad_;
    }

    /**
     * Getter de Nombre
     * @return Nombre
     */
    public String getNombre() {return Nombre;}

    /**
     * Getter de variable Apellidos
     * @return Apellidos
     */
    public String getApellidos() {return Apellidos;}

    /**
     * Getter de Dni
     * @return Dni
     */
    public String getDni() {return Dni;}

    /**
     * Getter de Edad
     * @return Edad
     */
    public int getEdad() {return Edad;}

    /**
     * Change the Nombre parameter.
     * @param Nombre_ Nombre del usuario.
    */
    public void setNombre(String Nombre_) {
        Nombre = Nombre_;
    }

    /**
     * Change the Apellidos parameter.
     * @param Apellidos_ Apellidos del usuario.
     */
    public void setApellidos(String Apellidos_) {
        Apellidos = Apellidos_;
    }

    /**
     * Change the Edad parameter.
     * @param Edad_ Edad el usuario.
     */
    public void setEdad(int Edad_) {
        Edad = Edad_;
    }
}
