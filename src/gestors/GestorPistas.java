package gestors;

import classes.*;
import classes.Material.Estado;
import classes.Material.Tipo;
import classes.Pista.TamanoPista;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorPistas {

    //* --- VARIABLES DE LA CLASE ---

    ArrayList<Pista> ListaPistas;
    ArrayList<Material> ListaMateriales;


    //* --- FUNCIONES DE LA CLASE PRIVADAS ---

    /**
     * Load ArrayList data from 'pistas-list.txt'. Located in '/data'.
     */
    private void loadData() {

        try {

            Pista PistaCargada = null;
            ArrayList<Material> materialesTemp = new ArrayList<>();

            String filename = "data/pistas-list.txt";

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String linea;

            while ((linea = reader.readLine()) != null) {

                //* LEE LA PRIMERA LINEA

                String[] campos = linea.split(",");

                if (campos.length == 5) {
                    
                    //* --- CARGA DE PISTAS ---

                    String NombrePista = campos[0];
                    boolean EstadoPista = Boolean.parseBoolean(campos[1]);
                    boolean TipoPista = Boolean.parseBoolean(campos[2]);
                    TamanoPista TamanoPistaObtained = TamanoPista.valueOf(campos[3]);
                    int MaxJugadoresPista = Integer.parseInt(campos[4]);
                    
                    // Crear la Pista con los materiales leídos
                    PistaCargada = new Pista(NombrePista, EstadoPista, TipoPista, TamanoPistaObtained, MaxJugadoresPista);
                    materialesTemp = new ArrayList<>();

                } else if (campos.length == 4) {
                    
                    //*  --- CARGA DE MATERIALES ---

                    int Id = Integer.parseInt(campos[0]);
                    Tipo Type = Tipo.valueOf(campos[1]);
                    boolean Use = Boolean.parseBoolean(campos[2]);
                    Estado State = Estado.valueOf(campos[3]);

                    // Crear material
                    Material MaterialCargado = new Material(Id, Type, Use, State);
                    materialesTemp.add(MaterialCargado);

                } else if (campos.length == 1 && campos[0].equals("%")) {

                    // Final de carga de pista con todos los materiales

                    if (PistaCargada != null) {
                        
                        PistaCargada.setMateriales(materialesTemp);  // Asignar los materiales a la pista
                        ListaPistas.add(PistaCargada);  // Agregar la pista a la lista
                    }

                } else {
                    System.err.println("[ERROR] No se han obtenido todos los campos al cargar las pistas.\n");
                    break;
                }

            }

            reader.close();

        } catch (Exception e) {
            System.err.println("[ERROR] No se han podido cargar las pistas correctamente.\n");
            e.printStackTrace();
        }
    }

    /**
     * Saves ArrayList data from 'pistas-list.txt'. Located in '/data'.
    */
    @SuppressWarnings("ConvertToTryWithResources")
    private void saveData() {
        try {
            
            String filename = "data/pistas-list.txt";
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false)); // false para sobreescribir el archivo

            for (int i = 0; i < ListaPistas.size(); i++) {

                
                
                Pista PistaAGuardar = ListaPistas.get(i);

                // Linea de registro de usuario
                String LineaAGuardar = PistaAGuardar.getNombrePista() + "," + PistaAGuardar.isEstadoPista() + ","
                                        + PistaAGuardar.isTipoPista() + "," + PistaAGuardar.getTamanoPista() + ","
                                        + PistaAGuardar.getMaxJugadores() + "\n";

                for (int j = 0; j < PistaAGuardar.getMateriales().size(); j++) {

                    String LineaMaterial = PistaAGuardar.getMateriales().get(j).getID()  + "," +
                                            PistaAGuardar.getMateriales().get(j).getTipo() + "," +
                                            PistaAGuardar.getMateriales().get(j).isUso() + "," +
                                            PistaAGuardar.getMateriales().get(j).getEstado() + "\n";

                    LineaAGuardar +=  LineaMaterial;
                }

                LineaAGuardar += "%" + "\n";

                writer.write(LineaAGuardar);  // Añadir el nuevo usuario en una nueva línea
            }

            writer.close();

            //System.out.println("[INFO] Pistas guardadas correctamente.\n");

        } catch (Exception e) {

			System.err.println("[ERROR] No se han podido guardar las pistas.\n");
            e.printStackTrace();
        }
    }

    /**
     * Load ArrayList data from 'materials-list.txt'. Located in '/data'.
     */
    @SuppressWarnings({"CallToPrintStackTrace", "ConvertToTryWithResources"})
    private void materialLoadData() {

        try {

            Material materialCargado;

            String filename = "data/materials-list.txt";

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String linea;

            while ((linea = reader.readLine()) != null) {

                //* LEE LA PRIMERA LINEA

                String[] campos = linea.split(",");

                if (campos.length == 4) {
                    
                    //* --- CARGA DE MATERIAL ---

                    int IdMaterial = Integer.parseInt(campos[0]);
                    Tipo TipoMaterial = Tipo.valueOf(campos[1]);
                    boolean UsoMaterial = Boolean.parseBoolean(campos[2]);
                    Estado EstadoMaterial = Estado.valueOf(campos[3]);

                    // Crear la Pista con los materiales leídos
                    materialCargado = new Material(IdMaterial, TipoMaterial, UsoMaterial, EstadoMaterial);

                    ListaMateriales.add(materialCargado); // Se añade a la lista de materiales.

                } else {
                    System.err.println("[ERROR] No se han obtenido todos los campos al cargar las pistas.\n");
                    break;
                }
            }

            reader.close();

            System.out.println("[INFO] Se han cargado todas las pistas correctamente.\n");

        } catch (IOException | NumberFormatException e) {
            System.err.println("[ERROR] No se han podido cargar las pistas correctamente.\n");
            e.printStackTrace();
        }
    }

    /**
     * Saves ArrayList data from 'materials-list.txt'. Located in '/data'.
    */
    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
    private void materialSaveData() {
        try {
            
            String filename = "data/materials-list.txt";
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false)); // false para sobreescribir el archivo

            for (int i = 0; i < ListaMateriales.size(); i++) {


                Material materialAGuardar = ListaMateriales.get(i);

                // Linea de registro de usuario
                String LineaAGuardar = materialAGuardar.getID() + "," + materialAGuardar.getTipo() + "," +
                                        materialAGuardar.isUso() + "," + materialAGuardar.getEstado() + "\n";

                writer.write(LineaAGuardar);  // Añadir el nuevo usuario en una nueva línea
            }

            writer.close();

            //System.out.println("[INFO] Pistas guardadas correctamente.\n");

        } catch (IOException e) {

			System.err.println("[ERROR] No se han podido guardar las pistas.\n");
            e.printStackTrace();
        }
    }

    //*  --- FUNCIONES DE LA CLASE PUBLICAS ---


    public GestorPistas() {

        ListaPistas = new ArrayList<>();
        ListaMateriales = new ArrayList<>();

        ListaMateriales.clear();
        ListaPistas.clear();

        loadData();
        materialLoadData();
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
    public boolean crearPista(String NombrePista, boolean EstadoPista, boolean TipoPista, TamanoPista Tam, int MaxJugadores) {

        try {
            // Crea una nueva pista
            Pista NuevaPista = new Pista(NombrePista, EstadoPista, TipoPista, Tam, MaxJugadores);

            // Anadir al listado
            ListaPistas.add(NuevaPista);

            // Guardar informacion
            saveData();

            return true;

        } catch (Exception e) {
            e.getCause();
        }

        return false;
    }

    /**
     * Añade al vector de pistas la pista pasada por referencia.
     * @param pista - Pista que se añadira al vector.
     * @return true - Si se añadio correctamente.
     * @return false - Si no se pudo añadir.
     */
    public boolean crearPista(Pista pista) {

        try {
            
            ListaPistas.add(pista);

            saveData();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Crea un material. ID se genera automaticamente.
     * @param Id
     * @param Type
     * @param Uso
     * @param State
     * @return true - Si lo creo.
     * @return false- Si no lo creo.
    */
    public boolean crearMaterial(Tipo Type, boolean Uso, Estado State) {

        try {
            int Id = ListaMateriales.size(); // Ajuste de ID

            Material NuevoMaterial = new Material(Id,Type, Uso, State);

            ListaMateriales.add(NuevoMaterial);

            //System.out.println("[DEBUG] Material añadido: " + NuevoMaterial.toString());

            materialSaveData();

            return true;
        } catch (Exception e) {
            e.getCause();
        }

        return false;
    }

    /**
     * Crea un material guardandolo en la lista.
     * @param material
     * @return true - Si lo creo.
     * @return false - Si no lo creo.
     */
    public boolean crearMaterial(Material material) {
    
        try {
            
            ListaMateriales.add(material);

            materialSaveData();

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Asocia un material con una pista.
     * @param MaterialToAssociate
     * @param PistaToAssociate
     * @return TRUE - Si se ha asociado correctamente.
     * @return FALSE - Si no se ha asociado.
    */
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AsociarMaterialAPistaDisponible(Material MaterialToAssociate, Pista PistaToAssociate) {

        try {
            // .asociarMaterialAPista() contiene las condiciones de asociacion.
            if (PistaToAssociate.asociarMaterialAPista(MaterialToAssociate) == true) {
                
                //System.out.println("[INFO] Se ha asociado el material a la pista correctamente.\n");

                saveData();
                materialSaveData();

                return true;
            } else {
                
                //System.out.println("[INFO] No se ha podido asociar el material a la pista indicada.\n");
                return false;
            }

        } catch (Exception e) {
            System.err.println("[ERROR] No se ha podido asocial el material a la pista.\n");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Imprime la lista de las pistas no disponibles. 
    */
    @SuppressWarnings("CallToPrintStackTrace")
    public void ListarPistasNoDisponibles() {

        try {
            int iterator = 1;
            //System.out.println("- LISTA DE PISTAS NO DISPONIBLES -\n");
            for (int i = 0; i < ListaPistas.size(); i++) {
                
                Pista aux = ListaPistas.get(i);
                
                if (aux.isEstadoPista() == false) {
                    System.out.println(iterator + ". Nombre: " + aux.getNombrePista() + ", Tipo: " + (aux.isTipoPista() ? "Interior" : "Exterior") + ", Estado: " +
                                        (aux.isEstadoPista() ? "Disponible" : "No disponible") + ", Tamano: " + aux.getTamanoPista() + ", Max Jugadores: " + aux.getMaxJugadores() + ", Num Materiales: " +
                                        aux.getMateriales().size() + "\n");

                    iterator++;
                }

                
            }
            
            //System.out.println("- FINAL DE LA LISTA -\n");

        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo listar las pistas no disponibles.\n");
            e.printStackTrace();
        }

    }

    /**
     * Lista las pistas libres que tengan al menos ese numero de jugadores.
     * @param NumJugadores
     * @param TipoPista TRUE = interior / FALSE = exterior.
    */
    @SuppressWarnings("CallToPrintStackTrace")
    public void ListarPistasLibres(int NumJugadores, boolean TipoPista) {

        try {
            int iterator = 1;
            //System.out.println("\n- LISTA DE PISTAS LIBRES / con al menos " + NumJugadores + " jugadores -\n");
            for (int i=0; i < ListaPistas.size(); i++) {
                
                Pista aux = ListaPistas.get(i);
                
                if (aux.isEstadoPista() == true && aux.isTipoPista() == TipoPista && aux.getMaxJugadores() >= NumJugadores) {
                    System.out.println(iterator + ". Nombre: " + aux.getNombrePista() + ", Tipo: " + (aux.isTipoPista() ? "Disponible" : "No disponible") + ", Estado: " +
                                        (aux.isEstadoPista() ? "Interior" : "Exterior") + ", Tamano: " + aux.getTamanoPista() + ", Max Jugadores: " + aux.getMaxJugadores() + ", Num Materiales: " +
                                        aux.getMateriales().size() + "\n");
                                        
                    iterator++;
                }

            }
            
            //System.out.println("- FINAL DE LA LISTA -\n");

        } catch (Exception e) {
            System.err.println("[ERROR] No se pudo listar las pistas libres.\n");
            e.printStackTrace();
        }
    }

    /**
     * Lista todas las pistas.
     */
    public void listarListaMateriales() {
        
        for (int i = 0; i < ListaMateriales.size(); i++) {
            System.out.println(i+1 + ". " + ListaMateriales.get(i).toString());
        }
    }

    /**
     * Lista todos los materiales.
     */
    public void listarListaPistas() {
        
        for (int i = 0; i < ListaPistas.size(); i++) {
            System.out.println(i+1 + ". " + ListaPistas.get(i).toString());
        }
    }

    /**
     * Obtiene una pista de la posicion indicada.
     * @param pos - Posicion de la pista.
     * @return La pista
     * @return null - Si no existe la pista.
     */
    public Pista getPista(int pos) {

        try {
            return ListaPistas.get(pos);
        } catch (Exception e) {
            System.err.println("[ERROR] No existe la pista en la posicion " + pos + ".\n");
        }

        return null;
    }

    /**
     * Obtiene un material de la posicion indicada.
     * @param pos - Posicion de la lista de materiales.
     * @return El material.
     * @return null - Si no existe el material.
     */
    public Material getMaterial(int pos) {

        try {
            return ListaMateriales.get(pos);
        } catch (Exception e) {
            System.err.println("[ERROR] No existe el material en la posicion " + pos + ".\n");
        }
    
        return null;
    }

}
