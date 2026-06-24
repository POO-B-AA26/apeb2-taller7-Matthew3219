
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Problema06: El banco UN BANCO mantiene las cuentas de varios clientes. Los
 * datos que describen a cada una de las cuentas consisten en el número de
 * cuenta, el nombre del cliente y el balance actual. Escriba una clase para
 * implementar dicha cuenta bancaria. El método constructor debe aceptar como
 * parámetros el número de cuenta y el nombre. Debe proporcionarse métodos para
 * depositar o retirar una cantidad de dinero y obtener el balance actual.
 *
 * El banco ofrece a sus clientes dos tipos de cuentas, una de CHEQUES y una de
 * AHORROS. Una cuenta de cheques puede sobregirarse (el balance puede ser menor
 * que cero), pero una cuenta de ahorros no. Al final de cada mes, se calcula el
 * interés sobre la cantidad que tenga la cuenta de ahorros. Este interés se
 * suma al balance. Escriba clases para describir cada uno de estos tipos de
 * cuentas, haciendo un máximo uso de la herencia. La clase de la cuenta de
 * ahorros debe proporcionar un método que sea invocado para calcular el
 * interés. Además, el banco está pensando en implementar una cuenta PLATINO que
 * viene siendo similar a los otros dos tipos anteriores de cuentas bancarias,
 * ésta tiene el interés del 10%, sin cargos ni castigos por sobregiro.
 *
 * Note
 *
 * Ud. debe implementar una clase de PRUEBA (Clase de ejecución) desde la cual
 * se pueda evidenciar el correcto funcionamiento de cada clase con n clientes,
 * y que además se pueda mostrar el balance (estado de cuenta) para cada
 * cliente.
 *
 * @author Matthew Castillo
 * @version 1.0
 */
class Cuenta {

    public String numeroCuenta;
    public String nombreCliente;
    public double balance;

    public Cuenta(String numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = 0.0;
    }

    public void depositar(double cantidad) {
        balance += cantidad;
        System.out.println("Deposito de $" + cantidad + " realizado. Balance actual: $" + balance);
    }

    public void retirar(double cantidad) {
        balance -= cantidad;
        System.out.println("Retiro de $" + cantidad + " realizado. Balance actual: $" + balance);
    }

    public double getBalanceActual() {
        return balance;
    }

    @Override
    public String toString() {
        return "Cuenta: " + numeroCuenta
                + "\nCliente: " + nombreCliente
                + "\nBalance: $" + balance;
    }
}

class Cheques extends Cuenta {

    public boolean sobregirarse;

    public Cheques(String numeroCuenta, String nombreCliente, double balance, boolean sobregirarse) {
        super(numeroCuenta, nombreCliente);
        this.balance = balance;
        this.sobregirarse = sobregirarse;
    }

    @Override
    public void retirar(double cantidad) {
        if (!sobregirarse && balance - cantidad < 0) {
            System.out.println("Error: No se puede sobregirar esta cuenta de cheques.");
            return;
        }
        balance -= cantidad;
        System.out.println("Retiro de $" + cantidad + " realizado. Balance actual: $" + balance);
    }

    @Override
    public String toString() {
        return "=== Cuenta Cheques ==="
                + "\n" + super.toString()
                + "\nPermite sobregiro: " + (sobregirarse ? "Si" : "No");
    }
}

class Ahorro extends Cuenta {

    public double interes;

    public Ahorro(String numeroCuenta, String nombreCliente, double balance) {
        super(numeroCuenta, nombreCliente);
        this.balance = balance;
        this.interes = 0.05; 
    }

    public Ahorro(String numeroCuenta, String nombreCliente, double balance, double interes) {
        super(numeroCuenta, nombreCliente);
        this.balance = balance;
        this.interes = interes;
    }

    @Override
    public void retirar(double cantidad) {
        if (balance - cantidad < 0) {
            System.out.println("Error: La cuenta de ahorros no puede tener balance negativo.");
            return;
        }
        balance -= cantidad;
        System.out.println("Retiro de $" + cantidad + " realizado. Balance actual: $" + balance);
    }

    public double calcularInteres() {
        double interesGanado = balance * interes;
        balance += interesGanado;
        System.out.println("Interes calculado: $" + interesGanado + " | Nuevo balance: $" + balance);
        return interesGanado;
    }

