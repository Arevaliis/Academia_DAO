package model;

public class Profesor extends Persona {
    private String especialidad;
    private double salario;

    public Profesor(int id, String nombre, String email, String especialidad, double salario) {
        super(id, nombre, email);
        this.especialidad = especialidad;
        this.salario = salario;
    }

    public Profesor(String nombre, String email, String especialidad, double salario) {
        super(nombre, email);
        this.especialidad = especialidad;
        this.salario = salario;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String getTipoPersona() {
        return "Profesor";
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", especialidad='" + especialidad + '\'' +
                ", salario=" + salario +
                ", tipo= " + getTipoPersona() + ".";
    }
}