package util;

import exception.ServiceException;

import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static int elegirOpcion() {
        String opc = scanner.nextLine();
        Validaciones.validarNumeroIngresado(opc);

        return Integer.parseInt(opc);
    }

    public static String ingresarNombre() throws ServiceException {
        System.out.print("Ingresa el nombre de la persona: ");
        String nombre = scanner.nextLine().trim().toLowerCase();
        Validaciones.validarNombre(nombre);

        return nombre;
    }

    public static String ingresarEmail() throws ServiceException {
        System.out.print("Ingresa el email de la persona: ");
        String email = scanner.nextLine().trim().toLowerCase();
        Validaciones.validarEmail(email);

        return email;
    }

    public static String ingresarNivel() throws ServiceException {
        System.out.print("Ingresa el nivel del alumno: ");
        String nivel = scanner.nextLine().trim().toLowerCase();
        Validaciones.validarNivel(nivel);

        return nivel;
    }

    public static int ingresarId(boolean valor) {
        String mensaje = (valor) ? "de la persona": "del curso";

        System.out.print("Ingresa el id " + mensaje + ": ");
        String id = scanner.nextLine();
        Validaciones.validarId(id);

        return Integer.parseInt(id);
    }

    public static String ingresarEspecialidad() throws ServiceException {
        System.out.print("Ingresa la especialidad del profesor: ");
        String especialidad = scanner.nextLine();
        Validaciones.validarEspecialidad(especialidad);

        return especialidad;
    }

    public static double ingresarSalario() throws ServiceException {
        System.out.print("Ingresa el salario del profesor: ");
        String salario = scanner.nextLine();
        Validaciones.validarSalario(salario);

        return Double.parseDouble(salario);
    }

    public static int insertarHoras() throws ServiceException {
        System.out.print("Ingresa el número de horas del curso: ");
        String horas = scanner.nextLine();
        Validaciones.validarNumeroHoras(horas);

        return Integer.parseInt(horas);
    }

    public static String ingresarCursoNombre() throws ServiceException {
        System.out.print("Ingresa el nombre del curso: ");
        String nombre = scanner.nextLine().trim().toLowerCase();
        Validaciones.validarNombreCurso(nombre);

        return nombre;
    }
}