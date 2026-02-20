package model;

public class Alumno extends Persona{
    private final String nivel;

    public Alumno(int id, String nombre, String email, String nivel) {
        super(id, nombre, email);
        this.nivel = nivel;
    }

    public Alumno(String nombre, String email, String nivel) {
        super(nombre, email);
        this.nivel = nivel;
    }

    public String getNivel() {
        return nivel;
    }

    @Override
    public String getTipoPersona() {
        return "Alumno";
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", nivel=" + nivel + '\'' +
                ", tipo =" + getTipoPersona() + ".";
    }
}