    @Override
    public String toString() {
        return "=== Cuenta Ahorros ==="
                + "\n" + super.toString()
                + "\nTasa de interes: " + (interes * 100) + "%";
    }
}

class Platino extends Cuenta {

    public double interes = 0.10;

    public Platino(String numeroCuenta, String nombreCliente, double balance) {
        super(numeroCuenta, nombreCliente);
        this.balance = balance;
    }

    @Override
    public void retirar(double cantidad) {
        balance -= cantidad;
        System.out.println("Retiro de $" + cantidad + " realizado. Balance actual: $" + balance);
    }

    public double calcularInteres() {
        double interesGanado = balance * interes;
        balance += interesGanado;
        System.out.println("Interes Platino (10%): $" + interesGanado + " | Nuevo balance: $" + balance);
        return interesGanado;
    }

    @Override
    public String toString() {
        return "=== Cuenta Platino ==="
                + "\n" + super.toString()
                + "\nTasa de interes: " + (interes * 100) + "%"
                + "\nSin penalizacion por sobregiro";
    }
}

public class Problema_6_EjecutorBanco {

    public static void main(String[] args) {

        ArrayList<Cuenta> cuentas = new ArrayList<>();

        Cheques cheque1 = new Cheques("CH001", "Jeremy Pizarro", 500.0, true);
        Ahorro ahorro1 = new Ahorro("AH001", "Melany Abad", 1000.0);
        Platino platino1 = new Platino("PL001", "Jorge Astudillo", 2000.0);

        cuentas.add(cheque1);
        cuentas.add(ahorro1);
        cuentas.add(platino1);

        System.out.println("===== ESTADO INICIAL =====\n");
        for (Cuenta c : cuentas) {
            System.out.println(c);
            System.out.println();
        }

        System.out.println("===== OPERACIONES =====\n");

        System.out.println("-- Cuenta Cheques --");
        cheque1.depositar(200.0);
        cheque1.retirar(800.0); 
        System.out.println();

        System.out.println("-- Cuenta Ahorros --");
        ahorro1.depositar(500.0);
        ahorro1.retirar(2000.0); 
        ahorro1.calcularInteres();
        System.out.println();

        System.out.println("-- Cuenta Platino --");
        platino1.depositar(1000.0);
        platino1.retirar(4000.0);
        platino1.calcularInteres();
        System.out.println();

        System.out.println("===== ESTADO FINAL =====\n");
        for (Cuenta c : cuentas) {
            System.out.println(c);
            System.out.println();
        }
    }
}
/**
 * ===== ESTADO INICIAL =====

    === Cuenta Cheques ===
    Cuenta: CH001
    Cliente: Jeremy Pizarro
    Balance: $500.0
    Permite sobregiro: Si

    === Cuenta Ahorros ===
    Cuenta: AH001
    Cliente: Melany Abad
    Balance: $1000.0
    Tasa de interes: 5.0%

    === Cuenta Platino ===
    Cuenta: PL001
    Cliente: Jorge Astudillo
    Balance: $2000.0
    Tasa de interes: 10.0%
    Sin penalizacion por sobregiro

    ===== OPERACIONES =====

    -- Cuenta Cheques --
    Deposito de $200.0 realizado. Balance actual: $700.0
    Retiro de $800.0 realizado. Balance actual: $-100.0

    -- Cuenta Ahorros --
    Deposito de $500.0 realizado. Balance actual: $1500.0
    Error: La cuenta de ahorros no puede tener balance negativo.
    Interes calculado: $75.0 | Nuevo balance: $1575.0

    -- Cuenta Platino --
    Deposito de $1000.0 realizado. Balance actual: $3000.0
    Retiro de $4000.0 realizado. Balance actual: $-1000.0
    Interes Platino (10%): $-100.0 | Nuevo balance: $-1100.0

    ===== ESTADO FINAL =====

    === Cuenta Cheques ===
    Cuenta: CH001
    Cliente: Jeremy Pizarro
    Balance: $-100.0
    Permite sobregiro: Si

    === Cuenta Ahorros ===
    Cuenta: AH001
    Cliente: Melany Abad
    Balance: $1575.0
    Tasa de interes: 5.0%

    === Cuenta Platino ===
    Cuenta: PL001
    Cliente: Jorge Astudillo
    Balance: $-1100.0
    Tasa de interes: 10.0%
    Sin penalizacion por sobregiro

    BUILD SUCCESSFUL (total time: 0 seconds)

 */
