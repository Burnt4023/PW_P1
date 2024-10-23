package classes;

public class Material {
    private Integer ID;
    private Tipo Tipo;
    private boolean Uso; // true - Usable, false - No usable
    private Estado Estado;

    public enum Tipo {
        PELOTA,
        CANASTA,
        CONO
    }

    public enum Estado {
        DISPONIBLE,
        RESERVADO,
        MALO
    }

    // Constructores
    Material() {}

    /**
     * 
     * @param ID Identificador del material
     * @param Tipo Tipo de Material
     * @param Uso En donde se puede usar
     * @param Estado En qué estado se encuentra
     * 
     */
    public Material(Integer ID, Tipo Tipo, boolean Uso, Estado Estado) {
        this.ID = ID;
        this.Tipo = Tipo;
        this.Uso = Uso;
        this.Estado = Estado;
    }

    // Getters
    public Integer getID() {
        return ID;
    }

    public Tipo getTipo() {
        return Tipo;
    }

    public boolean isUso() {
        return Uso;
    }

    public Estado getEstado() {
        return Estado;
    }

    // Setters
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setTipo(Tipo Tipo) {
        this.Tipo = Tipo;
    }

    public void setUso(boolean Uso) {
        this.Uso = Uso;
    }

    public void setEstado(Estado Estado) {
        this.Estado = Estado;
    }

    /**
     * @return return Devuelve los datos del Material como cadena
     */
    @Override
    public String toString(){
        String Text = "ID: " + getID() + ", Tipo: " + getTipo() + ", Uso: " + isUso() + ", Estado: " + getEstado();
        return Text;
    }
}