package service;

import dao.CursoDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Curso;
import model.ServiceRepository;

import java.util.List;

public class CursoService implements ServiceRepository<Curso> {
    private final CursoDAO cursoDAO;

    public CursoService(CursoDAO cursoDAO){ this.cursoDAO = cursoDAO; }

    @Override
    public void insertar(Curso curso) throws ServiceException {
        try {
            cursoDAO.insertar(curso);

        } catch (DAOException e) {
            throw new ServiceException("Error al insertar un curso en la base de datos.", e);
        }
    }

    @Override
    public void actualizar(Curso curso) throws ServiceException {
        try {
            cursoDAO.actualizar(curso);

        } catch (DAOException e) {
            throw new ServiceException("Error al actualizar un curso de la base de datos.", e);
        }
    }

    @Override
    public void eliminar(int id) throws ServiceException {
        try {

            Curso curso = cursoDAO.buscarPorId(id);
            if (curso == null ){throw new ServiceException("El curso no está registrado en la base de datos."); }

            cursoDAO.eliminar(id);

        } catch (DAOException e) {
            throw new ServiceException("Error al eliminar un curso de la base de datos.", e);
        }
    }

    @Override
    public Curso buscarPorId(int id) throws ServiceException {
        try {
            return cursoDAO.buscarPorId(id);

        } catch (DAOException e) {
            throw new ServiceException("Error al buscar un curso de la base de datos.", e);
        }    }

    @Override
    public List<Curso> listarTodos() throws ServiceException {
        try {
            return cursoDAO.listarTodos();

        } catch (DAOException e) {
            throw new ServiceException("Error al listar los cursos de la base de datos.", e);
        }
    }
}