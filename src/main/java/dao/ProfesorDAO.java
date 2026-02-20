package dao;

import exception.DAOException;
import model.CrudRepository;
import model.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO implements CrudRepository<Profesor> {
    private final Connection connection;

    public ProfesorDAO(Connection connection){this.connection = connection;}

    @Override
    public void insertar(Profesor profesor) throws DAOException {
        String sql = "INSERT INTO profesores (nombre, email, especialidad, salario) VALUES (?, ?, ?, ?)";

        try(PreparedStatement insert = connection.prepareStatement(sql)){

            insert.setString(1, profesor.getNombre());
            insert.setString(2, profesor.getEmail());
            insert.setString(3, profesor.getEspecialidad());
            insert.setDouble(4, profesor.getSalario());

            insert.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'insert' del profesor " + profesor.getNombre() + e);
        }
    }

    @Override
    public void actualizar(Profesor profesor) throws DAOException {
        String sql = "UPDATE profesores SET salario = ? WHERE id = ?";

        try(PreparedStatement update = connection.prepareStatement(sql)){
            update.setDouble(1, profesor.getSalario());
            update.setInt(2, profesor.getId());

            update.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'update' del profesor " + profesor.getNombre() + e);
        }
    }

    @Override
    public void eliminar(int id) throws DAOException {
        String sql = "DELETE FROM profesores WHERE id = ?";

        try(PreparedStatement delete = connection.prepareStatement(sql)){
            delete.setInt(1, id);
            delete.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'delete' del profesor con id " + id + e);
        }
    }

    @Override
    public Profesor buscarPorId(int id) throws DAOException {
        String sql = "SELECT * FROM profesores WHERE id = ?";

        try(PreparedStatement select = connection.prepareStatement(sql)){
            select.setInt(1, id);

            try (ResultSet resultadoConsultaProfesor = select.executeQuery()) {

                if (!resultadoConsultaProfesor.next()) { return null; }

                return new Profesor(    resultadoConsultaProfesor.getInt("id"),
                                        resultadoConsultaProfesor.getString("nombre"),
                                        resultadoConsultaProfesor.getString("email"),
                                        resultadoConsultaProfesor.getString("especialidad"),
                                        resultadoConsultaProfesor.getDouble("salario")
                );
            }
        } catch (SQLException e) {
            throw new DAOException("Error durante el 'select' del profesor con id " + id + e);
        }
    }

    @Override
    public List<Profesor> listarTodos() throws DAOException {
        String sql = "SELECT * FROM profesores";
        List<Profesor> profesores = new ArrayList<>();

        try(PreparedStatement selectAll = connection.prepareStatement(sql);
            ResultSet resultadoConsultaTodosProfesores = selectAll.executeQuery()) {

            if (!resultadoConsultaTodosProfesores.next()) { return new ArrayList<>(); }

            do{
                profesores.add(new Profesor(    resultadoConsultaTodosProfesores.getInt("id"),
                                                resultadoConsultaTodosProfesores.getString("nombre"),
                                                resultadoConsultaTodosProfesores.getString("email"),
                                                resultadoConsultaTodosProfesores.getString("especialidad"),
                                                resultadoConsultaTodosProfesores.getDouble("salario"))
                );

            }while (resultadoConsultaTodosProfesores.next());

            return profesores;

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'select' de los profesores." + e);
        }
    }

    public Profesor buscarPorEmail(String email) throws DAOException {
        String sql = "SELECT * FROM profesores WHERE email = ?";

        try(PreparedStatement select = connection.prepareStatement(sql)){
            select.setString(1, email);

            try (ResultSet resultadoConsultaProfesor = select.executeQuery()) {

                if (!resultadoConsultaProfesor.next()) { return null; }

                return new Profesor(    resultadoConsultaProfesor.getInt("id"),
                                        resultadoConsultaProfesor.getString("nombre"),
                                        resultadoConsultaProfesor.getString("email"),
                                        resultadoConsultaProfesor.getString("especialidad"),
                                        resultadoConsultaProfesor.getDouble("salario"));
            }

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'select' del profesor con email " + email + e);
        }
    }
}