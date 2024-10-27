package classes;

import classes.Pista.TamanoPista;
import java.util.Date;

public class Bono {

    // Variables de la clase.

    int IdBono; // Identificador del bono.
    TamanoPista TipoDeSesion; // Especifica que tipo de bono es.
    String DniUsuarioAsociado;
    int UsosRestantes;
    Date AltaDelBono; // Fecha que el bono se dio de alta.
    boolean Caducado;

    /**
     * Default constructor. 
    */
    public Bono() {}

    /**
     * Constructor con parametros. 
    */
    public Bono(int IdBono2, TamanoPista TipoDeSesion_,
                String DniUsuarioAsociado_) {

        IdBono = IdBono2;
        TipoDeSesion = TipoDeSesion_;
        DniUsuarioAsociado = DniUsuarioAsociado_;
        UsosRestantes = 5; // 5 Usos por bono.
        AltaDelBono = new Date(); // Fecha actual
        Caducado = false;
    }

    /**
     * Constructor con parametros completos.
    */
    public Bono(int IdBono2, TamanoPista TipoDeSesion_,
                String DniUsuarioAsociado_, int UsosRestantes_, Date AltaDelBono_, boolean Caducado_) {

        IdBono = IdBono2;
        TipoDeSesion = TipoDeSesion_;
        DniUsuarioAsociado = DniUsuarioAsociado_;
        UsosRestantes = UsosRestantes_;
        AltaDelBono = AltaDelBono_; // Fecha actual
        Caducado = Caducado_;
    }

    /**
     * Comprueba que el bono no tenga un año o mas, ya que estaria caducado.
     */
    private void comprobarCaducidad() {

        Date NowDate = new Date();

        long difference = NowDate.getTime() - AltaDelBono.getTime();

        int DiferenciaEnYears = (int) (difference / (1000L * 3600 * 24 * 365));

        if (DiferenciaEnYears >= 1) {
            Caducado = true;
        }
    }

    /**
     * Usa el bono. (Consume un uso).
     * @return true - Si se ha usado el bono.
     * @return false - Si no se ha usado el bono.
    */
    public Boolean usarBono() {

        // Comprobar fecha de caducidad.
        comprobarCaducidad();

        // Si no tiene mas usos.
        if (UsosRestantes <= 0) {
            return false;
        }

        // Si esta caducado.
        if (Caducado == false) {
            return false;
        } else {

            UsosRestantes -= 1;
        }

        return true;
    }

    public int getIdBono() {
        return IdBono;
    }

    public void setIdBono(int idBono) {
        this.IdBono = idBono;
    }

    // Getter y Setter para TipoDeSesion
    public TamanoPista getTipoDeSesion() {
        return TipoDeSesion;
    }

    public void setTipoDeSesion(TamanoPista tipoDeSesion) {
        this.TipoDeSesion = tipoDeSesion;
    }

    // Getter y Setter para UsuarioAsociado
    public String getDniUsuarioAsociado() {
        return DniUsuarioAsociado;
    }

    public void setUsuarioAsociado(String DniUsuarioAsociado) {
        this.DniUsuarioAsociado = DniUsuarioAsociado;
    }

    // Getter y Setter para UsosRestantes
    public int getUsosRestantes() {
        return UsosRestantes;
    }

    public void setUsosRestantes(int usosRestantes) {
        this.UsosRestantes = usosRestantes;
    }

    // Getter y Setter para AltaDelBono
    public Date getAltaDelBono() {
        return AltaDelBono;
    }

    public void setAltaDelBono(Date altaDelBono) {
        this.AltaDelBono = altaDelBono;
    }

    // Getter y Setter para Caducado
    public boolean isCaducado() {
        return Caducado;
    }

    public void setCaducado(boolean caducado) {
        this.Caducado = caducado;
    }
}
