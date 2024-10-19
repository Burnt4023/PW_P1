package classes.Reserva_Classes;

import classes.Usuario;
import classes.Pista.TamanoPista;
import classes.Pista;



public class ReservaInfantil extends Reserva {

    private int NumeroNinos_;

    
    //Getters
    public int getNumeroNinos(){
        return NumeroNinos_;
    }

    //Setters
   public void setNumeroNinos(int NumeroNinos) {
        NumeroNinos_ = NumeroNinos;
   }

    // Constructor de una instancia de ReservaInfantil
    public ReservaInfantil(Usuario usuario, Pista pista, int NumeroNinos) {
        
        super(); // Llamada al constructor vacío de la superclase (Reserva).

        if(usuario.getEdad() < 18)  //si es menor de edad, no puede hacer una ReservaInfantil
        {
            throw new IllegalArgumentException("Solo los adultos pueden realizar una reserva infantil.");
        }

        if( pista.getTamanoPista() != TamanoPista.MINIBASKET )
        {
            throw new IllegalArgumentException("Solo se puede reservar MINIBASKET en una ReservaInfantil.");
        }

        this.NumeroNinos_ = NumeroNinos;
    }


    // @Override se utiliza para sobrescribir métodos de una clase padre.
    // Esto permite cambiar el comportamiento de un método heredado en una subclase.

    // No es posible sobrescribir (override) constructores ni atributos, pero sí invocar
    // constructores del padre, con super(), y "ocultar" atributos...


    // Método toString
    @Override
    public String toString(){
        String Text = "IdUsuario: " + getIdUsuario() + ", Fecha: " + getFecha() + ", Duracion: " + getDuracion() + ", Precio: " + getPrecio() + ", Descuento: " + getDescuento() + ", Nº de niños: " + getNumeroNinos();
        return Text;
    }

}


