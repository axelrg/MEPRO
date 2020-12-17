package juego.control;

import juego.modelo.Tablero;
import juego.util.CoordenadasIncorrectasException;

/**
 * La clase Arbitro Abstracto implementa la interfaz Arbitro.
 * En esta clase aglutino los metodos comunes a los dos tipos de arbitros.
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public abstract class ArbitroAbstracto implements Arbitro {
    /**
     * La clase arbitro representa el funcionamiento del buscaminas.
     *
     * @author Axel Rubio Gonzalez
     * @version 1.0
     */

    /**
     * Objeto tablero sobre el que actua el arbitro.
     */
    protected Tablero tablero;

    /**
     * Constructor de la clase, crea un objeto tablero.
     * @param tablero un objeto tablero
     */
    public ArbitroAbstracto(Tablero tablero) {
        this.tablero = tablero;
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
     * Descubre la celda si cumple la condición de estar en estado oculto.
     */
    public void descubrirOcultas() {
        for (int i = 0; i < tablero.NUMERO_FILAS; i++) {
            for (int j = 0; j < tablero.NUMERO_COLUMNAS; j++) {
                try {
                    if (esLegalDescubrir(i, j)) {
                        descubrir(i, j);
                    }
                } catch (CoordenadasIncorrectasException ex) {
                    throw new RuntimeException("Se detiene la ejecucion por una excepcion", ex);
                }

            }
        }
    }

    /**
     * Comprueba si es legal descubrir una celda (solo será legal si está oculta).
     *
     * @param fila    la fila de la celda
     * @param columna la columna de la celda
     * @return booleano, true si es legal, false si no
     * @throws CoordenadasIncorrectasException Las coordenadas no estan en el tablero
     */
    public boolean esLegalDescubrir(int fila, int columna) throws CoordenadasIncorrectasException {
        return tablero.clonarCelda(fila, columna).estaOculta();

    }

    /**
     * Comprueba si es legal desmarcar una celda (solo será legal si está marcada).
     *
     * @param fila    la fila de la celda
     * @param columna la columna de la celda
     * @return booleano, true si es legal, false si no
     * @throws CoordenadasIncorrectasException Las coordenadas no estan en el tablero
     */
    public boolean esLegalDesmarcar(int fila, int columna) throws CoordenadasIncorrectasException {
        return tablero.clonarCelda(fila, columna).estaMarcada();

    }


    /**
     * Comprueba si es legal marcar una celda (solo será legal si está oculta y no hay ya 10 o mas marcadas).
     *
     * @param fila    la fila de la celda
     * @param columna la columna de la celda
     * @return booleano, true si es legal, false si no
     * @throws CoordenadasIncorrectasException Las coordenadas no estan en el tablero
     */
    public boolean esLegalMarcar(int fila, int columna) throws CoordenadasIncorrectasException {
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
                try {
                    if (tablero.clonarCelda(i, j).tieneMina() && tablero.clonarCelda(i, j).estaMarcada() || !tablero.clonarCelda(i, j).tieneMina() && tablero.clonarCelda(i, j).estaDescubierta()) {
                    } else {
                        return false;
                    }
                } catch (CoordenadasIncorrectasException ex) {
                    throw new RuntimeException("Se detiene la ejecucion por una excepcion", ex);
                }

            }
        }
        return true;
    }

    /**
     * Marca y desmarca las celdas.
     *
     * @param fila    la fila de la celda
     * @param columna la columna de la celda
     * @throws CoordenadasIncorrectasException Las coordenadas no estan en el tablero
     */
    public void marcarODesmarcar(int fila, int columna) throws CoordenadasIncorrectasException {
        tablero.marcarDesmarcar(fila, columna);
    }

}
