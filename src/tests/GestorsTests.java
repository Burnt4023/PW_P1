package tests;

import classes.*;
import classes.Pista.*;
import classes.Pista.TamanoPista;
import gestors.*;

public class GestorsTests {
    public static void main(String[] args) throws Exception {

        // EL DIRECTORIO DE COMIENZO ES EL COMIENZO DEL PROYECTO

        GestorPistas gestor = new GestorPistas();

        //gestor.ListarPistasLibres(5, true);
        //gestor.ListarPistasNoDisponibles();
        
        Pista EjemploPista1 = gestor.crearPista("Pista General", true, true, TamanoPista.ADULTOS, 5);
        Pista EjemploPista2 = gestor.crearPista("Pista Dedicada", false, true, TamanoPista.ADULTOS, 5);
        Material EjemploMaterial1 = gestor.crearMaterial(00, Material.Tipo.PELOTA, false, Material.Estado.DISPONIBLE);
        Material EjemploMaterial2 = gestor.crearMaterial(01, Material.Tipo.CANASTA, false, Material.Estado.DISPONIBLE);

        gestor.AsociarMaterialAPistaDisponible(EjemploMaterial1, EjemploPista1);
        gestor.AsociarMaterialAPistaDisponible(EjemploMaterial2, EjemploPista1);
        

        gestor.ListarPistasLibres(5, true);
        gestor.ListarPistasNoDisponibles();
    }
}
