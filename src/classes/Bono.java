package classes;

import classes.Pista.TamanoPista;
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
