package gestor;

import dao.ProfesorDAO;
import exception.ServiceException;
import model.Profesor;
import service.ProfesoresService;
import util.Input;
import util.Mensajes;

import java.sql.Connection;

public class GestorProfesores {
    public static void ejecutarMenuProfesores(Connection connection) {
        ProfesorDAO profesorDAO = new ProfesorDAO(connection);
        ProfesoresService profesoresService = new ProfesoresService(profesorDAO);

        int opc = 99;

        while (opc != 0){
            Mensajes.mostrarMenuProfesores();

            try {
                opc = Input.elegirOpcion();
                ejecutarAccionProfesores(opc, profesoresService);

            } catch (NumberFormatException | ServiceException e){
                System.err.println("\n" + e.getMessage() + "\n");
            }
        }
    }

    private static void ejecutarAccionProfesores(int opc, ProfesoresService profesoresService) throws ServiceException {
        switch (opc) {
            case 1 -> altaProfesor(profesoresService);
            case 2 -> actualizarProfesor(profesoresService);
            case 3 -> verProfesorPorId(profesoresService);
            case 4 -> verTodosProfesores(profesoresService);
            case 5 -> eliminarProfesor(profesoresService);

            case 0 -> System.out.println("Volviendo...");
            default -> System.out.println("\nLa opción ingresada no está dentro de las opciones disponibles.\n");
        }
    }

    private static void altaProfesor(ProfesoresService profesoresService) throws ServiceException {
        profesoresService.insertar(crearProfesorSinId());
        System.out.println("Profesor agregado con éxito.");
    }

    private static void actualizarProfesor(ProfesoresService profesoresService) throws ServiceException {
        int id = Input.ingresarId(true);

        Profesor existente = profesoresService.buscarPorId(id);
        Profesor actualizado = crearProfesorConId(existente);

        profesoresService.actualizar(actualizado);
        System.out.println("Profesor actualizado.");
    }

    private static void verProfesorPorId(ProfesoresService profesoresService) throws ServiceException {
        int id = Input.ingresarId(true);
        Profesor profesor = profesoresService.buscarPorId(id);

        System.out.println(profesor);
    }

    private static void verTodosProfesores(ProfesoresService profesoresService) throws ServiceException {
        profesoresService.listarTodos().forEach(System.out::println);
    }

    private static void eliminarProfesor(ProfesoresService profesoresService) throws ServiceException {
        int id = Input.ingresarId(true);
        profesoresService.eliminar(id);

        System.out.println("Profesor eliminado.");
    }

    private static Profesor crearProfesorSinId() throws ServiceException {
        return new Profesor(Input.ingresarNombre(), Input.ingresarEmail(), Input.ingresarEspecialidad(), Input.ingresarSalario());
    }

    private static Profesor crearProfesorConId(Profesor profesor) throws ServiceException {
        return new Profesor(profesor.getId(), profesor.getNombre(), profesor.getEmail(), profesor.getEspecialidad(), Input.ingresarSalario());
    }
}