package juego.control;
import juego.modelo.Tablero;

/**
 * La clase arbitro representa el funcionamiento del buscaminas.
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class Arbitro {
    /**
     * Objeto tablero sobre el que actua el arbitro.
     * */
    private Tablero tablero;

    /**
     * Constructor de la clase, crea un objeto tablero.
     */
    public Arbitro() {
        tablero = new Tablero();
    }

    /**
     * Devuelve un clon del tablero.
     *
     * @return un objeto tablero clonado.
     */
    public Tablero consultarTablero() {
        return tablero.clonar();
    }

    /**
     * Descubre la celda si cumple la condición de ser legal.
     *
     * @param fila fila de la celda
     * @param columna columna de la celda
     */
    public void descubrir(int fila, int columna) {
        if (esLegalDescubrir(fila, columna)) {
            tablero.descubrir(fila, columna);
        }

    }

    /**
     * Descubre la celda si cumple la condición de estar en estado oculto.
     */
    public void descubrirOcultas() {
        for (int i = 0; i < tablero.NUMERO_FILAS; i++) {
            for (int j = 0; j < tablero.NUMERO_COLUMNAS; j++) {
                if (esLegalDescubrir(i, j)) {
                    descubrir(i, j);
                }
            }
        }
    }

    /**
     * Comprueba si es legal descubrir una celda (solo será legal si está oculta).
     *
     * @param fila la fila de la celda
     * @param columna la columna de la celda
     * @return booleano, true si es legal, false si no
     */
    public boolean esLegalDescubrir(int fila, int columna) {
        return tablero.clonarCelda(fila, columna).estaOculta();

    }

    /**
     * Comprueba si es legal desmarcar una celda (solo será legal si está marcada).
     *
     * @param fila la fila de la celda
     * @param columna la columna de la celda
     * @return booleano, true si es legal, false si no
     */
    public boolean esLegalDesmarcar(int fila, int columna) {
        return tablero.clonarCelda(fila, columna).estaMarcada();

    }


    /**
     * Comprueba si es legal marcar una celda (solo será legal si está oculta y no hay ya 10 o mas marcadas).
     *
     * @param fila la fila de la celda
     * @param columna la columna de la celda
     * @return booleano, true si es legal, false si no
     */
    public boolean esLegalMarcar(int fila, int columna) {
        return tablero.clonarCelda(fila, columna).estaOculta() && tablero.contarBanderas() < 10;
    }

    /**
     * Comprueba si ha explotado alguna mina del tablero.
     *
     * @return booleano, true si han explotado, false si no
     */
    public boolean haExplotadoAlgunaMina() {
        return tablero.contarMinasExplotadas() != 0;
    }

    /**
     * Comprueba si la partida ha finalizado exitosamente (no han explotado minas y están descubiertas el resto de celdas).
     *
     * @return booleano, true si ha finalizado con exito, false si no
     */
    public boolean haFinalizadoConExito() {
        if (haExplotadoAlgunaMina()) {
            return false;
        }
        for (int i = 0; i < tablero.NUMERO_FILAS; i++) {
            for (int j = 0; j < tablero.NUMERO_COLUMNAS; j++) {
                if (tablero.clonarCelda(i, j).tieneMina() && tablero.clonarCelda(i, j).estaMarcada() || !tablero.clonarCelda(i, j).tieneMina() && tablero.clonarCelda(i, j).estaDescubierta()) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Marca y desmarca las celdas.
     *
     * @param fila la fila de la celda
     * @param columna la columna de la celda
     */
    public void marcarODesmarcar(int fila, int columna) {
        tablero.marcarDesmarcar(fila, columna);
    }

}