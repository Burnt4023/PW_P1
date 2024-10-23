package gestors;

import classes.*;
import classes.Reserva_Classes.Reserva;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase del gestor de las reservas. 
*/
public class GestorReservas {
    
    //* --- VARIABLES DE LA CLASE ---

    ArrayList<Reserva> ListaReservasAdultos;
    ArrayList<Reserva> ListaReservasFamiliar;
    ArrayList<Reserva> ListaReservasInfantil;

    //* --- FUNCIONES DE LA CLASE ---

    /**
     * Carga los datos del fichero de gestion de reservas. 
     */
    private void loadData() {

    }

    /**
     * Carga los datos del fichero de gestion de reservas.
     */
    private void saveData() {

    }

    /**
     * Default constructor. 
    */
    public GestorReservas() {}

    /**
     * Parameters contrcutor.
    */
    public GestorReservas(Usuario user_, Reserva reserve_) {

        // Limpia los arrays.
        ListaReservasFamiliar.clear();
        ListaReservasAdultos.clear();
        ListaReservasInfantil.clear();

        //todo - Load Data.
    }
    

    public boolean hacerReservaIndividual(Usuario user, Pista PistaAReservar) {
        
        return true;
    }

    public boolean hacerReservaConBono(Bono BonoUsuario, Usuario user, Pista PistaAReservar) {

        return true;
    }

    /**
     * Obtiene el precio de las horas de una reserva.
     * @param minutes Minutos que se reservara una pista.
     * @return Cantidad en EUR que cuesta la reserva.
     * @return -1 Si las cantidad de minutos no es la esperada.
     * @warning Las cantidades de minutos aceptadas son: 60, 90 y 120.
     */
    protected int getPrecioReserva(int minutes) {

        if (minutes == 60) {
            return 20;
        } else if (minutes == 90) {
            return 30;
        } else if (minutes == 120) {
            return 40;
        }

        return -1;
    }

    /**
     * Comprueba si una pista puede modificarse. Una pista puede ser modificada 24h antes de que empiece.
     * @param PistaConcreta
     * @return true - Si la pista puede ser modificada.
     * @return false - Si la pista no puede ser modificada.
    */
    protected boolean checkIfPistaCanBeModified(Pista PistaConcreta) {

        return true;
    }

    /**
     * To comment. 
    */
    public void verReservasFuturas() {


    }

    /**
     * To comment. 
    */
    public void verReserva(Date day) {

    }

    /**
     * To comment. 
    */
    public void verreserva(Pista PistaConcreta) {

    }

    /**
     * To comment. 
    */
    public void verReserva(Date day, Pista PistaConcreta) {

    }
}
