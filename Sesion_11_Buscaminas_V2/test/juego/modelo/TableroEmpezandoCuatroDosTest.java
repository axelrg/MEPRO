package juego.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import juego.util.CoordenadasIncorrectasException;

/**
 * Tests sobre el estado del tablero inicial 
 * empezando en celda (4,2).
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0 20181008
 */
@DisplayName("Test de integración sobre Tablero comenzando en celda inicial (4,2).")
public class TableroEmpezandoCuatroDosTest extends TableroEmpezandoAncestroTest {

	// Solución de una partida iniciando en (4,2)
	/*
	// @formatter:off
	0	 1  1  1  -  -  -  -  - 
	1	 1  M  2  1  2  1  2  1 
	2	 1  1  2  M  2  M  3  M 
	3	 -  -  1  1  2  2  M  2 
	4	 1  1  -  -  -  1  2  2 
	5	 M  1  -  -  -  -  2  M 
	6	 2  2  2  1  1  -  2  M 
	7	 1  M  2  M  1  -  1  1 

	 	 0  1  2  3  4  5  6  7 
	// @formatter:on
	 */

	/** Posiciones de minas. */
	private static final int[][] POSICIONES_MINAS = { { 1, 1 }, { 2, 3 }, { 2, 5 }, { 2, 7 }, { 3, 6 }, { 5, 0 },
			{ 5, 7 }, { 6, 7 }, { 7, 1 }, { 7, 3 } };

	@Override
	int[][] obtenerPosicionMinas() {
		return POSICIONES_MINAS;
	}

	@Override
	int obtenerFilaInicio() {
		return 4;
	}

	@Override
	int obtenerColumnaInicio() {
		return 2;
	}

	/**
	 * Comprueba las celdas con valores 0 minas adyacentes al descubrir la celda inicial.
	 * 
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero 
	 */
	@DisplayName("Comprueba las celdas con valores 0 minas adyacentes al descubrir la celda inicial")
	@Test
	void comprobarCeldasCero() throws CoordenadasIncorrectasException {
		int[][] posiciones = { { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 }, { 3, 0 }, { 3, 1 }, { 4, 2 }, { 4, 3 },
				{ 4, 4 }, { 5, 2 }, { 5, 3 }, { 5, 4 }, { 5, 5 }, { 6, 5 }, { 7, 5 } };
		comprobarNumero(posiciones, 0);
	}

