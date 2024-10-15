package tests;

import classes.*;
import gestors.*;

public class GestorsTests {
    public static void main(String[] args) throws Exception {

        // EL DIRECTORIO DE COMIENZO ES EL COMIENZO DEL PROYECTO

        Usuario NuevoUsuario = new Usuario("Nombre", "Apellidos", "dniA", 20);
        Usuario Pepe = new Usuario("Pepe", "garcia", "36478234A", 25);

        GestorUsuarios gestor = new GestorUsuarios();

        gestor.darAltaUsuario(NuevoUsuario);
        gestor.darAltaUsuario(Pepe);


        gestor.listarUsuariosRegistrados();

        gestor.modificarUsuario("36478234A", "Lucas", "Perez", 26);

        gestor.listarUsuariosRegistrados();
    }
}
