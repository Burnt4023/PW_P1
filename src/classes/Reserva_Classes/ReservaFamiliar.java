package classes.Reserva_Classes;

import classes.Usuario;
import classes.Pista.TamanoPista;
import classes.Pista;



public class ReservaFamiliar extends Reserva {

    private int NumeroNinos_;
    private int NumeroAdultos_; 

    
    //Getters
    public int getNumeroNinos(){
        return NumeroNinos_;
    }
    public int getNumeroAdultos(){
        return NumeroAdultos_;
    }

    //Setters
    public void setNumeroNinos(int NumeroNinos){
        NumeroNinos_ = NumeroNinos;
    }
    public void setNumeroAdultos(int NumeroAdultos){
        NumeroAdultos_ = NumeroAdultos;
    }


    // Constructor de una instancia de ReservaFamiliar Normal (sin bono).
    public ReservaFamiliar(Usuario usuario, Pista pista, int NumeroNinos, int NumeroAdultos) {
        super();

        if(usuario.getEdad() < 18)  //Si es menor de edad, no puede hacer una ReservaFamiliar
        {
            throw new IllegalArgumentException("Solo los adultos pueden realizar una reserva familiar.");
        }

        if(NumeroAdultos < 1)   // Como mínimo debe estar presente 1 adulto, independientemente del que haya hecho la reserva.
        {
            throw new IllegalArgumentException("Como mínimo debe estar presente 1 adulto.");
        }

        if((pista.getTamanoPista() != TamanoPista.MINIBASKET) && (pista.getTamanoPista() != TamanoPista.TRES_VS_TRES))
        {
            throw new IllegalArgumentException("Solo se puede reservar MINIBASKET o TRES_VS_TRES en una ReservaFamiliar.");
        }

        //Si se cumplen todas las condiciones anteriores, se crea la instancia de ReservaFamiliar.
        NumeroNinos_ = NumeroNinos;
        NumeroAdultos_ = NumeroAdultos;
    }


    //Método toString
    @Override
    public String toString(){
        if(getEsBono() == false)    // Si EsBono es false, imprimo todos los campos menos los de bono.
        {
            String Text = "IdUsuario: " + getIdUsuario() + ", Fecha: " + getFecha() + ", Duracion: " + getDuracion() + ", Precio: " + getPrecio() + ", Descuento: " + getDescuento() + ", Es Bono: " + getEsBono() + ", Nº de Niños: " + getNumeroNinos() + ", Nº de Adultos: " + getNumeroAdultos();
            return Text;
        }
        else{
            String Text = "IdUsuario: " + getIdUsuario() + ", Fecha: " + getFecha() + ", Duracion: " + getDuracion() + ", Precio: " + getPrecio() + ", Descuento: " + getDescuento() +"Es Bono: " + getEsBono() + ", Id del Bono: " + getIdBono() + ", Numero de Sesión: " + getNumeroSesion() + ", Nº de Niños: " + getNumeroNinos() + ", Nº de Adultos: " + getNumeroAdultos();
            return Text;
        }
    }


}


    //Java es muy orientado a objetos. No puedo tener funciones "libres" como en C++,
    //salvo en clases utilitarias estáticas... Todo debe estar dentro de una clase.
