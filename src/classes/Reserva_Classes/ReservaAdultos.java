package classes.Reserva_Classes;

import classes.Usuario;



public class ReservaAdultos extends Reserva {

    private int NumeroAdultos_; 


    //Getters
    public int getNumeroAdultos(){
        return NumeroAdultos_;
    }

    //Setters
    public void setNumeroAdultos(int NumeroAdultos){
        NumeroAdultos_ = NumeroAdultos;
    }

    // Constructor de una instancia de ReservaAdultos
    public ReservaAdultos(Usuario usuario) {
        super();

        if(usuario.getEdad() < 18)  //Si es menor de edad, no puede hacer una ReservaAdultos
        {
            throw new IllegalArgumentException("Solo los adultos pueden realizar una reserva familiar.");
        }

        //Si se cumplen todas las condiciones anteriores, se crea la instancia de ReservaFamiliar.
        this.NumeroAdultos_ = 1;    // Se considera al 1er adulto, que ha realizado la reserva.
    }

    //Método toString
    @Override
    public String toString(){
        if(getEsBono() == false)    // Si EsBono es false, imprimo todos los campos menos los de bono.
        {
            String Text = "IdUsuario: " + getIdUsuario() + ", Fecha: " + getFecha() + ", Duracion: " + getDuracion() + ", Precio: " + getPrecio() + ", Descuento: " + getDescuento() + ", Es Bono: " + getEsBono() + ", Nº de Adultos: " + getNumeroAdultos();
            return Text;
        }
        else{
            String Text = "IdUsuario: " + getIdUsuario() + ", Fecha: " + getFecha() + ", Duracion: " + getDuracion() + ", Precio: " + getPrecio() + ", Descuento: " + getDescuento() +"Es Bono: " + getEsBono() + ", Id del Bono: " + getIdBono() + ", Numero de Sesión: " + getNumeroSesion() + ", Nº de Adultos: " + getNumeroAdultos();
            return Text;
        }
    }

}