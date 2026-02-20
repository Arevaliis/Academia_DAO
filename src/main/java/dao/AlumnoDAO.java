package dao;

import exception.DAOException;
import model.Alumno;
import model.CrudRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO implements CrudRepository<Alumno> {
    private final Connection connection;

    public AlumnoDAO(Connection connection){ this.connection = connection; }

    @Override
    public void insertar(Alumno alumno) throws DAOException {
        String sql = "INSERT INTO alumnos(nombre, email, nivel) VALUES (?, ?, ?)";

        try(PreparedStatement insert = connection.prepareStatement(sql)){

            insert.setString(1, alumno.getNombre());
            insert.setString(2, alumno.getEmail());
            insert.setString(3, alumno.getNivel());
            insert.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'insert' del alumno. " + e);
        }
    }

    @Override
    public void actualizar(Alumno alumno) throws DAOException {
        String sql = "UPDATE alumnos SET nivel = ? WHERE ID = ?";

        try(PreparedStatement update = connection.prepareStatement(sql)){
            update.setString(1, alumno.getNivel());
            update.setInt(2, alumno.getId());
            update.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'update' del alumno. " + e);
        }
    }

    @Override
    public void eliminar(int id) throws DAOException {
        String sql = "DELETE FROM alumnos WHERE id = ?";

        try(PreparedStatement delete = connection.prepareStatement(sql)){

            delete.setInt(1, id);
            delete.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'delete' del alumno. " + e);
        }
    }

    @Override
    public Alumno buscarPorId(int id) throws DAOException {
        String sql = "SELECT * FROM alumnos WHERE id = ?";

        try(PreparedStatement select = connection.prepareStatement(sql)){
            select.setInt(1, id);

            try (ResultSet resultadoConsultaAlumno = select.executeQuery()) {

                if (!resultadoConsultaAlumno.next()) { return null; }

                return new Alumno(  resultadoConsultaAlumno.getInt("id"),
                                    resultadoConsultaAlumno.getString("nombre"),
                                    resultadoConsultaAlumno.getString("email"),
                                    resultadoConsultaAlumno.getString("nivel")
                );
            }

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'select' del alumno. " + e + " por id.");
        }
    }

    public Alumno buscarPorEmail(String email) throws DAOException {
        String sql = "SELECT * FROM alumnos WHERE email = ?";

        try(PreparedStatement select = connection.prepareStatement(sql)){
            select.setString(1, email);

            try (ResultSet resultadoConsultaAlumno = select.executeQuery()) {

                if (!resultadoConsultaAlumno.next()) { return null; }

                return new Alumno(  resultadoConsultaAlumno.getInt("id"),
                        resultadoConsultaAlumno.getString("nombre"),
                        resultadoConsultaAlumno.getString("email"),
                        resultadoConsultaAlumno.getString("nivel")
                );
            }

        } catch (SQLException e) {
            throw new DAOException("Error durante el 'select' del alumno. " + e + " por email.");
        }
    }

    @Override
    public List<Alumno> listarTodos() throws DAOException {
        String sql = "SELECT * FROM alumnos";
        List<Alumno> alumnos = new ArrayList<>();

        try(PreparedStatement selectAll = connection.prepareStatement(sql);
            ResultSet resultadoConsultaTodosAlumnos = selectAll.executeQuery()) {

                if (!resultadoConsultaTodosAlumnos.next()) { return new ArrayList<>(); }

                do{
                    alumnos.add(new Alumno( resultadoConsultaTodosAlumnos.getInt("id"),
                                            resultadoConsultaTodosAlumnos.getString("nombre"),
                                            resultadoConsultaTodosAlumnos.getString("email"),
                                            resultadoConsultaTodosAlumnos.getString("nivel"))
                    );

                }while (resultadoConsultaTodosAlumnos.next());

                return alumnos;

            } catch (SQLException e) {
            throw new DAOException("Error durante el 'select' de los alumnos. " + e);
        }
    }
}