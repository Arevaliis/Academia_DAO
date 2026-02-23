DROP DATABASE IF EXISTS academia;
CREATE DATABASE academia;

USE academia;

CREATE TABLE alumnos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    nivel ENUM('Basico', 'Intermedio', 'Avanzado') NOT NULL
);

CREATE TABLE profesores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    especialidad VARCHAR(100) NOT NULL,
    salario DECIMAL(8,2) NOT NULL CHECK (salario > 1000.00 and salario < 4000.01)
);

CREATE TABLE cursos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    horas INT NOT NULL CHECK (horas > 59 and horas < 501),
    profesor_id INT,

    CONSTRAINT fk_curso_profesor
        FOREIGN KEY (profesor_id)
        REFERENCES profesores(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

INSERT INTO alumnos (nombre, email, nivel) VALUES
    ('Juan Pérez', 'juan.perez@email.com', 'Basico'),
    ('María López', 'maria.lopez@email.com', 'Intermedio'),
    ('Carlos García', 'carlos.garcia@email.com', 'Avanzado');

INSERT INTO profesores (nombre, email, especialidad, salario) VALUES
    ('Ana Torres', 'ana.torres@email.com', 'Java', 2200.00),
    ('Luis Martínez', 'luis.martinez@email.com', 'Bases de Datos', 2100.00);

INSERT INTO cursos (nombre, horas, profesor_id) VALUES
    ('Programación en Java', 120, 1),
    ('Bases de Datos Relacionales', 100, 2),
    ('Introducción a la Programación', 80, NULL);

