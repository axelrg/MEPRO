package juego.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.MultipleFailuresError;

/**
 * Pruebas unitarias sobre el tablero.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena Sánchez</a>
 * @version 1.0 20181008
 * 
 */
@DisplayName("Test de integración sobre Tablero. Depende del correcto funcionamiento de la celda.")
public class TableroTest {

	/** Tablero de testing. */
	private Tablero tablero;

	/** Inicializa valores para cada test. */
	@BeforeEach
	void inicializar() {
		tablero = new Tablero();
	}

	/**
	 * Revisa la consulta de celdas clonadas en distintas posiciones incorrectas del tablero.
	 */
	@DisplayName("Comprueba la consulta de celdas en posiciones incorrectas del tablero")
	@Test
	void comprobarAccesoACeldasClonadasIncorrectas() {
		Tablero tableroLocal = new Tablero();
		// coordenadas incorrectas
		int[][] coordenadasIncorrectas = { { -1, -1 }, { 7, -1 }, { -1, 7 }, { 8, 8 } };
		for (int i = 0; i < coordenadasIncorrectas.length; i++) {
			assertNull(tableroLocal.obtenerCelda(coordenadasIncorrectas[i][0], coordenadasIncorrectas[i][1]),
					"La celda no debería estar contenida en el tablero devolviendo un nulo");
		}
	}

	/**
	 * Revisa la consulta de celdas en distintas posiciones correctas del tablero.
	 */
	@DisplayName("Comprueba la consulta de celdas en posiciones correctas del tablero")
	@Test
	void comprobarAccesoACeldasCorrectas() {
		Tablero tableroLocal = new Tablero();
		// coordenadas correctas
		int[][] coordenadasCorrectas = { { 4, 3 }, { 0, 0 }, { 7, 7 }, { 0, 7 }, { 7, 0 } };
		for (int i = 0; i < coordenadasCorrectas.length; i++) {
			assertNotNull("La celda sí debería estar contenida en el tablero, no debe devolver un nulo",
					tableroLocal.obtenerCelda(coordenadasCorrectas[i][0], coordenadasCorrectas[i][1]));
			Celda celda = tableroLocal.obtenerCelda(coordenadasCorrectas[i][0], coordenadasCorrectas[i][1]);
			assertThat("Fila incorrecta", celda.obtenerFila(), is(coordenadasCorrectas[i][0]));
			assertThat("Columna incorrecta", celda.obtenerColumna(), is(coordenadasCorrectas[i][1]));
		}
	}

	/** Genera la cadena de texto solución para un tablero vacío. */
	@DisplayName("Comprueba la generación de la cadena de texto solución con tablero vacío")
	@Test
	void comprobarCadenaTextoSolucionConTableroVacio() {
		// @formatter:off
		String cadenaEsperada = "0		-  -  -  -  -  -  -  -" + "1		-  -  -  -  -  -  -  -"
				+ "2		-  -  -  -  -  -  -  -" + "3		-  -  -  -  -  -  -  -"
				+ "4		-  -  -  -  -  -  -  -" + "5		-  -  -  -  -  -  -  -"
				+ "6		-  -  -  -  -  -  -  -" + "7		-  -  -  -  -  -  -  -" + "		0  1  2  3  4  5  6  7";
		// @formatter:on
		cadenaEsperada = cadenaEsperada.replaceAll("\\s", "");
		// eliminamos espacios/tabuladores para comparar
		String salida = tablero.obtenerSolucion().replaceAll("\\s", "");
		assertEquals(cadenaEsperada, salida, "La cadena de texto generada para un tablero vacío es incorecta.");
	}

	/** Genera la cadena de texto con el estado para un tablero vacío. */
	@DisplayName("Comprueba la generación de la cadena de texto con el estado con tablero vacío")
	@Test
	void comprobarCadenaTextoAMostrarConTableroVacio() {
		// @formatter:off
		String cadenaEsperada = "0		-  -  -  -  -  -  -  -" + "1		-  -  -  -  -  -  -  -"
				+ "2		-  -  -  -  -  -  -  -" + "3		-  -  -  -  -  -  -  -"
				+ "4		-  -  -  -  -  -  -  -" + "5		-  -  -  -  -  -  -  -"
				+ "6		-  -  -  -  -  -  -  -" + "7		-  -  -  -  -  -  -  -" + "		0  1  2  3  4  5  6  7";
		// @formatter:on
		cadenaEsperada = cadenaEsperada.replaceAll("\\s", "");
		// eliminamos espacios/tabuladores para comparar
		String salida = tablero.toString().replaceAll("\\s", "");
		assertEquals(cadenaEsperada, salida, "La cadena de texto a mostrar para un tablero vacío es incorecta.");
	}

