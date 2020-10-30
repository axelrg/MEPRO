package juego.modelo;

/**
 * La clase celda representa una celda del tablero del tres en raya.
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class Celda {
    /**
     * fila de la celda.
     */
    private int fila;
    /**
     * columna de la celda.
     */
    private int columna;
    /**
     * pieza de la celda.
     */
    private Pieza pieza = null;

    /**
     * Constructor de la clase, crea una celda con fila y columna.
     *
     * @param fila fila de la celda
     * @param columna columna de la celda
     */
    public Celda(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Getter del parametro pieza.
     *
     * @return devuelve la pieza
     */
    public Pieza obtenerPieza() {
        return pieza;
    }

    /**
     * Le asigna a la pieza una celda.
     *
     * @param pieza la pieza a asignar
     */
    public void establecerPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    /**
     * Comprueba si la celda esta vacia.
     *
     * @return devuelve true o false dependiendo del estado (vacia, llena)
     */
    public boolean estaVacia() {
        if (pieza == null)
            return true;
        else
            return false;
    }

    /**
     * Getter de fila.
     *
     * @return la fila
     */
    public int obtenerFila() {
        return fila;
    }

    /**
     * Getter de columna.
     *
     * @return la columna
     */
    public int obtenerColumna() {
        return columna;
    }

    /**
     * Devuelve el estado de la celda en un string.
     *
     * @return un string
     */
    public String toString() {
        return String.format("(%d/%d)", fila, columna);
    }
}


