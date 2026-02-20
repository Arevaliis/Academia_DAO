package model;

import java.util.Objects;

public class Curso {
    private int id;
    private String nombre;
    private int horas;
    private Profesor profesorAsignado;

    public Curso(int id, String nombre, int horas, Profesor profesorAsignado) {
        this.id = id;
        this.nombre = nombre;
        this.horas = horas;
        this.profesorAsignado = profesorAsignado;
    }

    public Curso(int horas, String nombre, Profesor profesorAsignado) {
        this.horas = horas;
        this.nombre = nombre;
        this.profesorAsignado = profesorAsignado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHoras() {
        return horas;
    }

    public Profesor getProfesorAsignado() {
        return profesorAsignado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public void setProfesorAsignado(Profesor profesorAsignado) {
        this.profesorAsignado = profesorAsignado;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Curso cursos)) return false;
        return Objects.equals(id, cursos.id) || Objects.equals(nombre, cursos.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        String nombreProfesor = (profesorAsignado == null ) ? "No tiene profesor asignado": profesorAsignado.getNombre();

        return "Nombre='" + nombre + '\'' +
                ", horas=" + horas +
                ", profesorAsignado=" + nombreProfesor + ".";
    }
}