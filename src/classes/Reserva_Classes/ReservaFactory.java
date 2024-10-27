package classes.Reserva_Classes;

import classes.Pista;
import classes.Usuario;



//* ----- Clase ReservaFactory
public class ReservaFactory {

    // Método para crear una reserva normal
    public static Reserva crearReserva(Reserva.TipoReserva TipoReserva, Usuario Usuario, Pista Pista, int NumeroNinos, int NumeroAdultos)
    {
        switch (TipoReserva) // Según el tipo de reserva deseado, se instanciará una subclase u otra.
        {
            case INFANTIL:
                // Se instancia una clase de tipo ReservaInfantil
                return new ReservaInfantil(Usuario, Pista, NumeroNinos, NumeroAdultos);

            case FAMILIAR:
                // Se instancia una clase de tipo ReservaFamiliar
                return new ReservaFamiliar(Usuario, Pista, NumeroNinos, NumeroAdultos);

            case ADULTOS:
                // Se instancia una clase de tipo ReservaAdultos
                return new ReservaAdultos(Usuario);
                    
            default:
                throw new IllegalArgumentException("Tipo de reserva no soportado");
        }
    }


    // Método para crear una reserva de bono
    public static Reserva crearReservaBono(Reserva.TipoReserva TipoReserva, Usuario Usuario, Pista Pista, int NumeroNinos, int NumeroAdultos, Boolean EsBono, int IdBono, int NumeroSesion) {
        Reserva reserva;

        switch (TipoReserva) {
            case INFANTIL:
                reserva = new ReservaInfantil(Usuario, Pista, NumeroNinos, NumeroAdultos);
                break;
                
            case FAMILIAR:
                reserva = new ReservaFamiliar(Usuario, Pista, NumeroNinos, NumeroAdultos);
                break;
                
            case ADULTOS:
                reserva = new ReservaAdultos(Usuario);
                break;
                
            default:
                throw new IllegalArgumentException("Tipo de reserva no soportado");
        }


        // Configurar como bono. Quiero que la instancia tenga los campos de bono modificados.
        reserva.setEsBono(true);
        reserva.setIdBono(IdBono);
        reserva.setNumeroSesion(NumeroSesion);

        return reserva;

    }
}




// Ejemplo de Implementación Reservas en la App


    // String tipoReserva = "Adultos"; // Esto podría venir de un formulario en la UI, por ejemplo.

    // Crear una reserva usando la fábrica
    // Reserva reserva = ReservaFactory.crearReserva(tipoReserva, usuario, pista, 0, 5);
    // Eso crearía una reserva para 5 adultos


    // Ahora ya se puede trabajar con la reserva creada
    // System.out.println(reserva.toString()); ...