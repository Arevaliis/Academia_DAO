package gestor;

import dao.AlumnoDAO;
import exception.ServiceException;
import model.Alumno;
import service.AlumnoService;
import util.Input;
import util.Mensajes;

import java.sql.Connection;

public class GestorAlumnos {
    public static void ejecutarMenuAlumnos(Connection connection) {
        AlumnoDAO alumnoDAO = new AlumnoDAO(connection);
        AlumnoService alumnoService = new AlumnoService(alumnoDAO);

        int opc = 99;

        while (opc != 0){
            Mensajes.mostrarMenuAlumnos();

            try {
                opc = Input.elegirOpcion();
                ejecutarAccionAlumnos(opc, alumnoService);

            } catch (NumberFormatException | ServiceException e){
                System.err.println("\n" + e.getMessage() + "\n");
            }
        }
    }

    private static void ejecutarAccionAlumnos(int opc, AlumnoService alumnoService) throws ServiceException {

        switch (opc) {
            case 1 -> altaAlumno(alumnoService);
            case 2 -> actualizarAlumno(alumnoService);
            case 3 -> verAlumnoPorId(alumnoService);
            case 4 -> verTodosAlumnos(alumnoService);
            case 5 -> eliminarAlumno(alumnoService);

            case 0 -> System.out.println("Volviendo...");
            default -> System.out.println("\nLa opción ingresada no está dentro de las opciones disponibles.\n");
        }
    }

    private static void altaAlumno(AlumnoService alumnoService) throws ServiceException {
        alumnoService.insertar(crearAlumnoSinId());
        System.out.println("Alumno ingresado con éxito.");
    }

    private static void actualizarAlumno(AlumnoService alumnoService) throws ServiceException {
        int id = Input.ingresarId(true);

        Alumno existente = alumnoService.buscarPorId(id);
        Alumno actualizado = crearAlumnoConId(existente);

        alumnoService.actualizar(actualizado);
        System.out.println("Se ha actualizado el nivel del alumno.");
    }

    private static void verAlumnoPorId(AlumnoService alumnoService) throws ServiceException {
        int id = Input.ingresarId(true);
        Alumno alumno = alumnoService.buscarPorId(id);

        System.out.println(alumno);
    }

    private static void verTodosAlumnos(AlumnoService alumnoService) throws ServiceException {
        alumnoService.listarTodos().forEach(System.out::println);
    }

    private static void eliminarAlumno(AlumnoService alumnoService) throws ServiceException {
        int id = Input.ingresarId(true);
        alumnoService.eliminar(id);

        System.out.println("Alumno eliminado.");
    }

    private static Alumno crearAlumnoSinId() throws ServiceException {
        return new Alumno(Input.ingresarNombre(), Input.ingresarEmail(), Input.ingresarNivel());
    }

    private static Alumno crearAlumnoConId(Alumno alumno) throws ServiceException {
        return new Alumno(alumno.getId(), alumno.getNombre(), alumno.getEmail(), Input.ingresarNivel());
    }
}