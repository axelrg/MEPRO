package juego.modelo;

import juego.util.Direccion;

import java.util.Arrays;

/**
 * La clase tablero representa el tablero sobre el que vamos a colocar las piezas.
 * El tablero puede ser de diferentes tamaños
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class Tablero {

    /**
     * Matriz de celdas (forma el tablero).
     */
    Celda[][] tablero;

    /**
     * Constructor de la clase, crea una pieza con un color determinado.
     * Comprueba que no introduzcamos un numero negativo o cero.
     *
     * @param filas    numero de filas que va a tener el tablero
     * @param columnas numero de columnas del tablero
     */
    public Tablero(int filas, int columnas) {

        if (filas < 0)
            filas = -filas;

        if (columnas < 0)
            columnas = -columnas;

        if (columnas == 0)
            columnas = 1;

        if (filas == 0)
            filas = 1;


        this.tablero = new Celda[filas][columnas];

        for (int fila = 0; fila < obtenerNumeroFilas(); fila++) {
            for (int columna = 0; columna < obtenerNumeroColumnas(); columna++) {
                tablero[fila][columna] = new Celda(fila, columna);

            }
        }
    }

    /**
     * Asigna la celda a la pieza y viceversa.
     *
     * @param celda objeto de tipo celda
     * @param pieza objeto de tipo pieza
     */
    public void colocar(Pieza pieza, Celda celda) {
        pieza.establecerCelda(celda);
        celda.establecerPieza(pieza);
    }

    /**
     * Devuelve la celda de las coordenadas dadas.
     *
     * @param fila    coordenada de la fila
     * @param columna coordenada de la pieza
     * @return devuelve la celda de las coordenadas
     */
    public Celda obtenerCelda(int fila, int columna) {
        if (estaEnTablero(fila, columna))
            return tablero[fila][columna];

        return null;
    }

    /**
     * Devuelve true o false en función de si las coordenadas estan en el tablero.
     *
     * @param fila    coordenada de la fila
     * @param columna coordenada de la pieza
     * @return booleano que indica si esta o no
     */
    public boolean estaEnTablero(int fila, int columna) {
        if (fila < 0 | columna < 0)
            return false;

        if (fila < obtenerNumeroFilas() & columna < obtenerNumeroColumnas())
            return true;

        return false;
    }

    /**
     * Nos proporciona el numero de piezas.
     *
     * @param d1 coordenada de la fila
     * @param d2 coordenada de la pieza
     * @return el numero de piezas en esa direccion
     */
    private int piezas(Celda celda, int d1, int d2) {
        /**Numero de piezas*/
        int piezas = 0;
        /**Contador*/
        int i = 0;
        for (int j = 0; j < 2; j++) {

            if (j > 0) {
                d2 = -d2;
                d1 = -d1;
                i = 1;
            }

            while (estaEnTablero(celda.obtenerFila() + i * d1, celda.obtenerColumna() + i * d2)) {
                if (celda.obtenerPieza() != null && tablero[celda.obtenerFila() + i * d1][celda.obtenerColumna() + i * d2].obtenerPieza() != null && celda.obtenerPieza().obtenerColor() == tablero[celda.obtenerFila() + i * d1][celda.obtenerColumna() + i * d2].obtenerPieza().obtenerColor()) {
                    piezas++;
                    i++;
                } else
                    break;
            }
        }
        return piezas;
    }

    /**
     * En función de la direccion llama de una forma u otra al metodo piezas.
     *
     * @param celda     celda a partir de la que se calcula
     * @param direccion direccion de la que queremos calcular las piezas
     * @return numero de piezas
     */
    public int contarPiezas(Celda celda, Direccion direccion) {
        /**Numero de piezas*/
        int piezas = 0;
        switch (direccion) {


            case HORIZONTAL:
                piezas = piezas(celda, 0, 1);
                break;

            case VERTICAL:
                piezas = piezas(celda, 1, 0);
                break;

            case DIAGONAL_NO_SE:
                piezas = piezas(celda, 1, 1);
                break;

            case DIAGONAL_SO_NE:
                piezas = piezas(celda, 1, -1);
                break;
        }


        return piezas;
    }

    /**
     * Devuelve el numero de piezas en el tablero de un determinado color.
     *
     * @param color color de la pieza que queremos contar
     * @return entero con el numero de piezas
     */
    public int obtenerNumeroPiezas(Color color) {
        /**Numero de piezas*/
        int piezas = 0;
        for (int i = 0; i < obtenerNumeroFilas(); i++) {
            for (int j = 0; j < obtenerNumeroColumnas(); j++) {
                if (tablero[i][j].obtenerPieza() != null && tablero[i][j].obtenerPieza().obtenerColor() == color) {
                    piezas++;
                }
            }
        }
        return piezas;
    }

    /**
     * Devuelve el numero de filas del tablero.
     *
     * @return entero con el numero de filas
     */
    public int obtenerNumeroFilas() {
        return tablero.length;
    }

    /**
     * Devuelve el numero de columnas del tablero.
     *
     * @return entero con el numero de columnas
     */
    public int obtenerNumeroColumnas() {
        return tablero[0].length;
    }

    /**
     * Devuelve el numero de filas del tablero.
     *
     * @return booleano dependiendo del estado el tablero (completo, incompleto)
     */
    public boolean estaCompleto() {
        for (int i = 0; i < obtenerNumeroFilas(); i++) {
            for (int j = 0; j < obtenerNumeroColumnas(); j++) {
                if (tablero[i][j].estaVacia()) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Devuelve el tablero en formato string.
     *
     * @return string del tablero
     */
    public String toString() {
        String string = "";
        for (int fila = 0; fila < obtenerNumeroFilas(); fila++) {
            if (fila != 0)
                string = string + '\n';
            for (int columna = 0; columna < obtenerNumeroColumnas(); columna++) {
                if (!tablero[fila][columna].estaVacia())
                    string = string + tablero[fila][columna].obtenerPieza().obtenerColor().toChar();
                else
                    string = string + '-';
            }
        }

        return string;
    }

}