	/**
	 * Comprueba que devuelve todas las celdas con independencia del orden.
	 */
	@DisplayName("Comprueba que la consulta de todas las celdas devuelve efectivamente todas (con independencia del orden)")
	@Test
	void comprobarObtenerCeldas() {
		Celda[] todas = tablero.clonarCeldas();
		int encontrada = 0;
		for (int i = 0; i < tablero.obtenerNumeroFilas(); i++) {
			for (int j = 0; j < tablero.obtenerNumeroColumnas(); j++) {
				Celda celda = tablero.obtenerCelda(i, j);
				for (Celda celdaAux : todas) {
					if (celdaAux.tieneCoordenadasIguales(celda)) {
						encontrada++;
						break;
					}
				}
			}
		}
		final int TOTAL_CELDAS = tablero.obtenerNumeroFilas() * tablero.obtenerNumeroColumnas();
		assertThat("No devuelve todas las celdas", encontrada, is(TOTAL_CELDAS));
	}

	/**
	 * Comprueba estado inicial del tablero.
	 */
	@DisplayName("Comprueba estado inicial del tablero")
	@Test
	void comprobarEstadoInicialTablero() {
		assertAll("estadoInicial", () -> assertThat("Alguna bandera ya colocada", tablero.contarBanderas(), is(0)),
				() -> assertThat("Alguna bandera ya colocada", tablero.contarMinas(), is(0)),
				() -> assertThat("Alguna mina ya está explotada", tablero.contarMinasExplotadas(), is(0)),
				() -> assertThat("Alguna mina ya está descubierta", tablero.contarCeldasDescubiertas(), is(0)));
	}

	/**
	 * Comprobar consulta de celda.
	 */
	@DisplayName("Comprobar consulta de celda")
	@Test
	void comprobarConsultaDeCelda() {
		for (int i = 0; i < tablero.obtenerNumeroFilas(); i++) {
			for (int j = 0; j < tablero.obtenerNumeroColumnas(); j++) {
				final int i_copia = i; // necesario por el uso de expresiones lambda...
				final int j_copia = j;
				Celda celda = tablero.clonarCelda(i, j);
				assertAll("consultarFilaColumnaEnCeldaConsultada",
						() -> assertThat("La fila no es correcta para la celda consultada " + celda.toString(), i_copia,
								is(celda.obtenerFila())),
						() -> assertThat("La columna no es correcta para la celda consultada " + celda.toString(), j_copia,
								is(celda.obtenerColumna())));
			}

		}
	}
	
	/**
	 * Revisa la consulta de celdas en distintas posiciones incorrectas del tablero.
	 */
	@DisplayName("Comprueba la consulta de celdas en posiciones incorrectas del tablero")
	@Test
	void comprobarAccesoACeldasIncorrectas() {
		Tablero tableroLocal = new Tablero();
		// coordenadas incorrectas
		int[][] coordenadasIncorrectas = { { -1, -1 }, { 7, -1 }, { -1, 7 }, { 8, 8 } };
		for (int i = 0; i < coordenadasIncorrectas.length; i++) {
			assertNull(tableroLocal.clonarCelda(coordenadasIncorrectas[i][0], coordenadasIncorrectas[i][1]),
					"La celda no debería estar contenida en el tablero devolviendo un nulo");
		}
	}
	
