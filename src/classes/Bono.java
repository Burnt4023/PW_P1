package classes;

import classes.Pista.TamanoPista;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Bono {

    // Variables de la clase.

    String IdBono; // Identificador del bono.
    TamanoPista TipoDeSesion; // Especifica que tipo de bono es.
    Usuario UsuarioAsociado;
    int UsosRestantes;
    Date AltaDelBono; // Fecha que el bono se dio de alta.
    boolean Caducado;
    
    // Funciones de la clase.
    ArrayList<Bono> ListaBonos;

    /**
     * Default constructor. 
    */
    public Bono() {}

    /**
     * Constructor con parametros. 
    */
    public Bono(String IdBono2, TamanoPista TipoDeSesion_,
                Usuario UsuarioAsociado_) {

        IdBono = IdBono2;
        TipoDeSesion = TipoDeSesion_;
        UsuarioAsociado = UsuarioAsociado_;
        UsosRestantes = 5; // 5 Usos por bono.
        AltaDelBono = new Date(); // Fecha actual
        Caducado = false;
    }

    /**
     * Guardar el bono en el archivo 'bonos-list.txt', situado en '/data'.
     */
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Método para guardar los datos del bono al final del archivo.
    private void saveData() {
        try {
            String ListaBonos = "data/bonos-list.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(ListaBonos, true)); // true para añadir al final del archivo
            
            // Convierte los datos del bono a una línea de texto en formato CSV
            String lineaBono = String.format("%s, %s, %s, %d, %s, %b%n",
                    IdBono,
                    TipoDeSesion.toString(), // Asegúrate de que TamanoPista tenga un método toString adecuado
                    UsuarioAsociado.getNombre(), // Suponiendo que Usuario tiene un método getNombreUsuario()
                    UsosRestantes,
                    dateFormat.format(AltaDelBono), // Convierte la fecha a un formato de texto
                    Caducado
            );

            // Escribe la línea en el archivo
            writer.write(lineaBono);
            writer.close();
            
            System.out.println("[INFO] Bono guardado exitosamente en el archivo.");

        } catch (Exception e) {
            System.err.println("[ERROR] No se han podido guardar los bonos.\n");
            e.printStackTrace();
        }
    }


    /**
     * Cargar el bono desde el archivo 'bonos-list.txt', situado en '/data'.
     */
    private void loadData() {
        
    }

    /**
     * Comprueba que el bono no tenga un año o mas, ya que estaria caducado.
     */
    private void comprobarCaducidad() {

        Date NowDate = new Date();

        long difference = NowDate.getTime() - AltaDelBono.getTime();

        int DiferenciaEnYears = (int) (difference / (1000L * 3600 * 24 * 365));

        if (DiferenciaEnYears >= 1) {
            Caducado = true;
        }
    }

    /**
     * Usa el bono. (Consume un uso).
     * @return true - Si se ha usado el bono.
     * @return false - Si no se ha usado el bono.
    */
    public Boolean usarBono() {

        // Comprobar fecha de caducidad.
        comprobarCaducidad();

        // Si no tiene mas usos.
        if (UsosRestantes <= 0) {
            return false;
        }

        // Si esta caducado.
        if (Caducado == false) {
            return false;
        } else {

            UsosRestantes -= 1;
        }

        return true;
    }

    
}
