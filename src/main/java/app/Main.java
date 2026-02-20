package app;

import gestor.GestorAlumnos;
import gestor.GestorCursos;
import gestor.GestorProfesores;
import util.Input;
import util.Mensajes;
import util.PoolConexiones;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = PoolConexiones.getConnection()) {
            int opcMenuPrincipal = 99;

            while(opcMenuPrincipal != 0){
                Mensajes.mostrarMenuPrincipal();

                try{
                    opcMenuPrincipal = Input.elegirOpcion();
                    mostraMenuSeleccionado(opcMenuPrincipal, connection);

                } catch (NumberFormatException e){
                    System.err.println("\n"+ e.getMessage() + "\n");
                }
            }
        } catch (SQLException e) {
            System.err.println("\nError en la conexión con la base de datos.\n");

        } finally {
            PoolConexiones.cerrarPool();
            System.err.println("\nSaliendo...");
        }
    }

    private static void mostraMenuSeleccionado(int opc, Connection connection) {
        switch (opc){
            case 1 -> GestorAlumnos.ejecutarMenuAlumnos(connection);
            case 2 -> GestorProfesores.ejecutarMenuProfesores(connection);
            case 3 -> GestorCursos.ejecutarMenuProfesores(connection);
            case 0 -> {}

            default -> System.err.println("\nLa opción ingresada no está dentro de las opciones disponibles.\n");
        }
    }
}