	/**
	 * Comprueba las celdas con valores 1 mina adyacente al descubrir la celda inicial.
	 * 
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero 
	 */
	@DisplayName("Comprueba las celdas con valores 1 mina adyacente al descubrir la celda inicial")
	@Test
	void comprobarCeldasUno() throws CoordenadasIncorrectasException {
		int[][] posiciones = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 3 }, { 1, 5 }, { 1, 7 }, { 2, 0 }, { 2, 1 },
				{ 3, 2 }, { 3, 3 }, { 4, 0 }, { 4, 1 }, { 4, 5 }, { 5, 1 }, { 6, 3 }, { 6, 4 }, { 7, 0 }, { 7, 4 },
				{ 7, 6 } };
		comprobarNumero(posiciones, 1);
	}

	/**
	 * Comprueba las celdas con valores 2 mina adyacente al descubrir la celda inicial.
	 * 
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero 
	 */
	@DisplayName("Comprueba las celdas con valores 2 mina adyacente al descubrir la celda inicial")
	@Test
	void comprobarCeldasDos() throws CoordenadasIncorrectasException {
		int[][] posiciones = { 
				{ 1, 2 }, { 1, 4 }, { 1, 6 }, 
				{ 2, 2 }, { 2, 4 }, 
				{ 3, 4 }, { 3, 5 }, { 3, 7 }, 
				{ 4, 6 }, { 4, 7 }, 
				{ 5, 6 }, 
				{ 6, 0 }, { 6, 1 }, { 6, 2 }, { 6, 6 }, 
				{ 7, 2 } };
		comprobarNumero(posiciones, 2);
	}

	/**
	 * Comprueba las celdas con valores 3 minas adyacente al descubrir la celda inicial.
	 * 
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero 
	 */
	@DisplayName("Comprueba las celdas con valores 3 minas adyacente al descubrir la celda inicial")
	@Test
	void comprobarCeldasTres() throws CoordenadasIncorrectasException {
		int[][] posiciones = { { 2, 6 } };
		comprobarNumero(posiciones, 3);
	}

	@Override
	String obtenerEstadoTrasDescubrirPrimeraCelda() {
		// @formatter:off
		return 	  "0	 -  -  -  -  -  -  -  -" 
		        + "1	 -  -  -  -  -  -  -  -" 
			    + "2	 1  1  2  -  -  -  -  -"
				+ "3	 .  .  1  1  2  2  -  -" 
			    + "4	 1  1  .  .  .  1  2  -" 
				+ "5	 -  1  .  .  .  .  2  -"
				+ "6	 -  2  2  1  1  .  2  -" 
				+ "7	 -  -  -  -  1  .  1  -" 
				      + "0  1  2  3  4  5  6  7";
		// @formatter:on
	}

	@Override
	String obtenerSolucionTrasDescubrirPrimeraCelda() {
		// @formatter:off
		return  "0	 1  1  1  -  -  -  -  -"
			  + "1	 1  M  2  1  2  1  2  1"
		      + "2	 1  1  2  M  2  M  3  M" 
		      + "3	 -  -  1  1  2  2  M  2" 
		      + "4	 1  1  -  -  -  1  2  2"
		      + "5	 M  1  -  -  -  -  2  M"
		      + "6	 2  2  2  1  1  -  2  M"
		      + "7	 1  M  2  M  1  -  1  1"
		      + 	"0  1  2  3  4  5  6  7";
		// @formatter:on
	}

	@Override
	String obtenerEstadoTrasMarcarTodasLasMinas() {
		// @formatter:off
		return 	  "0	 -  -  -  -  -  -  -  -" 
		        + "1	 -  P  -  -  -  -  -  -" 
			    + "2	 1  1  2  P  -  P  -  P"
				+ "3	 .  .  1  1  2  2  P  -" 
			    + "4	 1  1  .  .  .  1  2  -" 
				+ "5	 P  1  .  .  .  .  2  P"
				+ "6	 -  2  2  1  1  .  2  P" 
				+ "7	 -  P  -  P  1  .  1  -" 
				      + "0  1  2  3  4  5  6  7";
		// @formatter:on
	}

	@Override
	String obtenerEstadoTrasDescubirTodasLasMinas() {
		// @formatter:off
		return 	  "0	 -  -  -  -  -  -  -  -" 
				+ "1	 -  M  -  -  -  -  -  -" 
				+ "2	 1  1  2  M  -  M  -  M"
				+ "3	 .  .  1  1  2  2  M  -" 
				+ "4	 1  1  .  .  .  1  2  -" 
				+ "5	 M  1  .  .  .  .  2  M"
				+ "6	 -  2  2  1  1  .  2  M" 
				+ "7	 -  M  -  M  1  .  1  -" 
				      + "0  1  2  3  4  5  6  7";
		// @formatter:on
	}

	/**
	 * Descubrir una mina justo después de descubrir la celda inicial.
	 * 
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	@DisplayName("Descubrir una mina justo después de descubrir la celda inicial")
	@Test
	void comprobarDescubrirUnaMina() throws CoordenadasIncorrectasException {
		descubrirCeldaInicial();
		System.out.println(tablero.toString());
		tablero.descubrir(7, 1);
		System.out.println(tablero.toString());
		// @formatter:off
		String estadoActualEsperado = 
				  "0	 -  -  -  -  -  -  -  -" 
		        + "1	 -  -  -  -  -  -  -  -" 
			    + "2	 1  1  2  -  -  -  -  -"
				+ "3	 .  .  1  1  2  2  -  -" 
			    + "4	 1  1  .  .  .  1  2  -" 
				+ "5	 -  1  .  .  .  .  2  -"
				+ "6	 -  2  2  1  1  .  2  -" 
				+ "7	 -  M  -  -  1  .  1  -" 
				      + "0  1  2  3  4  5  6  7";
		// @formatter:on
		String cadenaEsperada = limpiarCadena(estadoActualEsperado);
		String cadenaObtenida = limpiarCadena(tablero.toString());

		assertAll("descubrirUnaMina",
				() -> assertThat("El número de celdas descubiertas no es correcto.",
						tablero.contarCeldasDescubiertas(), is(32)),
				() -> assertThat(tablero.contarMinasExplotadas(), is(1)), () -> assertEquals(cadenaEsperada,
						cadenaObtenida, "El texto a mostrar tras explotar una mina es incorrecto"));
	}

	/**
	 * Descubrir otra celda (0,6) después de descubrir la celda inicial.
	 * 
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	@DisplayName("Descubrir otra celda (0,6) después de descubrir la celda inicial")
	@Test
	void comprobarDescubrirOtraCelda() throws CoordenadasIncorrectasException {
		descubrirCeldaInicial();
		tablero.descubrir(0, 6);
		// @formatter:off
				String estadoActualEsperado = 
						  "0	 -  -  1  .  .  .  .  ." 
				        + "1	 -  -  2  1  2  1  2  1" 
					    + "2	 1  1  2  -  -  -  -  -"
						+ "3	 .  .  1  1  2  2  -  -" 
					    + "4	 1  1  .  .  .  1  2  -" 
						+ "5	 -  1  .  .  .  .  2  -"
						+ "6	 -  2  2  1  1  .  2  -" 
						+ "7	 -  -  -  -  1  .  1  -" 
						      + "0  1  2  3  4  5  6  7";
		// @formatter:on
		String cadenaEsperada = limpiarCadena(estadoActualEsperado);
		String cadenaObtenida = limpiarCadena(tablero.toString());

		assertAll("descubrirUnaCeldaAdicional(0,6)",
				() -> assertThat("El número de celdas descubiertas no es correcto.",
						tablero.contarCeldasDescubiertas(), is(43)),
				() -> assertThat("No debería haber ninguna mina explotada", tablero.contarMinasExplotadas(),
						is(0)),
				() -> assertEquals(cadenaEsperada, cadenaObtenida,
						"El texto a mostrar tras descubrir la celda (0,6) es incorrecto"));
	}

}
