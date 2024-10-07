public class Material {
    private Integer ID_;
    private Tipo Tipo_;
    private boolean Uso_;
    private Estado Estado_;

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

    Material(Integer ID, Tipo Tipo, boolean Uso, Estado Estado) {
        this.ID_ = ID;
        this.Tipo_ = Tipo;
        this.Uso_ = Uso;
        this.Estado_ = Estado;
    }

    // Getters
    public Integer getID() {
        return ID_;
    }

    public Tipo getTipo() {
        return Tipo_;
    }

    public boolean isUso() {
        return Uso_;
    }

    public Estado getEstado() {
        return Estado_;
    }

    // Setters
    public void setID(Integer ID) {
        this.ID_ = ID;
    }

    public void setTipo(Tipo Tipo) {
        this.Tipo_ = Tipo;
    }

    public void setUso(boolean Uso) {
        this.Uso_ = Uso;
    }

    public void setEstado(Estado Estado) {
        this.Estado_ = Estado;
    }
    public String toString(){
        String Text = "ID: " + getID() + ", Tipo: " + getTipo() + ", Uso: " + isUso() + ", Estado: " + getEstado();
        return Text;
    }
}