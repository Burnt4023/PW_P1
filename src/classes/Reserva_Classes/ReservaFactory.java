package classes.Reserva_Classes;

import classes.Usuario;
import classes.Pista;



//* ----- Clase ReservaFactory
public class ReservaFactory {
    
    public static Reserva crearReserva(String tipoReserva, Usuario usuario, Pista pista, int NumeroNinos, int NumeroAdultos)
    {
        switch (tipoReserva) // Según el tipo de reserva deseado, se instanciará una subclase u otra.
        {
            case "Infantil":
                // Se instancia una clase de tipo ReservaInfantil
                return new ReservaInfantil(usuario, pista, NumeroNinos);

            case "Familiar":
                // Se instancia una clase de tipo ReservaFamiliar
                return new ReservaFamiliar(usuario, pista, NumeroNinos, NumeroAdultos);

            case "Adultos":
                // Se instancia una clase de tipo ReservaAdultos
                return new ReservaAdultos(usuario);
                    
            default:
                throw new IllegalArgumentException("Tipo de reserva no soportado");
        }
    }

}


//* ----- Ejemplo de Implementación Reservas en la App

    // String tipoReserva = "Adultos"; // Esto podría venir de un formulario en la UI, por ejemplo.

    // Crear una reserva usando la fábrica
    // Reserva reserva = ReservaFactory.crearReserva(tipoReserva, usuario, pista, 0, 5);
    // Eso crearía una reserva para 5 adultos


    // Ahora ya se puede trabajar con la reserva creada
    // System.out.println(reserva.toString()); ...