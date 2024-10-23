package classes.Reserva_Classes;

import java.util.Date;



public abstract class Reserva {

        private String IdUsuario_;  // Usuario que realiza la reserva.
        private Date Fecha_;
        private int Duracion_;  // En minutos.
        private float Precio_;  // En euros.
        private boolean Descuento_;

        private String IdBono_; // Usado si se aplica bono
        private int NumeroSesion_; // Usado si se aplica bono


        //Constructor vacío (sin parámetros)
        public Reserva() {}

        //Constructor clase Reserva
        public Reserva(String IdUsuario, Date Fecha, int Duracion, float Precio, boolean Descuento) {
            IdUsuario_ = IdUsuario;
            Fecha_ = Fecha;
            Duracion_ = Duracion;
            Precio_ = Precio;
            Descuento_ = Descuento;
        }

        //Constructor clase Reserva (con Bono)
        public Reserva(String IdUsuario, Date Fecha, int Duracion, float Precio, boolean Descuento, String IdBono, int NumeroSesion) {
            IdUsuario_ = IdUsuario;
            Fecha_ = Fecha;
            Duracion_ = Duracion;
            Precio_ = Precio;
            Descuento_ = Descuento;

            IdBono_ = IdBono;
            NumeroSesion_ = NumeroSesion;
        }

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

        public String getIdBono() {
            return IdBono_;
        }

        public int getNumeroSesion()  {
            return NumeroSesion_;
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

        // Método abstracto toString(), para que cada subclase sobreescriba el método y que
        // así se muestre información específica de cada tipo de reserva.
        @Override
        public abstract String toString();

    }



/**
    >>> El Patrón "Factory" es un patrón de diseño que se utiliza para delegar la creación
    de objetos a una clase "fábrica", en lugar de crear instancias directamente en el código.

    
    Clase Reserva es de tipo abstracto (es decir, nunca se instanciará).

    Las clases ReservaInfantil, ReservaFamiliar, y ReservaAdultos serán subclases de Reserva.
    Cada clase debe estar en un fichero por separado.

    Y la clase ReservaFactory será la encargada de crear los distintos tipos de Reserva,
    según el tipo de reserva que se necesite instanciar.

*/