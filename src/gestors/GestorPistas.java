package gestors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import classes.*;
import classes.Material.*;
import classes.Pista.*;

public class GestorPistas {

    //* --- VARIABLES DE LA CLASE ---

    List<Pista> ListaPistas;
    List<Material> ListaMaterial;


    //* --- FUNCIONES DE LA CLASE PRIVADAS ---

    /**
     * Load ArrayList data from 'users-list.txt'. Located in '/data'.
    */
    private void loadData() {

        try {

            usuarios.clear();

            String ListaUsuarios = "data/users-list.txt";

            BufferedReader reader = new BufferedReader(new FileReader(ListaUsuarios));
            String linea;

            // Leer línea por línea el archivo
            while ((linea = reader.readLine()) != null) {

                // Dividir la línea en campos separados por espacios (" ")
                String[] campos = linea.split(",");

                // Verificar que la línea tiene los campos necesarios
                if (campos.length == 4) {
                    String NombreUser = campos[0];
                    String ApellidosUser = campos[1];
                    String DniUser = campos[2];
                    int EdadUser = Integer.parseInt(campos[3]); // Convierte a INT

                    // Creamos el usuario guardado
                    Usuario UsuarioCargado = new Usuario(NombreUser, ApellidosUser, DniUser, EdadUser);

                    // Anadir al vector
                    usuarios.add(UsuarioCargado);

                } else {

                    System.err.println("[ERROR] No se han obtenido todos los campos.\n");
                    break;
                }
            }

            reader.close();

            System.out.println("[INFO] Se han cargado todos los usuarios correctamente.\n");

        } catch (Exception e) {
            
            System.err.println("[ERROR] No se han podido cargar los usuarios correctamente.\n");
            e.printStackTrace();
        }
    }

    /**
     * Saves ArrayList data from 'users-list.txt'. Located in '/data'.
    */
    private void saveData() {
        try {
            
            String ListaUsuarios = "data/users-list.txt";
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(ListaUsuarios, false)); // false para sobreescribir el archivo

            for (int i = 0; i < usuarios.size(); i++) {

                
                
                Usuario UsuarioARegistrar = usuarios.get(i);

                // Linea de registro de usuario
                String NuevoUsuario = UsuarioARegistrar.getNombre() + "," + UsuarioARegistrar.getApellidos() + "," + UsuarioARegistrar.getDni() + "," + UsuarioARegistrar.getEdad() + "\n";

                writer.write(NuevoUsuario);  // Añadir el nuevo usuario en una nueva línea
            }

            writer.close();

            System.out.println("[INFO] Usuarios guardados correctamente.\n");

        } catch (Exception e) {

			System.err.println("[ERROR] No se han podido guardar los usuarios.\n");
            e.printStackTrace();
        }
    }



    //*  --- FUNCIONES DE LA CLASE PUBLICAS ---
    /**
     * Crea una Pista.
     * @param NombrePista
     * @param EstadoPista TRUE = disponible / FALSE = no disponible.
     * @param TipoPista TRUE = interior / FALSE = exterior.
     * @param Tam
     * @param MaxJugadores
     * @param Materiales
     * @return Pista creada.
    */
    public Pista crearPista(String NombrePista, boolean EstadoPista, boolean TipoPista, TamanoPista Tam, int MaxJugadores, List<Material> Materiales) {

        // Crea una nueva pista
        Pista NuevaPista = new Pista(NombrePista, EstadoPista, TipoPista, Tam, NumJugadores, Materiales);

        //todo GUARDAR PISTA EN LISTA DE PISTAS

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

        //todo GUARDAR MATERIAL EN LA LISTA

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

        

    }

    /**
     * Imprime la lista de las pistas no disponibles. 
    */
    public void ListarPistasNoDisponibles() {

    }

    /**
     * Lista las pistas libres que tengan al menos ese numero de jugadores.
     * @param NumJugadores
     * @param TipoPista TRUE = interior / FALSE = exterior.
    */
    public void ListarPistasLibres(int NumJugadores, boolean TipoPista) {

    }


}
