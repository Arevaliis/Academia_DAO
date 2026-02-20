package gestor;

import dao.CursoDAO;
import dao.ProfesorDAO;
import exception.ServiceException;
import model.Curso;
import model.Profesor;
import service.CursoService;
import service.ProfesoresService;
import util.Input;
import util.Mensajes;

import java.sql.Connection;

public class GestorCursos {

    public static void ejecutarMenuProfesores(Connection connection) {
        CursoDAO cursoDAO = new CursoDAO(connection);
        ProfesorDAO profesorDAO = new ProfesorDAO(connection);

        CursoService cursoService = new CursoService(cursoDAO);
        ProfesoresService profesoresService = new ProfesoresService(profesorDAO);

        int opc = 42;

        while (opc != 0){
            Mensajes.mostrarMenuCursos();

            try {
                opc = Input.elegirOpcion();
                ejecutarAccionCurso(opc, cursoService, profesoresService);

            } catch (NumberFormatException | ServiceException e){
                System.err.println("\n" + e.getMessage() + "\n");
            }
        }
    }

    private static void ejecutarAccionCurso(int opc, CursoService cursoService, ProfesoresService profesoresService) throws ServiceException{
        switch (opc){
            case 1 -> insertarCurso(cursoService, profesoresService);
            case 2 -> actualizarCurso(cursoService, profesoresService);
            case 3 -> verCurso(cursoService);
            case 4 -> verTodosLosCursos(cursoService);
            case 5 -> eliminarCurso(cursoService);

            case 0 -> System.err.println("Volviendo...");
            default -> System.out.println("\nLa opción ingresada no está dentro de las opciones disponibles.\n");
        }
    }

    private static void insertarCurso(CursoService cursoService, ProfesoresService profesoresService) throws ServiceException {
        Curso curso = crearCurso(profesoresService);
        cursoService.insertar(curso);

        System.out.println("Curso insertado con éxito.");
    }

    private static Curso crearCurso(ProfesoresService profesoresService) throws ServiceException {
        return new Curso(Input.insertarHoras(), Input.ingresarCursoNombre(), crearProfesor(profesoresService));
    }

    private static Profesor crearProfesor(ProfesoresService profesoresService) throws ServiceException {
        return profesoresService.buscarPorId(Input.ingresarId(true));
    }

    private static void actualizarCurso(CursoService cursoService, ProfesoresService profesoresService) throws ServiceException {
        Curso existente = cursoService.buscarPorId(Input.ingresarId(false));
        Curso nuevo = actualizarProfesorCurso(existente, profesoresService);

        cursoService.actualizar(nuevo);
        System.out.println("Curso actualizado");

    }

    private static Curso actualizarProfesorCurso(Curso curso, ProfesoresService profesoresService) throws ServiceException {
        return new Curso(curso.getId(), curso.getNombre(), curso.getHoras(), crearProfesor(profesoresService) );
    }

    private static void eliminarCurso(CursoService cursoService) throws ServiceException {
        cursoService.eliminar(Input.ingresarId(false));
        System.out.println("Curso eliminado");
    }

    private static void verCurso(CursoService cursoService) throws ServiceException {
        int id = Input.ingresarId(false);
        Curso curso = cursoService.buscarPorId(id);

        System.out.println(curso);
    }

    private static void verTodosLosCursos(CursoService cursoService) throws ServiceException {
        cursoService.listarTodos().forEach(System.out::println);
    }
}