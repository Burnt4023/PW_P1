package classes;

import java.util.Date;



/**
    >> El Patrón Factory es un patrón de diseño que se utiliza para delegar la creación
    de objetos a una clase "fábrica", en lugar de crear instancias directamente en el código.


    Clase Reserva es de tipo abstracto (es decir, nunca se instanciará).

    Las clases ReservaInfantil, ReservaFamiliar, y ReservaAdultos serán subclases de Reserva.

    La clase ReservaFactory será la encargada de crear los distintos tipos de Reserva,
    según el tipo de reserva que se necesite instanciar.

*/

public abstract class Reserva {

        private String IdUsuario_;  // Usuario que realiza la reserva.
        private Date Fecha_;
        private int Duracion_;  // En minutos.
        private float Precio_;  // En euros.
        private boolean Descuento_;


        //Constructor vacío (sin parámetros)
        public Reserva() {}

        // Getters
        public String getIdUsuario() {
            return IdUsuario_;
        }

        public Date getFecha() {
            return Fecha_;
        }

        public int getDuracion() {
            return Duracion_;
        }

        public float getPrecio() {
            return Precio_;
        }

        public boolean getDescuento() {
            return Descuento_;
        }

        
        // Setters
        public void setIdUsuario(String IdUsuario) {
            this.IdUsuario_ = IdUsuario;
        }

        public void setFecha(Date Fecha) {
            this.Fecha_ = Fecha;
        }

        public void setDuracion(int Duracion) {
            this.Duracion_ = Duracion;
        }

        public void setPrecio(float Precio) {
            this.Precio_ = Precio;
        }

        public void setDescuento(boolean Descuento) {
            this.Descuento_ = Descuento;
        }
}




// ----- CLASE FACTORY -----
public class ReservaFactory {
    
    public static Reserva crearReserva(String tipoReserva)
    {
        switch (tipoReserva) // Según el tipo de reserva deseado, se instanciará una subclase u otra.
        {
            case "Infantil":
                return new ReservaInfantil();

            case "Familiar":
                //return new ReservaFamiliar();

            case "Adultos":
                //return new ReservaAdultos();
                    
            default:
                throw new IllegalArgumentException("Tipo de reserva no soportado");
        }
    }
    
}



//* ----- ReservaInfantil -----
public class ReservaInfantil extends Reserva {

        // Constructor específico
        public ReservaInfantil()
        {
            super(); // Llama al constructor de Reserva
            // Podrías poner comportamientos específicos de una reserva infantil aquí
        }
}


//* ----- ReservaFamiliar -----
public class ReservaFamiliar extends Reserva {

    // Constructor para la reserva familiar
    public ReservaFamiliar() {
        super();
        //Aquí se pueden añadir comportamientos o atrib. especiales de reserva familiar...
    }


}

    
    //* ----- ReservaAdultos -----
public class ReservaAdultos extends Reserva {


    // Constructor espeífico para la reserva de adultos
    public ReservaAdultos() {
        super();
    }


}




