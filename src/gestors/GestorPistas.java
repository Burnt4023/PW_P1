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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

}
