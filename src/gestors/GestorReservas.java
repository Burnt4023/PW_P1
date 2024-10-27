package gestors;

import classes.*;
import classes.Reserva_Classes.Reserva;
import classes.Reserva_Classes.Reserva.TipoReserva;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import classes.Reserva_Classes.ReservaFactory;

/**
 * Clase del gestor de las reservas. 
*/
public class GestorReservas {
    
    //* --- VARIABLES DE LA CLASE ---

    ArrayList<Reserva> ListaReservas;
    ArrayList<Bono> ListaBonos;

    //* --- FUNCIONES DE LA CLASE ---

    /**
     * Guardar el bono en el archivo 'bonos-list.txt', situado en '/data'.
     */
    private void saveBonoData() {
        
        try {

            String ListaBonosString = "data/bonos-list.txt";
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(ListaBonosString, false)); // false para sobreescribir el archivo

            for (int i = 0; i < ListaBonos.size(); i++) {

                
                
                Bono bono = ListaBonos.get(i);

                // Linea de registro de usuario
                String nuevoBono = bono.getIdBono() + "," + bono.getTipoDeSesion() + "," + bono.getUsuarioAsociado().getDni() + "," +
                                    bono.getUsosRestantes() + "," + bono.getAltaDelBono() + "," +
                                    bono.isCaducado() + "\n";

                writer.write(nuevoBono);  // Añadir el nuevo usuario en una nueva línea
            }

            writer.close();

            //System.out.println("[INFO] Usuarios guardados correctamente.\n");

        } catch (Exception e) {

			System.err.println("[ERROR] No se han podido guardar los usuarios.\n");
            e.printStackTrace();
        }
    }

    /**
     * Cargar el bono desde el archivo 'bonos-list.txt', situado en '/data'.
     */
    private void loadBonoData() {
        try {

            String ListaBonosString = "data/bonos-list.txt";

            BufferedReader reader = new BufferedReader(new FileReader(ListaBonosString));
            String linea;

            // Leer línea por línea el archivo
            while ((linea = reader.readLine()) != null) {

                // Dividir la línea en campos separados por espacios (" ")
                String[] campos = linea.split(",");

                // Verificar que la línea tiene los campos necesarios
                if (campos.length == 6) {
                    String IdBono  = campos[0];
                    Pista.TamanoPista TipoDeSesion = Pista.TamanoPista.valueOf(campos[1]);
                    String DniUsuario = campos[2];
                    int UsosRestantes = Integer.parseInt(campos[3]);
                    Date AltaDelBono = convertString2Date(campos[4]);
                    boolean Caducado = Boolean.parseBoolean(campos[5]);

                    // Creamos el usuario guardado
                    Bono bono = new Bono(IdBono, TipoDeSesion, new Usuario(DniUsuario), UsosRestantes, AltaDelBono, Caducado);

                    // Anadir al vector
                    ListaBonos.add(bono);

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
     * Carga los datos del fichero de gestion de reservas. 
     */
    private void loadData() {

    }

    /**
     * Carga los datos del fichero de gestion de reservas.
     */
    private void saveData() {

    }

    /**
     * Default constructor. 
    */
    public GestorReservas() {}

    /**
     * Parameters contrcutor.
    */
    public GestorReservas(Usuario user_, Reserva reserve_) {

        ListaReservas = new ArrayList<>();
        ListaBonos = new ArrayList<>();

        // Limpia los arrays.
        ListaReservas.clear();
        ListaBonos.clear();

        //todo - Load Data.
    }
    

    public boolean hacerReservaIndividual(TipoReserva tipoReserva, String DniUsuario, Pista PistaAReservar, int Minutos, int precio) {
        
        if (tipoReserva == TipoReserva.ADULTOS) {

            Reserva reserva = ReservaFactory.crearReserva(tipoReserva.toString(), new Usuario(DniUsuario), PistaAReservar, Minutos, 0, false, false, null, 0);W

        } else if (tipoReserva == TipoReserva.FAMILIAR) {

        } else if (tipoReserva == TipoReserva.INFANTIL) {

        }

        return false;
    }

    public boolean hacerReservaConBono(Bono BonoUsuario, Usuario user, Pista PistaAReservar) {

        return true;
    }

    /**
     * Obtiene el precio de las horas de una reserva.
     * @param minutes Minutos que se reservara una pista.
     * @return Cantidad en EUR que cuesta la reserva.
     * @return -1 Si las cantidad de minutos no es la esperada.
     * @warning Las cantidades de minutos aceptadas son: 60, 90 y 120.
     */
    public int getPrecioReserva(int minutes) {

        if (minutes == 60) {
            return 20;
        } else if (minutes == 90) {
            return 30;
        } else if (minutes == 120) {
            return 40;
        }

        return -1;
    }

    /**
     * Comprueba si una pista puede modificarse. Una pista puede ser modificada 24h antes de que empiece.
     * @param PistaConcreta
     * @return true - Si la pista puede ser modificada.
     * @return false - Si la pista no puede ser modificada.
    */
    protected boolean checkIfPistaCanBeModified(Pista PistaConcreta) {

        return true;
    }

    /**
     * To comment. 
    */
    public void verReservasFuturas() {


    }

    /**
     * To comment. 
    */
    public void verReserva(Date day) {

    }

    /**
     * To comment. 
    */
    public void verreserva(Pista PistaConcreta) {

    }

    /**
     * To comment. 
    */
    public void verReserva(Date day, Pista PistaConcreta) {

    }
}
