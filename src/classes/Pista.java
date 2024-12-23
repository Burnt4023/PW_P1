package classes;

import java.util.ArrayList;
import java.util.List;

public class Pista {
    
    private String nombrePista;
    private boolean estadoPista; // true = disponible, false = no disponible
    private boolean tipoPista; // true = interior, false = exterior
    private TamanoPista tamanoPista;
    private int maxJugadores;
    private List<Material> materiales;

    public enum TamanoPista {
        MINIBASKET,
        ADULTOS,
        TRES_VS_TRES;
    }

    // Constructor vacío
    public Pista() {
        this.materiales = new ArrayList<>();
    }

    // Constructor parametrizado (sin la lista de materiales)
    /**
     * 
     * @param nombrePista Nombre de la Pista
     * @param estadoPista Disponible (1) o Ocupado (0)
     * @param tipoPista Interior (1) o Exterior (0)
     * @param tamanoPista Tamaño de la Pista
     * @param maxJugadores Cantidad máxima de jugadores simultaneos
     */
    public Pista(String nombrePista, boolean estadoPista, boolean tipoPista, TamanoPista tamanoPista, int maxJugadores) {
        this.nombrePista = nombrePista;
        this.estadoPista = estadoPista;
        this.tipoPista = tipoPista;
        this.tamanoPista = tamanoPista;
        this.maxJugadores = maxJugadores;
        this.materiales = new ArrayList<>();
    }

    //Constructor con lista de materiales
    /**
     * 
     * @param nombrePista
     * @param estadoPista
     * @param tipoPista
     * @param tamañoPista
     * @param maxJugadores
     * @param material
     */
    public Pista(String nombrePista, boolean estadoPista, boolean tipoPista, TamanoPista tamanoPista, int maxJugadores, List<Material> material){
        this.nombrePista = nombrePista;
        this.estadoPista = estadoPista;
        this.tipoPista = tipoPista;
        this.tamanoPista = tamanoPista;
        this.maxJugadores = maxJugadores;
        this.materiales = material;
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

    public TamanoPista getTamanoPista() {
        return tamanoPista;
    }

    public void setTamanoPista(TamanoPista tamanoPista) {
        this.tamanoPista = tamanoPista;
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
    /**
     * @return Devuelve los datos de la pista como cadena
     */
    @Override
    public String toString() {
        return  "Nombre: '" + nombrePista + '\'' +
                ", Estado: " + (estadoPista ? "Disponible" : "No disponible") +
                ", Tipo: " + (tipoPista ? "Interior" : "Exterior") +
                ", Tamano: " + tamanoPista +
                ", MaxJugadores: " + maxJugadores +
                ", NumMateriales: " + materiales.size();
    }

    // Método consultarMaterialesDisponibles
    /**
     * @return Devuelve una lista con todos los materiales que estén disponibles
     */
    public List<Material> consultarMaterialesDisponibles() {
        List<Material> materialesDisponibles = new ArrayList<>();
        for (Material material : materiales) {
            if (material.getEstado() == Material.Estado.DISPONIBLE) {
                materialesDisponibles.add(material);
            }
        }
        return materialesDisponibles;
    }

    // Método asociarMaterialAPista

    /**
     * 
     * @brief Cuenta el numero de materiales de cada tipo en la lista y, si hay espacio y el tipo de pista es adecuado, lo añade
     * @param material El material a añadir
     * @return True si se añade el material, false si no
     */
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