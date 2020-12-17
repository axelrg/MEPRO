package juego.control;

import juego.modelo.Tablero;
import juego.util.CoordenadasIncorrectasException;

/**
 * Arbitro. Comportamiento común para todos los árbitros.
 * 
 * @author <a href="rmartico@ubu.es">Raúl Marticorena</a>
 * @since 1.0
 * @version 1.0
 */
public interface Arbitro {

	/**
	 * Comprueba si la partida está finalizada.
	 * 
	 * Una partida está finalizada si todas las celdas descubiertas no son minas y
	 * se han colocado la minas.
	 * 
	 * @return true si está acabada, false en caso contrario
	 */
	boolean haFinalizadoConExito();

	/**
	 * Comprueba si ha explotado alguna mina.
	 * 
	 * @return true si ha explotado alguna mina, false en caso contrario
	 */
	boolean haExplotadoAlgunaMina();

	/**
	 * Descubre celdas a partir de la celda indicada.
	 * 
	 * @param fila fila
	 * @param columna columna
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	void descubrir(int fila, int columna) throws CoordenadasIncorrectasException;

	/**
	 * Descubre todas las celdas ocultas actualmente.
	 */
	void descubrirOcultas();

	/**
	 * Marca o desmarca la celda si es posible.
	 * 
	 * @param fila fila
	 * @param columna columna
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	void marcarODesmarcar(int fila, int columna) throws CoordenadasIncorrectasException;

	/**
	 * Comprueba si es legal descubrir la celda.
	 * 
	 * @param fila fila
	 * @param columna columna
	 * @return true si es legal descubrir, false en caso contrario
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	boolean esLegalDescubrir(int fila, int columna) throws CoordenadasIncorrectasException;

	/**
	 * Comprueba si es legal marcar la celda.
	 * 
	 * @param fila fila
	 * @param columna columna
	 * @return true si es legal marcar, false en caso contrario
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	boolean esLegalMarcar(int fila, int columna) throws CoordenadasIncorrectasException;

	/**
	 * Comprueba si es legal desmarcar la celda.
	 * 
	 * @param fila fila
	 * @param columna columna
	 * @return true si es legal desmarcar, false en caso contrario
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	boolean esLegalDesmarcar(int fila, int columna) throws CoordenadasIncorrectasException;

	/**
	 * Clona un tablero.
	 * 
	 * @return clon del tablero actual solo para consulta
	 */
	Tablero consultarTablero();

}