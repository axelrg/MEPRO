package juego.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests sobre el estado del tablero inicial 
 * empezando en celda (0,0).
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0 20181008
 */
@DisplayName("Test de integración sobre Tablero comenzando en celda inicial (0,0).")
public class TableroEmpezandoCeroCeroTest extends TableroEmpezandoAncestroTest {
	
	// Solución de una partida iniciando en (4,2)
	/*
	// @formatter:off
	0	 -  -  -  1  1  1  -  - 
	1	 -  1  1  2  M  1  -  - 
	2	 -  1  M  3  2  2  -  - 
	3	 1  2  1  2  M  2  1  1 
	4	 M  2  1  2  1  3  M  2 
	5	 1  2  M  1  -  2  M  2 
	6	 2  3  2  1  -  1  2  2 
	7	 M  M  1  -  -  -  1  M 

		 0  1  2  3  4  5  6  7 
	// @formatter:on
	*/

	/** Posiciones de minas. */
	private static final int[][] POSICIONES_MINAS = { { 1, 4 }, { 2, 2 }, { 3, 4 }, { 4, 0 }, { 4, 6 }, { 5, 2 },
			{ 5, 6 }, { 7, 0 }, { 7, 1 }, { 7, 7 } };

	@Override
	int[][] obtenerPosicionMinas() {
		return POSICIONES_MINAS;
	}

	@Override
	int obtenerFilaInicio() {
		return 0;
	}

	@Override
	int obtenerColumnaInicio() {
		return 0;
	}

