/**
 *
 * @author Axel Rubio Gonzalez
 *
 */

package juego.modelo;

public class Celda {
    private int fila;
    private int columna;
    private Pieza pieza;

    public Celda(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public Pieza obtenerPieza() {
        return pieza;
    }

    public void establecerPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public boolean estaVacia() {
        if (pieza == null)
            return true;
        else
            return false;
    }

    public int obtenerFila() {
        return fila;
    }

    public int obtenerColumna() {
        return columna;
    }

    public String toString() {
        return String.format("(%d/%d)", fila, columna);
    }
}


