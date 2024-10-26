package classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class Usuario{
    
    //* --- VARIABLES DE LA CLASE --- 

    private String Nombre;
    private String Apellidos;
    private String Dni;
    private int Edad;
    private Date FechaInscripcion;

    
    //* --- FUNCIONES DE LA CLASE ---
    
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
        FechaInscripcion = new Date(); // Inicia con la fecha actual.
    }

     /**
     * Parameters constructor with date.
     * @param Nombre_ Nombre del usuario
     * @param Apellidos_ Apellidos del usuario
     * @param Dni_ Dni del usuario
     * @param Edad_ Edad al usuario
    */
    public Usuario( String Nombre_,
                            String Apellidos_,
                            String Dni_,
                            int Edad_,
                            Date FechaInscripcion_) {

        Nombre = Nombre_;
        Apellidos = Apellidos_;
        Dni = Dni_;
        Edad = Edad_;
        FechaInscripcion = FechaInscripcion_;
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
     * Getter de FechaInscripcion
     * @return FechaInscripcion
    */
    public Date getFechaInscripcion() {return FechaInscripcion;}

    /**
     * Getter de FechaInscripcion String.
     * @return FechaInscripcion con formato "dd/MM/yyyy".
     */
    public String getStringFormatedFechaInscripcion() {

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);

        return df.format(FechaInscripcion);
    }

    /**
     * Getter de Usuario a partir del IdUsuario.
     * @ return instancia Usuario a partir de IdUsuario.
     */
//    public Usuario getUserString(String IdUsuario) {

//       

//        return();
//    }

    /**
     * Convierte un String en un Date.
     * @param String2Convert String que se convertirá.
     * @return El Date del string anterior.
     * @throws ParseException
     * @warning El formato utilizado es "dd/MM/yyyy".
     */
    public Date convertString2Date(final String String2Convert) throws ParseException{

        // Nos llega DD/MM/YYYY
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);

        Date value = df.parse(String2Convert); 

        return value;
    }

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

    /**
     * Calcula la antiguedad con la que un usuario lleva Inscrito en el sistema.
     * @return Devuelve el tiempo que lleva inscrito el usuario en años.
     * @see Jugador Que es el origen de esta funcion.
    */
    public int calcularAntiguedad() {
        Date ahora = new Date();
        long diferencia = ahora.getTime() - FechaInscripcion.getTime();
        return (int) (diferencia / (1000L * 3600 * 24 * 365)); // Convertir a años
    }
}
