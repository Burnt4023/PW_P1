package gestors;

import classes.*;
import classes.Usuario.getStringFormatedFecha;
import classes.Pista.TamanoPista;
import classes.Reserva_Classes.Reserva;
import classes.Reserva_Classes.Reserva.TipoReserva;
import classes.Reserva_Classes.ReservaFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;   // manejo de horas
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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
                                    bono.getUsosRestantes() + "," + getStringFormatedFecha(bono.getAltaDelBono()) + "," +
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
                    int IdBono  = Integer.parseInt(campos[0]);
                    Pista.TamanoPista TipoDeSesion = Pista.TamanoPista.valueOf(campos[1]);
                    String DniUsuario = campos[2];
                    int UsosRestantes = Integer.parseInt(campos[3]);
                    Date AltaDelBono = Usuario.convertString2Date(campos[4]);
                    boolean Caducado = Boolean.parseBoolean(campos[5]);

                    // Creamos el bono guardado
                    Bono bono = new Bono(IdBono, TipoDeSesion, DniUsuario, UsosRestantes, AltaDelBono, Caducado);

                    System.out.println("[DEBUG]: Cargado bono " + bono.toString());

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
            
            System.err.println("[ERROR] No se han podido cargar los bonos correctamente.\n");
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

    /**
     * Getter de FechaInscripcion String.
     * @return FechaInscripcion con formato "dd/MM/yyyy".
     */
    static public String getStringFormatedFecha(Date fecha) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);

        return df.format(fecha);
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
       
        Reserva reserva = ReservaFactory.crearReservaBono(tipoReserva, Usuario, PistaAReservar, 0, 5, true, BonoUsuario.getIdBono(), 5 - BonoUsuario.getUsosRestantes());

        if (reserva == null) {
            // Lógica para agregar la reserva a la base de datos o al sistema de reservas
            // ... 
            return true;
        }


        } else if (tipoReserva == TipoReserva.FAMILIAR) {

            Reserva reserva = ReservaFactory.crearReservaBono(tipoReserva, Usuario, PistaAReservar, 0, 5, true, BonoUsuario.getIdBono(), 5 - BonoUsuario.getUsosRestantes());

            if (reserva == null) {
                // Lógica para agregar la reserva a la base de datos o al sistema de reservas
                // ... 
                return true;
            }

            
        } else if (tipoReserva == TipoReserva.INFANTIL) {
            Reserva reserva = ReservaFactory.crearReservaBono(tipoReserva, Usuario, PistaAReservar, 0, 5, true, BonoUsuario.getIdBono(), 5 - BonoUsuario.getUsosRestantes());

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
    protected boolean checkIfPistaCanBeModified(Pista PistaConcreta, Reserva Reserva) {

        // Obtener la fecha y hora actuales
        Date fechaActual = new Date();

        // Obtener la fecha de la reserva
        Date fechaReserva = Reserva.getFecha(); // Asegúrate de que `getFecha()` retorne un `Date`

        // Calcular la diferencia en milisegundos
        long diferenciaEnMilisegundos = fechaReserva.getTime() - fechaActual.getTime();

        // Convertir la diferencia a horas
        long diferenciaEnHoras = TimeUnit.MILLISECONDS.toHours(diferenciaEnMilisegundos);

        // Verificar si faltan menos de 24 horas
        if (diferenciaEnHoras < 24) {
            return false; // No se puede modificar la reserva
        }

        return true; // Se puede modificar la reserva
    }


    /**
     * Muestra todas las reservas futuras realizadas por el usuario.
     * Este método filtra y muestra aquellas reservas cuya fecha es posterior a la fecha actual.
     * No toma parámetros y, generalmente, se esperaría que muestre las reservas en un formato amigable para el usuario.
     */
    public void verReservasFuturas() {

        Date fechaActual = new Date();  //se crea una instancia de Date para obtener la fecha y hora actual. Esto se usará para filtrar las reservas futuras.
    
        for (Reserva reserva : ListaReservas) {
            if (reserva.getFecha().after(fechaActual)) {  // Comprobar si la fecha es futura
                System.out.println(reserva);  // Ahora imprime la reserva directamente
            }
        }

    }


    /**
     * Muestra las reservas realizadas para una fecha específica.
     * @param day La fecha de la reserva que se desea ver.
     */
    public void verReserva(Date day) {

        for (Reserva reserva : ListaReservas) {
            if (esMismaFecha(reserva.getFecha(), day)) {  // Comparamos solo el día
                System.out.println(reserva);  // Se usa el método toString de la clase Reserva
            }
        }
        
    }


   /**
     * Compara si dos fechas son iguales, ignorando la hora.
     * @param fecha1 Primera fecha a comparar.
     * @param fecha2 Segunda fecha a comparar.
     * @return true si ambas fechas son el mismo día, false en caso contrario.
     */
    private boolean esMismaFecha(Date fecha1, Date fecha2) {
        // Convierte ambas fechas a LocalDate
        LocalDate localDate1 = fecha1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = fecha2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Compara las fechas sin considerar la hora
        return localDate1.isEqual(localDate2);
    }


    /**
     * Muestra las reservas asociadas a una pista específica.
     * @param PistaConcreta La pista de la cual se desean ver las reservas.
     * Este método ayuda a verificar la disponibilidad de una pista concreta en función de las reservas existentes.
     */
    public void verreserva(Pista PistaConcreta) {

        for (Reserva reserva : ListaReservas) {
            if (reserva.getPista().equals(PistaConcreta)) {  // Verificar si la pista coincide
                System.out.println(reserva);  // Llama a toString
            }
        }
    }


/**
     * Muestra las reservas realizadas para una fecha y pista específicas.
     * @param day La fecha de la reserva.
     * @param PistaConcreta La pista para la cual se desea ver la reserva.
     */
    public void verReserva(Date day, Pista PistaConcreta) {

        for (Reserva reserva : ListaReservas) {
            if (esMismaFecha(reserva.getFecha(), day) && reserva.getPista().equals(PistaConcreta)) {  // Verificar ambos criterios
                System.out.println(reserva);  // Usa el método toString
            }
        }
    }

}
