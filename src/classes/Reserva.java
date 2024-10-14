package classes;

/*
 * Las clases ReservaInfantil, ReservaFamiliar, y ReservaAdultos
 * serán subclases de Reserva.
 * 
 * La clase Factory será la encargada de crear los distintos tipos
 * de reserva. Según el tipo de reserva que se necesite crear,
 * esta clase Factory decidirá cuál de las subclases de Reserva
 * debe instanciar.
 * 
 * Así, no habrá que precuparse por instanciar
 * en otras partes del código.
 * 
*/

/**
 * @brief Clase Reserva.
 * 
 * Id_ Identificador del usuario que realiza la reserva
 * Fecha_
 * Hora_
 * Precio_
 * Descuento_
 * 
 * Estos atributos estarán en la clase Reserva, que será
 * la clase abstracta (base) de los distintos tipos de reservas.
*/
public abstract class Reserva {
        //private
        private String Id_;
        private String Fecha_;
        private String Hora_;
    
        private double Precio_;
        private double Descuento_;


        //constructor
        
            public Reserva(String id, String fecha, String hora, double precio, double descuento) {
                //this.id = id;
                //this.fecha = fecha;
                //this.hora = hora;
                //this.precio = precio;
                //this.descuento = descuento;
            }
        
            // Métodos comunes, como getters y setters.
            public abstract void mostrarDetalles();
}
        





