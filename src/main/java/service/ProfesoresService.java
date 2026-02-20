package service;

import dao.ProfesorDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Profesor;
import model.ServiceRepository;

import java.util.List;

public class ProfesoresService implements ServiceRepository<Profesor> {
    private final ProfesorDAO profesorDAO;

    public ProfesoresService(ProfesorDAO profesorDAO) { this.profesorDAO = profesorDAO; }

    @Override
    public void insertar(Profesor profesor) throws ServiceException {
        try{

            if (profesorDAO.buscarPorEmail(profesor.getEmail()) != null) { throw new ServiceException("El profesor ya está registrado en la base de datos."); }
            profesorDAO.insertar(profesor);

        } catch (DAOException e) {
            throw new ServiceException("Error al insertar un profesor en la base de datos.", e);
        }
    }

    @Override
    public void actualizar(Profesor profesor) throws ServiceException {

        try{
            profesorDAO.actualizar(profesor);

        } catch (DAOException e) {
            throw new ServiceException("Error al actualizar un profesor de la base de datos.", e);
        }
    }

    @Override
    public void eliminar(int id) throws ServiceException {
        try{
            Profesor profesor = profesorDAO.buscarPorId(id);
            if (profesor == null ){throw new ServiceException("El profesor no está registrado en la base de datos."); }

            profesorDAO.eliminar(id);

        } catch (DAOException e) {
            throw new ServiceException("Error al eliminar un profesor de la base de datos.", e);
        }
    }

    @Override
    public Profesor buscarPorId(int id) throws ServiceException {
        try{
            Profesor profesor = profesorDAO.buscarPorId(id);
            if (profesor == null ){throw new ServiceException("El profesor no está registrado en la base de datos."); }

            return profesor;

        } catch (DAOException e) {
            throw new ServiceException("Error al buscar un profesor en la base de datos.", e);
        }
    }

    @Override
    public List<Profesor> listarTodos() throws ServiceException {
        try{
            return profesorDAO.listarTodos();

        } catch (DAOException e) {
            throw new ServiceException("Error al listar los profesores de la base de datos.", e);
        }
    }
}