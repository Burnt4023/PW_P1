package gestors;

import classes.*;
import classes.Material.Estado;
import classes.Material.Tipo;
import classes.Pista.TamanoPista;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class GestorPistas {

    //* --- VARIABLES DE LA CLASE ---

    ArrayList<Pista> ListaPistas;


    //* --- FUNCIONES DE LA CLASE PRIVADAS ---

    /**
     * Load ArrayList data from 'pistas-list.txt'. Located in '/data'.
     */
    private void loadData() {

        try {

            ListaPistas.clear();

            String filename = "data/pistas-list.txt";

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String linea;

            // Leer línea por línea el archivo
            while ((linea = reader.readLine()) != null) {

                // Dividir la línea en campos separados por comas
                String[] campos = linea.split(",");

                // Verificar que la línea tiene al menos los 5 campos esperados antes de procesar los materiales
                if (campos.length >= 5) {

                    String NombrePista = campos[0].trim();
                    boolean StatePista = Boolean.parseBoolean(campos[1].trim());
                    boolean TypePista = Boolean.parseBoolean(campos[2].trim());
                    TamanoPista TamPista = TamanoPista.valueOf(campos[3].trim().toUpperCase());
                    int MaxJugadores = Integer.parseInt(campos[4].trim());

                    //* --- LECTURA DE LOS MATERIALES ---

                    ArrayList<Material> Materials = new ArrayList<>();

                    // Extraer los materiales si los hay (se espera que el campo sea un arreglo vacío "[]" o con detalles de materiales).
                    if (campos.length == 6) {
                        String materialesString = campos[5].trim();

                        // Comprobar si hay materiales (no es "[]")
                        if (!materialesString.equals("[]")) {
                            
                            // Quitar los corchetes que delimitan los materiales
                            materialesString = materialesString.substring(1, materialesString.length() - 1);
                            String[] materialesArray = materialesString.split("ID: ");  // Dividir los materiales por ID

                            // Procesar cada material
                            for (String materialInfo : materialesArray) {
                                if (!materialInfo.isBlank()) {
                                    // Dividir cada atributo del material
                                    String[] atributosMaterial = materialInfo.split(",");

                                    int idMaterial = Integer.parseInt(atributosMaterial[0].trim());
                                    Material.Tipo tipoMaterial = Material.Tipo.valueOf(atributosMaterial[1].split(":")[1].trim());
                                    boolean usoMaterial = Boolean.parseBoolean(atributosMaterial[2].split(":")[1].trim());
                                    Material.Estado estadoMaterial = Material.Estado.valueOf(atributosMaterial[3].split(":")[1].trim());

                                    // Crear el objeto Material
                                    Material material = new Material(idMaterial, tipoMaterial, usoMaterial, estadoMaterial);
                                    Materials.add(material);  // Añadir a la lista
                                }
                            }
                        }
                    }

                    // Crear la Pista con los materiales leídos
                    Pista PistaCargada = new Pista(NombrePista, StatePista, TypePista, TamPista, MaxJugadores, Materials);

                    // Añadir la pista a la lista
                    ListaPistas.add(PistaCargada);

                } else {
                    System.err.println("[ERROR] No se han obtenido todos los campos para la pista.\n");
                    break;
                }
            }

            reader.close();

            System.out.println("[INFO] Se han cargado todas las pistas correctamente.\n");

        } catch (Exception e) {
            System.err.println("[ERROR] No se han podido cargar las pistas correctamente.\n");
            e.printStackTrace();
        }
    }


    /**
     * Saves ArrayList data from 'pistas-list.txt'. Located in '/data'.
    */
    private void saveData() {
        try {
            
            String filename = "data/pistas-list.txt";
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false)); // false para sobreescribir el archivo

            for (int i = 0; i < ListaPistas.size(); i++) {

                
                
                Pista PistaAGuardar = ListaPistas.get(i);

                // Linea de registro de usuario
                String LineaAGuardar = PistaAGuardar.getNombrePista() + "," + PistaAGuardar.isEstadoPista() + ","
                                        + PistaAGuardar.isTipoPista() + "," + PistaAGuardar.getTamanoPista() + ","
                                        + PistaAGuardar.getMaxJugadores() + "," + PistaAGuardar.getMateriales() + "\n";

                writer.write(LineaAGuardar);  // Añadir el nuevo usuario en una nueva línea
            }

            writer.close();

            System.out.println("[INFO] Pistas guardadas correctamente.\n");

        } catch (Exception e) {

			System.err.println("[ERROR] No se han podido guardar las pistas.\n");
            e.printStackTrace();
        }
    }


    //*  --- FUNCIONES DE LA CLASE PUBLICAS ---


    public GestorPistas() {

        ListaPistas = new ArrayList<>();

        ListaPistas.clear();

        loadData();
    }

    /**
     * Crea una Pista y la guarda el la lista de pistas.
     * @param NombrePista
     * @param EstadoPista TRUE = disponible / FALSE = no disponible.
     * @param TipoPista TRUE = interior / FALSE = exterior.
     * @param Tam
     * @param MaxJugadores
     * @param Materiales
     * @return Pista creada.
    */
    public Pista crearPista(String NombrePista, boolean EstadoPista, boolean TipoPista, TamanoPista Tam, int MaxJugadores) {

        // Crea una nueva pista
        Pista NuevaPista = new Pista(NombrePista, EstadoPista, TipoPista, Tam, MaxJugadores);

        // Anadir al listado
        ListaPistas.add(NuevaPista);

        // Guardar informacion
        saveData();

        return NuevaPista;
    }

    /**
     * Crea un material.
     * @param Id
     * @param Type
     * @param Uso
     * @param State
     * @return Material Creado.
    */
    public Material crearMaterial(int Id, Tipo Type, boolean Uso, Estado State) {

        Material NuevoMaterial = new Material(Id, Type, Uso, State);

        return NuevoMaterial;
    }

    /**
     * Asocia un material con una pista.
     * @param MaterialToAssociate
     * @param PistaToAssociate
     * @return TRUE - Si se ha asociado correctamente.
     * @return FALSE - Si no se ha asociado.
    */
    public boolean AsociarMaterialAPistaDisponible(Material MaterialToAssociate, Pista PistaToAssociate) {

        try {
            // .asociarMaterialAPista() contiene las condiciones de asociacion.
            if (PistaToAssociate.asociarMaterialAPista(MaterialToAssociate) == true) {
                
                System.out.println("[INFO] Se ha asociado el material a la pista correctamente.\n");

                saveData();

                return true;
            } else {
                
                System.out.println("[INFO] No se ha podido asociar el material a la pista indicada.\n");
                return false;
            }

        } catch (Exception e) {
            System.err.println("[ERROR] No se ha podido asocial el material a la pista.\n");
            e.getCause();
        }

        return false;
    }

    /**
     * Imprime la lista de las pistas no disponibles. 
    */
    public void ListarPistasNoDisponibles() {

        try {
            int iterator = 1;
            System.out.println("- LISTA DE PISTAS NO DISPONIBLES -\n");
            for (int i = 0; i < ListaPistas.size(); i++) {
                
                Pista aux = ListaPistas.get(i);
                
                if (aux.isEstadoPista() == false) {
                    System.out.println(iterator + ". Nombre: " + aux.getNombrePista() + ", Tipo: " + (aux.isTipoPista() ? "Interior" : "Exterior") + ", Estado: " +
                                        (aux.isEstadoPista() ? "Disponible" : "No disponible") + ", Tamano: " + aux.getTamanoPista() + ", Max Jugadores: " + aux.getMaxJugadores() + ", Num Materiales: " +
                                        aux.getMateriales().size() + "\n");

                    iterator++;
                }

                
            }
            
            System.out.println("- FINAL DE LA LISTA -\n");

        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo listar las pistas no disponibles.\n");
            e.getCause();
        }

    }

    /**
     * Lista las pistas libres que tengan al menos ese numero de jugadores.
     * @param NumJugadores
     * @param TipoPista TRUE = interior / FALSE = exterior.
    */
    public void ListarPistasLibres(int NumJugadores, boolean TipoPista) {

        try {
            int iterator = 1;
            System.out.println("- LISTA DE PISTAS LIBRES / con al menos " + NumJugadores + " jugadores -\n");
            for (int i=0; i < ListaPistas.size(); i++) {
                
                Pista aux = ListaPistas.get(i);
                
                if (aux.isEstadoPista() == true && aux.isTipoPista() == TipoPista && aux.getMaxJugadores() >= NumJugadores) {
                    System.out.println(iterator + ". Nombre: " + aux.getNombrePista() + ", Tipo: " + (aux.isTipoPista() ? "Disponible" : "No disponible") + ", Estado: " +
                                        (aux.isEstadoPista() ? "Interior" : "Exterior") + ", Tamano: " + aux.getTamanoPista() + ", Max Jugadores: " + aux.getMaxJugadores() + ", Num Materiales: " +
                                        aux.getMateriales().size() + "\n");
                                        
                    iterator++;
                }

            }
            
            System.out.println("- FINAL DE LA LISTA -\n");

        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo listar las pistas libres.\n");
            e.getCause();
        }
    }

}
