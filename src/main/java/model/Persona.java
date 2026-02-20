package model;

import java.util.Objects;

public abstract class Persona {
    private int id;
    private final String nombre;
    private final String email;

    public Persona(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Persona(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public abstract String getTipoPersona();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(id, persona.id) || Objects.equals(email, persona.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return  "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'';
    }
}