package dao;

import exception.DAOException;
import model.CrudRepository;
import model.Curso;
import model.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO implements CrudRepository<Curso> {
    private final Connection connection;

    public CursoDAO(Connection connection){ this.connection = connection; }

    @Override
    public void insertar(Curso curso) throws DAOException {
        String sql = "INSERT INTO cursos(nombre, horas, profesor_id) VALUES (?, ?, ?)";

        try(PreparedStatement insert = connection.prepareStatement(sql)){

            insert.setString(1, curso.getNombre());
            insert.setInt(2, curso.getHoras());
            insert.setInt(3, curso.getProfesorAsignado().getId());

            insert.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'insert' del alumno. " + e);
        }
    }

    @Override
    public void actualizar(Curso curso) throws DAOException {
        String sql = "UPDATE cursos SET profesor_id = ? WHERE ID = ?";

        try(PreparedStatement update = connection.prepareStatement(sql)){
            update.setInt(1, curso.getProfesorAsignado().getId());
            update.setInt(2, curso.getId());

            update.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'update' del curso. " + e);
        }
    }

    @Override
    public void eliminar(int id) throws DAOException {
        String sql = "DELETE FROM cursos WHERE id = ?";

        try(PreparedStatement delete = connection.prepareStatement(sql)){
            delete.setInt(1, id);
            delete.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'delete' del curso. " + e);
        }
    }

    @Override
    public Curso buscarPorId(int id) throws DAOException {
        String sqlCurso = "SELECT * FROM cursos WHERE id = ?";

        try(PreparedStatement selectCurso = connection.prepareStatement(sqlCurso)){
            selectCurso.setInt(1, id);

            try (ResultSet resultadoCurso = selectCurso.executeQuery()) {

                if (!resultadoCurso.next()) { return null; }

                Profesor profesor_id = buscarProfesor(resultadoCurso.getInt("profesor_id"));

                return new Curso( resultadoCurso.getInt("id"),
                                  resultadoCurso.getString("nombre"),
                                  resultadoCurso.getInt("horas"),
                                  profesor_id
                );
            }

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'select' del curso. " + e);
        }
    }

    public Profesor buscarProfesor(int id) throws DAOException {
        String sqlProfesor = "SELECT * FROM profesores WHERE id = ?";

        try(PreparedStatement selectProfesor = connection.prepareStatement(sqlProfesor)){
            selectProfesor.setInt(1, id);

            try (ResultSet profesor = selectProfesor.executeQuery()) {

                if (!profesor.next()) { return null; }

                return new Profesor( profesor.getInt("id"),
                                     profesor.getString("nombre"),
                                     profesor.getString("email"),
                                     profesor.getString("especialidad"),
                                     profesor.getDouble("salario")
                );
            }

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'select' del curso. " + e);
        }
    }

    @Override
    public List<Curso> listarTodos() throws DAOException {
        String sqlCurso = "SELECT * FROM cursos";
        List<Curso> cursos = new ArrayList<>();

        try(PreparedStatement selectCurso = connection.prepareStatement(sqlCurso);
            ResultSet resultadoCursos = selectCurso.executeQuery()){

            if (!resultadoCursos.next()) { return null; }

            do{
                cursos.add(new Curso(   resultadoCursos.getInt("id"),
                                        resultadoCursos.getString("nombre"),
                                        resultadoCursos.getInt("horas"),
                                        buscarProfesor(resultadoCursos.getInt("profesor_id")))
                );


            } while (resultadoCursos.next());

            return cursos;

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'select' de los cursos. " + e);
        }
    }
}