	/**
	 * Comprueba las celdas con valores 0 minas adyacentes al descubrir la celda inicial (0,0).
	 */
	@DisplayName("Comprueba las celdas con valores 0 minas adyacentes al descubrir la celda inicial (0,0)")
	@Test
	void comprobarCeldasCero() {
		int[][] posiciones = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 6 }, { 0, 7 }, { 1, 0 }, { 1, 6 }, { 1, 7 }, { 2, 0 },
				{ 2, 6 }, { 2, 7 }, { 5, 4 }, { 6, 4 }, { 7, 3 }, { 7, 4 }, { 7, 5 } };
		comprobarNumero(posiciones, 0);
	}

	/**
	 * Comprueba las celdas con valores 1 mina adyacente al descubrir la celda inicial (0,0).
	 */
	@DisplayName("Comprueba las celdas con valores 1 mina adyacente al descubrir la celda inicial (0,0)")
	@Test
	void comprobarCeldasUno() {
		int[][] posiciones = { { 0, 3 }, { 0, 4 }, { 0, 5 }, { 1, 1 }, { 1, 2 }, { 1, 5 }, { 2, 1 }, { 3, 0 }, { 3, 2 },
				{ 3, 6 }, { 3, 7 }, { 4, 2 }, { 4, 4 }, { 5, 0 }, { 5, 3 }, { 6, 3 }, { 6, 5 }, { 7, 2 }, { 7, 6 } };
		comprobarNumero(posiciones, 1);
	}

	/**
	 * Comprueba las celdas con valores 2 mina adyacente al descubrir la celda inicial (0,0).
	 */
	@DisplayName("Comprueba las celdas con valores 2 mina adyacente al descubrir la celda inicial (0,0)")
	@Test
	void comprobarCeldasDos() {
		int[][] posiciones = { { 1, 3 }, { 2, 4 }, { 2, 5 }, { 3, 1 }, { 3, 3 }, { 3, 5 }, { 4, 1 }, { 4, 3 }, { 4, 7 },
				{ 5, 1 }, { 5, 5 }, { 5, 7 }, { 6, 0 }, { 6, 2 }, { 6, 6 }, { 6, 7 } };
		comprobarNumero(posiciones, 2);
	}

	/**
	 * Comprueba las celdas con valores 3 minas adyacente al descubrir la celda inicial (0,0).
	 */
	@DisplayName("Comprueba las celdas con valores 3 minas adyacente al descubrir la celda inicial (0,0)")
	@Test
	void comprobarCeldasTres() {
		int[][] posiciones = { { 2, 3 }, { 4, 5 }, { 6, 1 } };
		comprobarNumero(posiciones, 3);
	}

	/**
	 * Comprueba las celdas descubiertas al descubrir la celda inicial (0,0).
	 */
	@DisplayName("Comprueba las celdas descubiertas al descubrir la celda inicial (0,0)")
	@Test
	void comprobarCeldasDescubiertasAlDescubrirInicial() {
		descubrirCeldaInicial();
		int[][] posiciones = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 1, 3 }, { 2, 0 },
				{ 2, 1 }, { 3, 0 }, { 3, 1 } };
		comprobarCeldaDescubiertasInicialmente(posiciones);
	}

	@Override
	String obtenerEstadoTrasDescubrirPrimeraCelda() {
		// @formatter:off
		return 
				  "0	 .  .  .  1  -  -  -  -" 
				+ "1	 .  1  1  2  -  -  -  -" 
				+ "2	 .  1  -  -  -  -  -  -"
			    + "3	 1  2  -  -  -  -  -  -" 
				+ "4	 -  -  -  -  -  -  -  -" 
				+ "5	 -  -  -  -  -  -  -  -"
				+ "6	 -  -  -  -  -  -  -  -" 
				+ "7	 -  -  -  -  -  -  -  -" 
				+ "0  1  2  3  4  5  6  7";
		// @formatter:on
	}

	@Override
	String obtenerSolucionTrasDescubrirPrimeraCelda() {
		// @formatter:off
		return 
				  "0	 -  -  -  1  1  1  -  -" 
				+ "1	 -  1  1  2  M  1  -  -" 
				+ "2	 -  1  M  3  2  2  -  -"
				+ "3	 1  2  1  2  M  2  1  1" 
				+ "4	 M  2  1  2  1  3  M  2" 
				+ "5	 1  2  M  1  -  2  M  2"
				+ "6	 2  3  2  1  -  1  2  2" 
				+ "7	 M  M  1  -  -  -  1  M" 
				+ "0  1  2  3  4  5  6  7";
		// @formatter:on
	}

	@Override
	String obtenerEstadoTrasMarcarTodasLasMinas() {
		// @formatter:off
		return 
				  "0	 .  .  .  1  -  -  -  -" 
		        + "1	 .  1  1  2  P  -  -  -" 
			    + "2	 .  1  P  -  -  -  -  -"
				+ "3	 1  2  -  -  P  -  -  -" 
			    + "4	 P  -  -  -  -  -  P  -" 
				+ "5	 -  -  P  -  -  -  P  -"
				+ "6	 -  -  -  -  -  -  -  -" 
				+ "7	 P  P  -  -  -  -  -  P" 
				+ "0  1  2  3  4  5  6  7";
		// @formatter:on
	}

	@Override
	String obtenerEstadoTrasDescubirTodasLasMinas() {
		// @formatter:off
				return 
						  "0	 .  .  .  1  -  -  -  -" 
				        + "1	 .  1  1  2  M  -  -  -" 
					    + "2	 .  1  M  -  -  -  -  -"
						+ "3	 1  2  -  -  M  -  -  -" 
					    + "4	 M  -  -  -  -  -  M  -" 
						+ "5	 -  -  M  -  -  -  M  -"
						+ "6	 -  -  -  -  -  -  -  -" 
						+ "7	 M  M  -  -  -  -  -  M" 
						+ "0  1  2  3  4  5  6  7";
				// @formatter:on
	}

	/**
	 * Descubrir una mina justo después de descubrir la celda inicial.
	 */
	@DisplayName("Descubrir una mina justo después de descubrir la celda inicial")
	@Test
	void comprobarDescubrirUnaMina() {
		descubrirCeldaInicial();
		tablero.descubrir(1, 4);
		// @formatter:off
		String estadoActualEsperado = 
				  "0	 .  .  .  1  -  -  -  -" 
		        + "1	 .  1  1  2  M  -  -  -" 
			    + "2	 .  1  -  -  -  -  -  -"
				+ "3	 1  2  -  -  -  -  -  -" 
			    + "4	 -  -  -  -  -  -  -  -" 
				+ "5	 -  -  -  -  -  -  -  -"
				+ "6	 -  -  -  -  -  -  -  -" 
				+ "7	 -  -  -  -  -  -  -  -" 
				+ "0  1  2  3  4  5  6  7";
		// @formatter:on
		String cadenaEsperada = limpiarCadena(estadoActualEsperado);
		String cadenaObtenida = limpiarCadena(tablero.toString());

		assertAll("descubrirUnaMina",
				() -> assertThat("El número de celdas descubiertas no es correcto.",
						tablero.contarCeldasDescubiertas(), is(13)),
				() -> assertThat(tablero.contarMinasExplotadas(), is(1)), () -> assertEquals(cadenaEsperada,
						cadenaObtenida, "El texto a mostrar tras explotar una mina es incorrecto"));
	}

	/**
	 * Descubrir otra celda (5,4) después de descubrir la celda inicial.
	 */
	@DisplayName("Descubrir otra celda (5,4) después de descubrir la celda inicial")
	@Test
	void comprobarDescubrirOtraCelda() {
		descubrirCeldaInicial();
		tablero.descubrir(5, 4);
		// @formatter:off
		String estadoActualEsperado = 
				  "0	 .  .  .  1  -  -  -  -" 
		        + "1	 .  1  1  2  -  -  -  -" 
			    + "2	 .  1  -  -  -  -  -  -"
				+ "3	 1  2  -  -  -  -  -  -" 
			    + "4	 -  -  -  2  1  3  -  -" 
				+ "5	 -  -  -  1  .  2  -  -"
				+ "6	 -  -  2  1  .  1  2  -" 
				+ "7	 -  -  1  .  .  .  1  -" 
				      + "0  1  2  3  4  5  6  7";
		// @formatter:on
		String cadenaEsperada = limpiarCadena(estadoActualEsperado);
		String cadenaObtenida = limpiarCadena(tablero.toString());

		assertAll("descubrirUnaCeldaAdicional(5,4)",
				() -> assertThat("El número de celdas descubiertas no es correcto.",
						tablero.contarCeldasDescubiertas(), is(28)),
				() -> assertThat("No debería haber ninguna mina explotada", tablero.contarMinasExplotadas(), is(0)),
				() -> assertEquals(cadenaEsperada, cadenaObtenida,
						"El texto a mostrar tras descubrir la celda (5,4) es incorrecto"));
	}

}
