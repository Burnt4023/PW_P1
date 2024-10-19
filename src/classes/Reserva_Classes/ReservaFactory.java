package classes.Reserva_Classes;


//* ----- CLASE FACTORY -----
public class ReservaFactory {
    
    public static Reserva crearReserva(String tipoReserva)
    {
        switch (tipoReserva) // Según el tipo de reserva deseado, se instanciará una subclase u otra.
        {
            case "Infantil":
                return new ReservaInfantil();

            case "Familiar":
                return new ReservaFamiliar();

            case "Adultos":
                return new ReservaAdultos();
                    
            default:
                throw new IllegalArgumentException("Tipo de reserva no soportado");
        }
    }
    
}
