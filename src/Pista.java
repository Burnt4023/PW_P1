import java.util.ArrayList;
import java.util.List;

public class Pista {
    
    private String nombrePista;
    private boolean estadoPista; // true = disponible, false = no disponible
    private boolean tipoPista; // true = interior, false = exterior
    private TamañoPista tamañoPista;
    private int maxJugadores;
    private List<Material> materiales;

    private enum TamañoPista {
        MINIBASKET,
        ADULTOS,
        TRES_VS_TRES
    }

    // Constructor vacío
    public Pista() {
        this.materiales = new ArrayList<>();
    }

    // Constructor parametrizado (sin la lista de materiales)
    public Pista(String nombrePista, boolean estadoPista, boolean tipoPista, TamañoPista tamañoPista, int maxJugadores) {
        this.nombrePista = nombrePista;
        this.estadoPista = estadoPista;
        this.tipoPista = tipoPista;
        this.tamañoPista = tamañoPista;
        this.maxJugadores = maxJugadores;
        this.materiales = new ArrayList<>();
    }

    // Getters y setters
    public String getNombrePista() {
        return nombrePista;
    }

    public void setNombrePista(String nombrePista) {
        this.nombrePista = nombrePista;
    }

    public boolean isEstadoPista() {
        return estadoPista;
    }

    public void setEstadoPista(boolean estadoPista) {
        this.estadoPista = estadoPista;
    }

    public boolean isTipoPista() {
        return tipoPista;
    }

    public void setTipoPista(boolean tipoPista) {
        this.tipoPista = tipoPista;
    }

    public TamañoPista getTamañoPista() {
        return tamañoPista;
    }

    public void setTamañoPista(TamañoPista tamañoPista) {
        this.tamañoPista = tamañoPista;
    }

    public int getMaxJugadores() {
        return maxJugadores;
    }

    public void setMaxJugadores(int maxJugadores) {
        this.maxJugadores = maxJugadores;
    }

    public List<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<Material> materiales) {
        this.materiales = materiales;
    }

    // Método toString
    @Override
    public String toString() {
        return "Pista{" +
                "nombrePista='" + nombrePista + '\'' +
                ", estadoPista=" + (estadoPista ? "Disponible" : "No disponible") +
                ", tipoPista=" + (tipoPista ? "Interior" : "Exterior") +
                ", tamañoPista=" + tamañoPista +
                ", maxJugadores=" + maxJugadores +
                ", materiales=" + materiales.size() +
                '}';
    }

    // Método consultarMaterialesDisponibles
    public List<Material> consultarMaterialesDisponibles() {
        List<Material> materialesDisponibles = new ArrayList<>();
        for (Material material : materiales) {
            if (material.getEstado() != Material.Estado.MALO && material.getEstado() != Material.Estado.RESERVADO) {
                materialesDisponibles.add(material);
            }
        }
        return materialesDisponibles;
    }

    // Método asociarMaterialAPista
    public boolean asociarMaterialAPista(Material material) {
        int pelotasCount = 0;
        int canastasCount = 0;
        int conosCount = 0;

        // Contar pelotas, canastas y conos en la pista
        for (Material mat : materiales) {
            if (mat.getTipo() == Material.Tipo.PELOTA) pelotasCount++;
            if (mat.getTipo() == Material.Tipo.CANASTA) canastasCount++;
            if (mat.getTipo() == Material.Tipo.CONO) conosCount++;
        }

        // Restricciones de cantidad
        if (material.getTipo() == Material.Tipo.PELOTA && pelotasCount >= 12) return false;
        if (material.getTipo() == Material.Tipo.CANASTA && canastasCount >= 2) return false;
        if (material.getTipo() == Material.Tipo.CONO && conosCount >= 20) return false;

        // Restricciones por tipo de pista
        if (!tipoPista) { // Si la pista es exterior (false), solo se pueden añadir materiales para exterior
            if (material.getTipo() == Material.Tipo.CANASTA || material.getTipo() == Material.Tipo.CONO) {
                materiales.add(material);
                return true;
            }
        } else { // Si la pista es interior (true), se pueden añadir todo tipo de materiales
            materiales.add(material);
            return true;
        }

        return false; // Si no cumple con ninguna de las condiciones anteriores
    }
}