package classes.Reserva_Classes;

import java.util.Date;

// Para gestión de archivos
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public abstract class Reserva {

        private String IdUsuario_;  // Usuario que realiza la reserva.
        private Date Fecha_;
        private int Duracion_;  // En minutos.
        private float Precio_;  // En euros.
        private boolean Descuento_;

        // Usado si se aplica bono
        private boolean EsBono_;
        private String IdBono_;
        private int NumeroSesion_;


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

        //Constructor clase Reserva con Bono
        public Reserva(String IdUsuario, Date Fecha, int Duracion, float Precio, boolean Descuento, boolean EsBono, String IdBono, int NumeroSesion) {
            IdUsuario_ = IdUsuario;
            Fecha_ = Fecha;
            Duracion_ = Duracion;
            Precio_ = Precio;
            Descuento_ = Descuento;

            EsBono_ = EsBono;
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

        public boolean getEsBono() {
            return EsBono_;
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

        public void setEsBono(boolean EsBono) {
            this.EsBono_ = EsBono;
        }

        public void setIdBono(String IdBono) {
            this.IdBono_ = IdBono;
        }

        public void setNumeroSesion(int NumeroSesion)  {
            this.NumeroSesion_ = NumeroSesion;
        }


        // Método para guardar la reserva en un archivo
        public void guardarEnArchivo() {
            String filePath = "data/reservas-list.txt"; // Ruta al archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // 'true' para añadir al final del archivo
                // Formato de la reserva en texto
                String ReservaData = String.format(
                    "%s,%s,%d,%s,%.2f,%.2f,%b,%s,%d",
                    IdUsuario_, Fecha_, Duracion_, Precio_, Descuento_,
                    EsBono_, IdBono_ != null ? IdBono_ : "N/A", NumeroSesion_
                );

                writer.write(ReservaData);
                writer.newLine();
                writer.write("%"); // Separador entre reservas
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }


        // Método abstracto toString(). Así cada subclase podrá sobreescribir el método
        // y que así se muestre información específica de cada tipo de reserva.
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