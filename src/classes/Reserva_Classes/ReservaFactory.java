package classes.Reserva_Classes;

import classes.Pista;
import classes.Usuario;



//* ----- Clase ReservaFactory
public class ReservaFactory {
    
    public enum TipoReserva {
        ADULTOS,
        FAMILIAR,
        INFANTIL;
    }

    // Método para crear una reserva normal
    public static Reserva crearReserva(String TipoReserva, Usuario Usuario, Pista Pista, int NumeroNinos, int NumeroAdultos)
    {
        switch (TipoReserva) // Según el tipo de reserva deseado, se instanciará una subclase u otra.
        {
            case "Infantil":
                // Se instancia una clase de tipo ReservaInfantil
                return new ReservaInfantil(Usuario, Pista, NumeroNinos, NumeroAdultos);

            case "Familiar":
                // Se instancia una clase de tipo ReservaFamiliar
                return new ReservaFamiliar(Usuario, Pista, NumeroNinos, NumeroAdultos);

            case "Adultos":
                // Se instancia una clase de tipo ReservaAdultos
                return new ReservaAdultos(Usuario);
                    
            default:
                throw new IllegalArgumentException("Tipo de reserva no soportado");
        }
    }


    // Método para crear una reserva de bono
    public static Reserva crearReservaBono(String TipoReserva, Usuario Usuario, Pista Pista, int NumeroNinos, int NumeroAdultos, Boolean EsBono, String IdBono, int NumeroSesion) {
        Reserva reserva;

        switch (TipoReserva) {
            case "Infantil":
                reserva = new ReservaInfantil(Usuario, Pista, NumeroNinos, NumeroAdultos);
                break;
                
            case "Familiar":
                reserva = new ReservaFamiliar(Usuario, Pista, NumeroNinos, NumeroAdultos);
                break;
                
            case "Adultos":
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