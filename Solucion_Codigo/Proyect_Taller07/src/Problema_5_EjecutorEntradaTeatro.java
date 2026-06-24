
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Problema05: Dadas las siguientes clases, que expresan una relación de
 * herencia entre las entidades:
 *
 * Se desea gestionar la venta de entradas para un espectáculo en un teatro. El
 * patio de butacas del teatro se divide en varias zonas, cada una identificada
 * por su nombre. Los datos de las zonas son los mostrados en la siguiente
 * tabla: Para realizar la compra de una entrada, un espectador debe indicar la
 * zona que desea y presentar al vendedor el documento que justifique que tiene
 * algún tipo de descuento (estudiante, abonado o pensionista). El vendedor
 * sacará la entrada del tipo apropiado y de la zona indicada, en el momento de
 * la compra se asignará a la entrada un identificador (un número entero) que
 * permitirá la identificación de la entrada en todas las operaciones que
 * posteriormente se desee realizar con ella.
 *
 * Una entrada tiene como datos asociados su identificador, la zona a la que
 * pertenece y el nombre del comprador. Los precios de las entradas dependen de
 * la zona y del tipo de entrada según lo explicado a continuación: Entradas
 * normales: su precio es el precio normal de la zona elegida sin ningún tipo de
 * descuento. Entradas reducidas (para estudiantes o pensionistas): su precio
 * tiene una rebaja del 15% sobre el precio normal de la zona elegida. Entradas
 * abonado: su precio es el precio para abonados de la zona elegida. La
 * interacción entre el vendedor y la aplicación es la descrita en los
 * siguientes casos de usos.
 *
 * Caso de uso “Vende entrada”:
 *
 * El vendedor elige la opción “vende entrada” e introduce la zona deseada, el
 * nombre del espectador y el tipo (normal, abonado o beneficiario de entrada
 * reducida). Si la zona elegida no está completa, la aplicación genera una
 * nueva entrada con los datos facilitados. Si no existe ninguna zona con ese
 * nombre, se notifica y finaliza el caso de uso sin generar la entrada. Si la
 * zona elegida está completa lo notifica y finaliza el caso de uno sin generar
 * la entrada. La aplicación muestra el identificador y el precio de la entrada.
 * Caso de uso “Consulta entrada”:
 *
 * El vendedor elige la opción “consulta entrada” e introduce el identificador
 * de la entrada. La aplicación muestra los datos de la entrada: nombre del
 * espectador, precio y nombre de la zona. Si no existe ninguna entrada con ese
 * identificador, lo notifica y finaliza el caso de uso
 *
 * @author Matthew Castillo
 * @version 1.0
 */
class Zona {

    public String nombre;
    public int numLocalidades;
    public double precioNormal;
    public double precioAbonado;
    public int localidadesOcupadas;

    public Zona(String nombre, int numLocalidades, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.numLocalidades = numLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
        this.localidadesOcupadas = 0;
    }

    public boolean estaCompleta() {
        return localidadesOcupadas >= numLocalidades;
    }

    public void ocuparLocalidad() {
        localidadesOcupadas++;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Zona: " + nombre
                + " | Localidades: " + localidadesOcupadas + "/" + numLocalidades
                + " | Precio normal: $" + precioNormal
                + " | Precio abonado: $" + precioAbonado;
    }
}

abstract class Entrada {

    public int id;
    public Zona zona;
    public String nombreComprador;

    public Entrada(int id, Zona zona, String nombreComprador) {
        this.id = id;
        this.zona = zona;
        this.nombreComprador = nombreComprador;
    }

    public double calcularPrecio() {
        return zona.precioNormal;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + "\nComprador: " + nombreComprador
                + "\nZona: " + zona.getNombre()
                + "\nPrecio: $" + calcularPrecio();
    }
}

class EntradaNormal extends Entrada {

    public EntradaNormal(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio() {
        return zona.precioNormal;
    }

    @Override
    public String toString() {
        return "=== Entrada Normal ===\n" + super.toString();
    }
}

class EntradaReducida extends Entrada {

    public double descuento = 0.15;

    public EntradaReducida(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio() {
        return zona.precioNormal * (1 - descuento);
    }

    @Override
    public String toString() {
        return "=== Entrada Reducida (15% descuento) ===\n" + super.toString();
    }
}

class EntradaAbonado extends Entrada {

    public EntradaAbonado(int id, Zona zona, String nombreComprador) {
        super(id, zona, nombreComprador);
    }

    @Override
    public double calcularPrecio() {
        return zona.precioAbonado;
    }

    @Override
    public String toString() {
        return "=== Entrada Abonado ===\n" + super.toString();
    }
}

class Teatro {

    public ArrayList<Zona> zonas;
    public ArrayList<Entrada> entradas;
    private int contadorId;

    public Teatro() {
        this.zonas = new ArrayList<>();
        this.entradas = new ArrayList<>();
        this.contadorId = 1;
    }