	/**
	 * Comprueba la clonación de un tablero vacío.
	 */
	@DisplayName("Comprueba la clonación de un tablero vacío")
	@Test
	void comprobarClonacionTableroVacío() {
		Tablero tablero = new Tablero();
		Tablero clon = tablero.clonar();
		assertAll("clonaciónCorrectaTableroEnBlanco", 
				() -> assertNotSame(clon, tablero, "Las referencias del tablero original y del clon deben apuntar a objetos diferentes"),
				() -> assertThat("Debería tener el mismo número de filas", tablero.obtenerNumeroFilas(), is(clon.obtenerNumeroFilas())),
				() -> assertThat("Debería tener el mismo número de columnas", tablero.obtenerNumeroColumnas(), is(clon.obtenerNumeroColumnas())),
				() -> assertThat("Estado debería ser equivalente en formato texto", tablero.toString(), is(clon.toString())),
				() -> assertThat("Solución debería ser equivalente en formato texto", tablero.obtenerSolucion(), is(clon.obtenerSolucion())));

	}
	
	/**
	 * Comprueba la clonación de las celdas un tablero vacío.
	 */
	@DisplayName("Comprueba la clonación de las celdas un tablero vacío")
	@Test
	void comprobarClonacionDeCeldasDeUnTableroVacío() {
		Tablero tablero = new Tablero();
		Tablero clon = tablero.clonar();
		comprobarIgualdadCeldasClonadas(tablero, clon);

	}
	
	/**
	 * Comprueba la clonación de las celdas de un tablero iniciado.
	 */
	@DisplayName("Comprueba la clonación de las celdas de un tablero iniciado")
	@Test
	void comprobarClonacionDeCeldasDeUnTableroIniciado() {
		Tablero tablero = new Tablero();
		tablero.descubrir(0, 0);
		Tablero clon = tablero.clonar();
		comprobarIgualdadCeldasClonadas(tablero, clon);

	}

	/**
	 * Comprueba la igualdad de celdas clonadas.
	 * 
	 * @param tablero tablero
	 * @param clon clon del tablero
	 * @throws MultipleFailuresError si hay algún error grave
	 */
	private void comprobarIgualdadCeldasClonadas(Tablero tablero, Tablero clon) throws MultipleFailuresError {
		for (int fila = 0; fila < tablero.obtenerNumeroFilas(); fila++) {
			for (int columna = 0; columna < tablero.obtenerNumeroColumnas(); columna++) {
				Celda original = tablero.obtenerCelda(fila, columna);
				Celda clonada = clon.obtenerCelda(fila, columna);
				assertNotSame(clonada, original, "Los tableros comparten las celdas, no es un clon correcto.");
				assertAll("estadoCoincidente",
					() -> assertThat("No tienen igual fila", original.obtenerFila(), is(clonada.obtenerFila())),
					() -> assertThat("No tienen igual columna", original.obtenerColumna(), is(clonada.obtenerColumna())),
					() -> assertThat("No tienen mismo número de minas adyacentes", original.obtenerNumeroMinasAdyacentes(), is(clonada.obtenerNumeroMinasAdyacentes())),
					() -> assertThat("No tienen coincidencia en tener o no mina", original.tieneMina(), is(clonada.tieneMina())),
					() -> assertThat("El estado oculta no coincide", original.estaOculta(), is(clonada.estaOculta())),
					() -> assertThat("El estado marcada no coincide", original.estaMarcada(), is(clonada.estaMarcada())),
					() -> assertThat("El estado descubierta no coincide", original.estaDescubierta(), is(clonada.estaDescubierta()))
					
					);				
			}
		}
	}
	
	/**
	 * Comprueba la clonación tras primer movimiento.
	 */
	@DisplayName("Comprueba la clonación de un tablero tras primer movimiento")
	@Test
	void comprobarClonacionTableroTrasPrimerMovimiento() {
		Tablero tablero = new Tablero();
		tablero.descubrir(0, 0);
		Tablero clon = tablero.clonar();
		assertAll("clonaciónCorrectaTableroTrasPrimerMovimiento", 
				() -> assertNotSame(clon, tablero, "Las referencias del tablero original y del clon deben apuntar a objetos diferentes"),
				() -> assertThat("Debería tener el mismo número de filas", tablero.obtenerNumeroFilas(), is(clon.obtenerNumeroFilas())),
				() -> assertThat("Debería tener el mismo número de columnas", tablero.obtenerNumeroColumnas(), is(clon.obtenerNumeroColumnas())),
				() -> assertThat("Estado debería ser equivalente en formato texto", tablero.toString(), is(clon.toString())),
				() -> assertThat("Solución debería ser equivalente en formato texto", tablero.obtenerSolucion(), is(clon.obtenerSolucion())));
	}

}
