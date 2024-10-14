import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Jugador {
    
    private String nombreApellidos;
    private Date fechaNacimiento;
    private Date fechaInscripcion; // Se inicializa con la fecha actual
    private String correoElectronico;

    // Constructor vacío
    public Jugador() {}

    // Constructor parametrizado (sin la fecha de inscripción)
    public Jugador(String nombreApellidos, Date fechaNacimiento, String correoElectronico) {
        this.nombreApellidos = nombreApellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaInscripcion = new Date(); // Inicializa con la fecha actual
        this.correoElectronico = correoElectronico;
    }

    // Getters y setters
    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    // Método toString
    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Jugador{" +
                "nombreApellidos='" + nombreApellidos + '\'' +
                ", fechaNacimiento=" + dateFormat.format(fechaNacimiento) +
                ", fechaInscripcion=" + dateFormat.format(fechaInscripcion) +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }

    // Método calcularAntiguedad
    public int calcularAntiguedad() {
        Date ahora = new Date();
        long diferencia = ahora.getTime() - fechaInscripcion.getTime();
        return (int) (diferencia / (1000L * 60 * 60 * 24 * 365)); // Convertir a años
    }
}