    public void agregarZona(Zona z) {
        zonas.add(z);
    }

    public Zona buscarZona(String nombre) {
        for (Zona z : zonas) {
            if (z.getNombre().equalsIgnoreCase(nombre)) {
                return z;
            }
        }
        return null;
    }

    public Entrada buscarEntrada(int id) {
        for (Entrada e : entradas) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    public void venderEntrada(String nombreZona, String nombreComprador, String tipo) {
        Zona zona = buscarZona(nombreZona);

        if (zona == null) {
            System.out.println("Error: No existe la zona '" + nombreZona + "'.");
            return;
        }

        if (zona.estaCompleta()) {
            System.out.println("Error: La zona '" + nombreZona + "' está completa.");
            return;
        }

        Entrada entrada;
        switch (tipo.toLowerCase()) {
            case "normal":
                entrada = new EntradaNormal(contadorId++, zona, nombreComprador);
                break;
            case "reducida":
                entrada = new EntradaReducida(contadorId++, zona, nombreComprador);
                break;
            case "abonado":
                entrada = new EntradaAbonado(contadorId++, zona, nombreComprador);
                break;
            default:
                System.out.println("Error: Tipo de entrada desconocido.");
                return;
        }

        zona.ocuparLocalidad();
        entradas.add(entrada);
        System.out.println("Entrada generada exitosamente.");
        System.out.println("ID: " + entrada.id + " | Precio: $" + entrada.calcularPrecio());
    }

    public void consultarEntrada(int id) {
        Entrada entrada = buscarEntrada(id);
        if (entrada == null) {
            System.out.println("Error: No existe ninguna entrada con ID " + id + ".");
            return;
        }
        System.out.println(entrada);
    }

    public double calcularTotalVentas(int id, int cantidad) {
        Entrada entrada = buscarEntrada(id);
        if (entrada == null) {
            System.out.println("Error: No existe ninguna entrada con ID " + id + ".");
            return 0;
        }
        return entrada.calcularPrecio() * cantidad;
    }
}

public class Problema_5_EjecutorEntradaTeatro {

    public static void main(String[] args) {

        Teatro teatro = new Teatro();

        teatro.agregarZona(new Zona("Principal", 200, 25.0, 17.5));
        teatro.agregarZona(new Zona("PalcoB", 40, 70.0, 40.0));
        teatro.agregarZona(new Zona("Central", 400, 20.0, 14.0));
        teatro.agregarZona(new Zona("Lateral", 100, 15.5, 10.0));

        System.out.println("===== VENTA DE ENTRADAS =====\n");

        teatro.venderEntrada("Principal", "Joel Cabrera", "normal");
        System.out.println();
        teatro.venderEntrada("Central", "Melany Abad", "reducida");
        System.out.println();
        teatro.venderEntrada("PalcoB", "Oswaldo Martinez", "abonado");
        System.out.println();
        teatro.venderEntrada("Inexistente", "Mateo Diaz", "normal");
        System.out.println();

        System.out.println("===== CONSULTA DE ENTRADAS =====\n");
        teatro.consultarEntrada(1);
        System.out.println();
        teatro.consultarEntrada(2);
        System.out.println();
        teatro.consultarEntrada(99);
        System.out.println("===== TOTAL VENTAS =====\n");

        System.out.println("Entrada ID 1 x3: " + teatro.calcularTotalVentas(1, 3));

        System.out.println("Entrada ID 2 x2: " + teatro.calcularTotalVentas(2, 2));

        System.out.println("Entrada ID 3 x5: " + teatro.calcularTotalVentas(3, 5));

        System.out.println("Entrada ID 99 x1: " + teatro.calcularTotalVentas(99, 1));
    }
}
/**
 * Run: ===== VENTA DE ENTRADAS =====
 *
 * Entrada generada exitosamente.
 * ID: 1 | Precio: $25.0
 *
 * Entrada generada exitosamente.
 * ID: 2 | Precio: $17.0
 *
 * Entrada generada exitosamente. 
 * ID: 3 | Precio: $40.0
 *
 * Error: No existe la zona 'Inexistente'.
 *
 * ===== CONSULTA DE ENTRADAS =====
 *
 * === Entrada Normal === 
 * ID: 1 
 * Comprador: Joel Cabrera Zona: 
 * Principal Precio:
 * $25.0
 *
 * === Entrada Reducida (15% descuento) === 
 * ID: 2 
 * Comprador: Melany Abad Zona:
 * Central Precio:
 * $17.0
 *
 * Error: No existe ninguna entrada con ID 99. ===== TOTAL VENTAS =====
 *
 * Entrada ID 1 x3: 75.0 
 * Entrada ID 2 x2: 34.0 
 * Entrada ID 3 x5: 200.0 
 * Error: No existe ninguna entrada con ID 99.
 * Entrada ID 99 x1: 0.0 BUILD SUCCESSFUL
 * (total time: 1 second)
 *
 *
 *
 *
 */
