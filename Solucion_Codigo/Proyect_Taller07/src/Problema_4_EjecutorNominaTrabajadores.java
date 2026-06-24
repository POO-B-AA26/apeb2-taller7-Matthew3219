
import java.util.Arrays;

/**
 * Problema04: Se desea desarrollar un sistema de nómina para los trabajadores
 * de una empresa. Los datos personales de los trabajadores son nombre y
 * apellidos, dirección y DNI. Además, existen diferentes tipos de trabajadores:
 *
 * Fijos Mensuales: que cobran una cantidad fija al mes. Comisionistas: cobran
 * un porcentaje fijo por las ventas que han realizado Por Horas: cobran un
 * precio por cada una de las horas que han realizado durante el mes. El precio
 * es fijo para las primeras 40 horas y es otro para las horas realizadas a
 * partir de la 40 hora mensual. Jefe: cobra un sueldo fijo (no hay que
 * calcularlo). Cada empleado tiene obligatoriamente un jefe (exceptuando los
 * jefes que no tienen ninguno). El programa debe permitir dar de alta a
 * trabajadores, así como fijar horas o ventas realizadas e imprimir la nómina
 * correspondiente al final de mes. Diseñe la jerarquia de clases UML basado en
 * herencia, que defina de mejor forma el escenario planteado. Para probar el
 * diseño de clases, instancia en el clase de prueba Ejecutor (la-s clase-s
 * respectiva-s), con datos aleatorios. En los escenarios de prueba verifique su
 * solución con al menos 2 tipos de trabajadores.
 *
 * @author Matthew Castillo
 * @version 1.0
 */
class Trabajador {

    public String nombre;
    public String apellidos;
    public String direccion;
    public String dni;
    public double sueldo;

    public Trabajador(String nombre, String apellidos, String direccion, String dni, double sueldo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.sueldo = sueldo;
    }

    public double calcularTotalSueldo() {
        return sueldo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + apellidos
                + "\nDNI: " + dni
                + "\nDireccion: " + direccion
                + "\nSueldo base: $" + sueldo;
    }
}

class TrabajadorFijoMensual extends Trabajador {

    public TrabajadorFijoMensual(String nombre, String apellidos, String direccion, String dni, double sueldo) {
        super(nombre, apellidos, direccion, dni, sueldo);
    }

    @Override
    public double calcularTotalSueldo() {
        return sueldo;
    }

    @Override
    public String toString() {
        return "=== Trabajador Fijo Mensual ==="
                + "\n" + super.toString()
                + "\nTotal nomina: $" + calcularTotalSueldo();
    }
}

class TrabajadorComisionista extends Trabajador {

    public int ventas;
    public double porcentaje;

    public TrabajadorComisionista(String nombre, String apellidos, String direccion, String dni, double sueldo, int ventas, double porcentaje) {
        super(nombre, apellidos, direccion, dni, sueldo);
        this.ventas = ventas;
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcularTotalSueldo() {
        return sueldo + (ventas * porcentaje / 100);
    }

    @Override
    public String toString() {
        return "=== Trabajador Comisionista ==="
                + "\n" + super.toString()
                + "\nVentas: " + ventas
                + "\nPorcentaje comision: " + porcentaje + "%"
                + "\nTotal nomina: $" + calcularTotalSueldo();
    }
}

class TrabajadorPorHoras extends Trabajador {

    public double precio;
    public double horasTrabajadas;
    public double precioHorasExtra;

    public TrabajadorPorHoras(String nombre, String apellidos, String direccion, String dni, double sueldo, double precio, double horasTrabajadas, double precioHorasExtra) {
        super(nombre, apellidos, direccion, dni, sueldo);
        this.precio = precio;
        this.horasTrabajadas = horasTrabajadas;
        this.precioHorasExtra = precioHorasExtra;
    }

    @Override
    public double calcularTotalSueldo() {
        if (horasTrabajadas <= 40) {
            return horasTrabajadas * precio;
        } else {
            return (40 * precio) + ((horasTrabajadas - 40) * precioHorasExtra);
        }
    }

    @Override
    public String toString() {
        return "=== Trabajador Por Horas ==="
                + "\n" + super.toString()
                + "\nHoras trabajadas: " + horasTrabajadas
                + "\nPrecio/hora normal: $" + precio
                + "\nPrecio/hora extra: $" + precioHorasExtra
                + "\nTotal nomina: $" + calcularTotalSueldo();
    }
}

class Jefe extends Trabajador {

    public Jefe(String nombre, String apellidos, String direccion, String dni, double sueldo) {
        super(nombre, apellidos, direccion, dni, sueldo);
    }

    @Override
    public double calcularTotalSueldo() {
        return sueldo;
    }

    @Override
    public String toString() {
        return "=== Jefe ==="
                + "\n" + super.toString()
                + "\nTotal nomina: $" + calcularTotalSueldo();
    }
}

public class Problema_4_EjecutorNominaTrabajadores {

    public static void main(String[] args) {

        Jefe jefe = new Jefe("Alberto", "Capurro", "Av. Principal 101", "J001", 3000.0);

        TrabajadorFijoMensual fijo = new TrabajadorFijoMensual(
                "Melany", "Abad", "Calle 5 #23", "T001", 1200.0);

        TrabajadorComisionista comisionista = new TrabajadorComisionista(
                "Jeremy", "Pizarro", "Calle 8 #45", "T002", 500.0, 150, 10.0);

        TrabajadorPorHoras porHoras = new TrabajadorPorHoras(
                "Joel", "Cabrera", "Av. Sur 67", "T003", 0.0, 12.0, 45.0, 18.0);

        System.out.println(jefe);
        System.out.println();
        System.out.println(fijo);
        System.out.println();
        System.out.println(comisionista);
        System.out.println();
        System.out.println(porHoras);
    }
}
/**
 * Run: 
 * === Jefe ===
    Nombre: Alberto Capurro
    DNI: J001
    Direccion: Av. Principal 101
    Sueldo base: $3000.0
    Total nomina: $3000.0

    === Trabajador Fijo Mensual ===
    Nombre: Melany Abad
    DNI: T001
    Direccion: Calle 5 #23
    Sueldo base: $1200.0
    Total nomina: $1200.0

    === Trabajador Comisionista ===
    Nombre: Jeremy Pizarro
    DNI: T002
    Direccion: Calle 8 #45
    Sueldo base: $500.0
    Ventas: 150
    Porcentaje comision: 10.0%
    Total nomina: $515.0

    === Trabajador Por Horas ===
    Nombre: Joel Cabrera
    DNI: T003
    Direccion: Av. Sur 67
    Sueldo base: $0.0
    Horas trabajadas: 45.0
    Precio/hora normal: $12.0
    Precio/hora extra: $18.0
    Total nomina: $570.0
BUILD SUCCESSFUL (total time: 0 seconds)
 */
