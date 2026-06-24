
import java.util.ArrayList;

/**
 * Problema01: Dibuje un diagrama de clases que muestre la estructura de un
 * capítulo de libro; un capítulo está compuesto por varias secciones, cada una
 * de las cuales comprende varios párrafos y figuras. Un párrafo incluye varias
 * sentencias, cada una de las cuales contiene varias palabras.
 *
 * Suponga que en un futuro se prevé que el sistema gestione además de párrafos
 * y figuras otros componentes, como tablas, listas, viñetas, etc. Suponga
 * además que una palabra puede aparecer en varias sentencias.
 *
 * @author Matthew Castillo
 * @version 1.0
 */
class Palabra {

    public String texto;
    public int longitud;

    public Palabra(String texto) {
    }

    public String getTexto() {
        return texto;
    }

    @Override
    public String toString() {
        return "";
    }
}

class Sentencia {

    public int orden;
    public ArrayList<Palabra> palabras;

    public Sentencia(int orden) {
    }

    public ArrayList<Palabra> getPalabras() {
        return palabras;
    }

    @Override
    public String toString() {
        return "";
    }
}

abstract class ComponenteSeccion {

    public abstract void mostrar();

    public abstract String getTipo();

    @Override
    public String toString() {
        return "";
    }
}

class Parrafo extends ComponenteSeccion {

    public int numero;
    public int numSentencias;
    private ArrayList<Sentencia> sentencias;

    public Parrafo(int numero) {
    }

    public ArrayList<Sentencia> getSentencias() {
        return sentencias;
    }

    @Override
    public void mostrar() {
    }

    @Override
    public String getTipo() {
        return "";
    }
}

class Figura extends ComponenteSeccion {

    public String formato;

    public Figura(String formato) {
    }

    public String getFormato() {
        return formato;
    }

    @Override
    public void mostrar() {
    }

    @Override
    public String getTipo() {
        return "";
    }
}

class Seccion {

    public String titulo;
    private ArrayList<ComponenteSeccion> componentes;

    public Seccion(String titulo) {
    }

    public ArrayList<ComponenteSeccion> getComponentes() {
        return componentes;
    }

    @Override
    public String toString() {
        return "";
    }
}

class Capitulo {

    public String titulo;
    public int numero;
    private ArrayList<Seccion> secciones;

    public Capitulo(String titulo, int numero) {
    }

    public ArrayList<Seccion> getSecciones() {
        return secciones;
    }

    @Override
    public String toString() {
        return "";
    }
}

public class Problema_1_EjecutorLibros {

    public static void main(String[] args) {
    }
}
/**
 * Run:
 * BUILD SUCCESSFUL (total time: 0 seconds)
 */
