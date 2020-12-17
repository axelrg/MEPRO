package juego.control;

import juego.modelo.Tablero;
import juego.util.CoordenadasIncorrectasException;

/**
 * La clase ArbitroInseguro hereda de ArbitroAbstracto.
 * En esta clase se implementa el metodo descubrir que depende del tipo de arbitro empleado.
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class ArbitroInseguro extends ArbitroAbstracto {


    /**
     * Constructor de Arbitro Inseguro, simplemente invoca al de la clase de la que hereda (ArbitroAbstracto).
     *
     * @param tablero tablero en el que va a realizarse la partida
     */
    public ArbitroInseguro(Tablero tablero) {
        super(tablero);
    }

    /**
     * Descubre la celda si cumple la condición de ser legal.
     * Coloca las minas antes de que se realice el primer movimiento.
     *
     * @param fila    fila de la celda
     * @param columna columna de la celda
     */
    public void descubrir(int fila, int columna) throws CoordenadasIncorrectasException {
        if (tablero.contarMinas() == 0) {
            tablero.colocarMinas();
        }
        if (esLegalDescubrir(fila, columna)) {
            tablero.descubrir(fila, columna);
        }
    }
}
