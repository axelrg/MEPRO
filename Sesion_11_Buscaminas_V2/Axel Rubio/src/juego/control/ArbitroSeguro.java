package juego.control;

import juego.modelo.Tablero;
import juego.util.CoordenadasIncorrectasException;

/**
 * La clase ArbitroSeguro hereda de ArbitroAbstracto.
 * En esta clase se implementa el metodo descubrir que depende del tipo de arbitro empleado.
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class ArbitroSeguro extends ArbitroAbstracto {


    /**
     * Constructor de Arbitro Seguro, simplemente invoca al de la clase de la que hereda (ArbitroAbstracto).
     *
     * @param tablero tablero en el que va a realizarse la partida
     */
    public ArbitroSeguro(Tablero tablero) {
        super(tablero);
    }


    /**
     * Descubre la celda si cumple la condición de ser legal.
     *
     * @param fila    fila de la celda
     * @param columna columna de la celda
     */
    public void descubrir(int fila, int columna) throws CoordenadasIncorrectasException {
        if (esLegalDescubrir(fila, columna)) {
            tablero.descubrir(fila, columna);
        }

    }
}
