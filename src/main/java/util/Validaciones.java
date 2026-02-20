package util;

import exception.ServiceException;

public class Validaciones {
    public static void validarNumeroIngresado(String opc) {

        if (opc.isEmpty()){ throw new NumberFormatException("Debe ingresar una opción. No puede dejar el campo vacío."); }
        if (opc.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new NumberFormatException("La opción ingresada debe ser un número entero válido."); }
    }

    public static void validarNombre(String nombre) throws ServiceException {

        if (nombre.isEmpty()){ throw new ServiceException("Debe ingresar un nombre. No puede dejar el campo vacío."); }
        if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new ServiceException("El nombre ingresado no es válido. Solo puede contener letras."); }
    }

    public static void validarEmail(String email) throws ServiceException {

        if (email.isEmpty()){ throw new ServiceException("Debe ingresar un email. No puede dejar el campo vacío."); }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) { throw new ServiceException("El email ingresado no es válido."); }
    }

    public static void validarNivel(String nivel) throws ServiceException {

        if (nivel.isEmpty()){ throw new ServiceException("Debe ingresar el nivel del alumno. No puede dejar el campo vacío."); }
        if (!nivel.matches("^(basico|intermedio|avanzado)$")) { throw new ServiceException("El nivel debe ser: 'basico', 'intermedio' o 'avanzado'."); }
    }

    public static void validarId(String id) {

        if (id.isEmpty()) { throw new NumberFormatException("Debe ingresar el id de la persona. No puede dejar el campo vacío."); }
        if (id.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new NumberFormatException("El ID debe ser un número entero válido."); }
    }

    public static void validarEspecialidad(String especialidad) throws ServiceException {

        if (especialidad.isEmpty()) { throw new NumberFormatException("Debe ingresar la especialidad del profesor. No puede dejar el campo vacío."); }
        if (!especialidad.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new ServiceException("La especialidad ingresada no es válida. Solo puede contener letras."); }
    }

    public static void validarSalario(String salario) throws ServiceException {

        if (salario.isEmpty()) { throw new NumberFormatException("Debe ingresar el salario del profesor. No puede dejar el campo vacío."); }
        if (salario.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new NumberFormatException("El salario debe ser un número válido. No puede ingresar letras ni símbolos."); }
        if (salario.contains(",")) { throw new NumberFormatException("Debe poner solo '.' en la parte decimal. Ejemplo: 2500.50"); }

        double salarioNumero =  Double.parseDouble(salario);
        if (1000.00 > salarioNumero || salarioNumero > 4000.01) { throw new ServiceException("El salario debe estar comprendido entre 1.000,00 € y 4.000,00 €."); }
    }

    public static void validarNombreCurso(String nombre) throws ServiceException {

        if (nombre.isEmpty()){ throw new ServiceException("Debe ingresar el nombre del curso. No puede dejar el campo vacío."); }
        if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new ServiceException("El nombre del curso ingresado no es válido. Solo puede contener letras."); }
    }

    public static void validarNumeroHoras(String horas)  throws ServiceException {

        if (horas.isEmpty()) { throw new NumberFormatException("Debe ingresar las horas del curso. No puede dejar el campo vacío."); }
        if (horas.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) { throw new NumberFormatException("Las horas del curso debe ser un número entero válido."); }

        int horasNumero = Integer.parseInt(horas);
        if (19 > horasNumero || horasNumero > 501) { throw new ServiceException("El número de horas mínimo de un curso son 20h y el máximo son 500h."); }
    }
}