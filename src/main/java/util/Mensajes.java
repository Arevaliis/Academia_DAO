package util;

public class Mensajes {

    public static void mostrarMenuPrincipal() {
        System.out.print("""
                
                ===== ACADEMIA =====
                1. Gestión de alumnos
                2. Gestión de profesores
                3. Gestión de cursos
                0. Salir
                Opción:
                """
        );
    }

    public static void mostrarMenuAlumnos() {
        System.out.print("""
                
                === ALUMNOS ===
                1. Alta alumno
                2. Actualizar nivel alumno
                3. Ver alumno por ID
                4. Ver todos los alumnos
                5. Eliminar alumno
                0. Volver
                Opción: """
        );
    }

    public static void mostrarMenuProfesores() {
        System.out.print("""
                
                === PROFESORES ===
                1. Alta profesor
                2. Actualizar profesor
                3. Ver profesor por ID
                4. Ver todos los profesores
                5. Eliminar profesor
                0. Volver
                Opción: """
        );
    }

    public static void mostrarMenuCursos() {
        System.out.print("""
                
                === CURSOS ===
                1. Alta curso
                2. Asignar profesor a curso
                3. Ver curso por ID
                4. Ver todos los cursos
                5. Eliminar curso
                0. Volver
                Opción: """
        );
    }
}