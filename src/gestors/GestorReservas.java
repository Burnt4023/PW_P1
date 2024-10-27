package gestors;

import classes.*;
import classes.Pista.TamanoPista;
import classes.Reserva_Classes.Reserva;
import classes.Reserva_Classes.Reserva.TipoReserva;
import classes.Reserva_Classes.ReservaFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

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
                String nuevoBono = bono.getIdBono() + "," + bono.getTipoDeSesion() + "," + bono.getDniUsuarioAsociado() + "," +
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
                if (campos.length == 7) {
                    int IdBono  = Integer.parseInt(campos[0]);
                    Pista.TamanoPista TipoDeSesion = Pista.TamanoPista.valueOf(campos[1]);
                    String DniUsuario = campos[2];
                    int UsosRestantes = Integer.parseInt(campos[3]);
                    Date AltaDelBono = Usuario.convertString2Date(campos[5]);
                    boolean Caducado = Boolean.parseBoolean(campos[4]);
                    int NumeroSesion = Integer.parseInt(campos[6]);

                    // Creamos el bono guardado
                    Bono bono = new Bono(IdBono, TipoDeSesion, DniUsuario, UsosRestantes, AltaDelBono, Caducado, NumeroSesion);

                    // Anadir al vector
                    ListaBonos.add(bono);

                } else {

                    System.err.println("[ERROR] No se han obtenido todos los campos.\n");
                    break;
                }
            }

            reader.close();

            //System.out.println("[INFO] Se han cargado todos los usuarios correctamente.\n");

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
    public GestorReservas() {

        ListaReservas = new ArrayList<>();
        ListaBonos = new ArrayList<>();

        // Limpia los arrays.
        ListaReservas.clear();
        ListaBonos.clear();

        //todo - Load Data.
        loadBonoData();
    }
    

    /**
     * Crea un bono.
     */
    public boolean makeNuevoBono(String DniUsuario, TamanoPista tamPista) {

        try {

            Bono nuevoBono = new Bono(ListaBonos.size(), tamPista, DniUsuario);

            ListaBonos.add(nuevoBono);

            saveBonoData();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Lista todos los bonos.
     */
    public void listarBonos() {

        for (int i = 0; i < ListaBonos.size(); i++) {

            Bono aux = ListaBonos.get(i);

            System.out.println(i+1 + ". UsuarioAsociado: " + aux.getDniUsuarioAsociado() + ", TipoBono: " + aux.getTipoDeSesion() + ", UsosRestantes: " + aux.getUsosRestantes() + ", Caducado: " + aux.isCaducado() + ", AltaDelBono: " + aux.getAltaDelBono() + "\n");
        }
    }



    public boolean hacerReservaIndividual(TipoReserva tipoReserva, Usuario Usuario, Pista PistaAReservar, int Minutos, int precio) {
        
        if (tipoReserva == TipoReserva.ADULTOS) {
            // Se crea una instancia de reserva     //new Date() es la fecha y hora actuales
            // calcularDescuento(dniUsuario) Método que calcule el descuento según la antigüedad         
            Reserva reserva = ReservaFactory.crearReserva(tipoReserva, Usuario, PistaAReservar, 0, 5);

            if (reserva == null) {
                // Lógica para agregar la reserva a la base de datos o al sistema de reservas
                // ... 
                return true;
            }


        } else if (tipoReserva == TipoReserva.FAMILIAR) {

            Reserva reserva = ReservaFactory.crearReserva(tipoReserva, Usuario, PistaAReservar, 0, 5);

            if (reserva == null) {
                // Lógica para agregar la reserva a la base de datos o al sistema de reservas
                // ... 
                return true;
            }


        } else if (tipoReserva == TipoReserva.INFANTIL) {
            Reserva reserva = ReservaFactory.crearReserva(tipoReserva, Usuario, PistaAReservar, 0, 5);

            if (reserva == null) {
                // Lógica para agregar la reserva a la base de datos o al sistema de reservas
                // ... 
                return true;
            }
        }

        return false;
    }



    public boolean hacerReservaConBono(TipoReserva tipoReserva, Usuario Usuario, Pista PistaAReservar, int Minutos, int precio, Bono BonoUsuario, Usuario user) {

        if (tipoReserva == TipoReserva.ADULTOS) {
        // Se crea una instancia de reserva con bono
       
        Reserva reserva = ReservaFactory.crearReservaBono(tipoReserva, Usuario, PistaAReservar, 0, 5, true, BonoUsuario.getIdBono(), BonoUsuario.getNumeroSesion());

        if (reserva == null) {
            // Lógica para agregar la reserva a la base de datos o al sistema de reservas
            // ... 
            return true;
        }


        } else if (tipoReserva == TipoReserva.FAMILIAR) {

            Reserva reserva = ReservaFactory.crearReservaBono(tipoReserva, Usuario, PistaAReservar, 0, 5, true, BonoUsuario.getIdBono(), BonoUsuario.getNumeroSesion());

            if (reserva == null) {
                // Lógica para agregar la reserva a la base de datos o al sistema de reservas
                // ... 
                return true;
            }

            
        } else if (tipoReserva == TipoReserva.INFANTIL) {
            Reserva reserva = ReservaFactory.crearReservaBono(tipoReserva, Usuario, PistaAReservar, 0, 5, true, BonoUsuario.getIdBono(), BonoUsuario.getNumeroSesion());

            if (reserva == null) {
                // Lógica para agregar la reserva a la base de datos o al sistema de reservas
                // ... 
                return true;
            }
        }

        return false;
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
