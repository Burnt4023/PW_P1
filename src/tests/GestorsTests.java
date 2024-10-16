package tests;

import classes.*;
import gestors.GestorUsuarios;

/**
 * Clase tipo Main() para probar el correcto funcionamiento de los gestores. 
*/
public class GestorsTests {
    public static void main(String[] args) throws Exception {

        // EL DIRECTORIO DE COMIENZO ES EL COMIENZO DEL PROYECTO

        GestorUsuarios gestor = new GestorUsuarios();

        Usuario user2 = new Usuario("Pepitaaaaa", "Perez", "01A", 10);

        gestor.darAltaUsuario(user2);

        gestor.listarUsuariosRegistrados();

    }
}
