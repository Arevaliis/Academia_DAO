package service;

import dao.AlumnoDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Alumno;
import model.ServiceRepository;

import java.util.List;

public class AlumnoService implements ServiceRepository<Alumno> {
    private final AlumnoDAO alumnoDAO;

    public AlumnoService(AlumnoDAO alumnoDAO){ this.alumnoDAO = alumnoDAO; }

    @Override
    public void insertar(Alumno alumno) throws ServiceException {
        try{
            if (alumnoDAO.buscarPorEmail(alumno.getEmail()) != null ){throw new ServiceException("El alumno ya está registrado en la base de datos."); }
            alumnoDAO.insertar(alumno);

        } catch (DAOException e) {
            throw new ServiceException("Error al insertar un alumno en la base de datos.", e);
        }
    }

    @Override
    public void actualizar(Alumno alumno) throws ServiceException {
        try{
            alumnoDAO.actualizar(alumno);

        } catch (DAOException e) {
            throw new ServiceException("Error al actualizar un alumno de la base de datos", e);
        }
    }

    @Override
    public void eliminar(int id) throws ServiceException {
        try{
            Alumno alumno = alumnoDAO.buscarPorId(id);
            if (alumno == null ){throw new ServiceException("El alumno no está registrado en la base de datos."); }

            alumnoDAO.eliminar(id);

        } catch (DAOException e) {
            throw new ServiceException("Error al eliminar un alumno de la base de datos.", e);
        }
    }

    @Override
    public Alumno buscarPorId(int id) throws ServiceException {
        try{
            Alumno alumno = alumnoDAO.buscarPorId(id);
            if (alumno == null ){throw new ServiceException("El alumno no está registrado en la base de datos."); }

            return alumno;

        } catch (DAOException e) {
            throw new ServiceException("Error al buscar un alumno en la base de datos por id.", e);
        }
    }

    @Override
    public List<Alumno> listarTodos() throws ServiceException {
        try{
            return alumnoDAO.listarTodos();

        } catch (DAOException e) {
            throw new ServiceException("Error al listar los alumno de la base de datos.", e);
        }
    }
}