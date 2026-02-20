package model;

import exception.DAOException;

import java.util.List;

public interface CrudRepository<T> {

    void insertar(T entidad) throws DAOException;

    void actualizar(T entidad) throws DAOException;

    void eliminar(int id) throws DAOException;

    T buscarPorId(int id) throws DAOException;

    List<T> listarTodos() throws DAOException;
}