package juego.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import juego.control.DistanciaChebyshev;

/**
 * Tests sobre el estado del tablero inicial.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0 20181008
 */
public abstract class TableroEmpezandoAncestroTest {
	
	/** Tablero de testing. */
	Tablero tablero;

	/** Inicializa valores para cada test. */
	@BeforeEach
	void inicializar() {
		tablero = new Tablero();
	}
	
	/**
	 * Obtiene la fila de inicio.
	 * 
	 * @return fila inicio
	 */
	abstract int obtenerFilaInicio();
	
	/**
	 * Obtiene la columna de inicio.
	 * 
	 * @return columna inicio
	 */
	abstract int obtenerColumnaInicio();
	
	/**
	 * Posiciones de minas.
	 * 
	 * @return posiciones de minas
	 */
	abstract int[][] obtenerPosicionMinas();
	
	/**
	 * Comprueba estado inicial.
	 */
	@DisplayName("Comprueba estado inicial de las 10 minas empezando a descubrir la celda inicial (0,0)")
	@Test
	void comprobarEstadoInicialTrasColocacionMinasEmpezandoEnCeldaCeroCero() {
		final int fila = obtenerFilaInicio();
		final int columna = obtenerColumnaInicio();
		tablero.colocarMinas(new Celda(fila, columna), new DistanciaChebyshev(), new Random(fila * 10L + columna));
		assertAll("estadoInicialTrasColocarMinasEnCeroCero",
				() -> assertThat("No debería haber celdas descubiertas", tablero.contarCeldasDescubiertas(),
						is(0)),
				() -> assertThat("No debería haber minas explotadas", tablero.contarMinasExplotadas(), is(0)),
				() -> assertThat("No debería haber banderas colocadas", tablero.contarBanderas(), is(0)),
				() -> assertThat("El número de minas es incorrecto", tablero.contarMinas(), is(10)));
	}
	
	/**
	 * Comprueba la colocación.
	 */
	@DisplayName("Comprueba colocación de las 10 minas empezando a descubrir la celda inicial (0,0)")
	@Test
	void comprobarColocacionMinasEmpezandoEnCeldaCeroCero() {
		final int fila = obtenerFilaInicio();
		final int columna = obtenerColumnaInicio();
		tablero.colocarMinas(new Celda(fila, columna), new DistanciaChebyshev(), new Random(fila * 10L + columna));
		assertThat("El número de minas colocadas es incorrecto", tablero.contarMinas(), is(10));
	
		for (int i = 0; i < obtenerPosicionMinas().length; i++) { // al primer fallo el test para...
			Celda celda = tablero.obtenerCelda(obtenerPosicionMinas()[i][0], obtenerPosicionMinas()[i][1]);
			assertThat("La celda " + celda.toString() + " debería tener una mina", celda.tieneMina(), is(true));
			assertThat("El número de celdas con valor cero minas adyacentes es incorrecto",
					celda.obtenerNumeroMinasAdyacentes(), is(-1));
		}
	}
	
	/**
	 * Comprueba el número de minas adyacentes.
	 * 
	 * @param posiciones posiciones
	 * @param numeroMinasAdyacentes número de minas adyacentes
	 */
	void comprobarNumero(int[][] posiciones, int numeroMinasAdyacentes) {
		final int fila = obtenerFilaInicio();
		final int columna = obtenerColumnaInicio();
		tablero.colocarMinas(new Celda(fila, columna), new DistanciaChebyshev(), new Random(fila * 10L + columna));
		int contador = 0;
		for (int i = 0; i < posiciones.length; i++) { // al primer fallo el test para...
			Celda celda = tablero.obtenerCelda(posiciones[i][0], posiciones[i][1]);
			assertThat("El número de minas adyacentes para la celda " + celda.toString() + " es incorrecto",
					celda.obtenerNumeroMinasAdyacentes(), is(numeroMinasAdyacentes));
			contador++;
		}
		assertThat("El número de celdas con valor cero es incorrecto", contador, is(posiciones.length));
	}
	
	/**
	 * Descubre la celda de inicio.
	 */
	void descubrirCeldaInicial() {
		final int fila = obtenerFilaInicio();
		final int columna = obtenerColumnaInicio();
		tablero.colocarMinas(new Celda(fila, columna), new DistanciaChebyshev(), new Random(fila * 10L + columna));
		tablero.descubrir(fila, columna);
	}
	
	/**
	 * Marca todas las minas con bandera.
	 */
	void marcarTodasLasMinas() {
		for (int i = 0; i < obtenerPosicionMinas().length; i++) {
			tablero.marcarDesmarcar(obtenerPosicionMinas()[i][0], obtenerPosicionMinas()[i][1]);
		}
	}
	
	/**
	 * Descubre todas las celdas con minas.
	 */
	void descubrirTodasLasMinas() {
		for (int i = 0; i < obtenerPosicionMinas().length; i++) {
			tablero.descubrir(obtenerPosicionMinas()[i][0], obtenerPosicionMinas()[i][1]);
		}
	}
	
	/**
	 * Elimina caracteres blancos, tabuladores o saltos en cadena.
	 * 
	 * @param cadena cadena
	 * @return cadena limpia
	 */
	String limpiarCadena(String cadena) {
		return cadena.replaceAll("\\s", "");
	}
	
	/**
	 * Comprueba celdas descubiertas inicialmente.
	 * 
	 * @param posiciones posiciones de celdas a comprobar
	 */
	void comprobarCeldaDescubiertasInicialmente(int[][] posiciones) {
		for (int i = 0; i < posiciones.length; i++) { // al primer fallo el test para...
			Celda celda = tablero.obtenerCelda(posiciones[i][0], posiciones[i][1]);
			assertThat("La celda " + celda.toString() + " debería estar descubierta.", celda.estaDescubierta(),
					is(true));
		}
		int contador = 0;
		for (Celda celda : tablero.clonarCeldas()) {
			if (celda.estaDescubierta()) {
				contador++;
			}
		}
		assertThat("El número de celdas descubiertas no es correcto.", contador, is(posiciones.length));
		assertThat("El número de celdas descubiertas calculado por el tablero no es correcto",
				tablero.contarCeldasDescubiertas(), is(posiciones.length));
	}
	
	/**
	 * Obtiene el texto con el estado del tablero tras descubrir
	 * la primera celda.
	 * 
	 * @return texto con el estado del tablero
	 */
	abstract String obtenerEstadoTrasDescubrirPrimeraCelda();


	/**
	 * Comprueba el estado al descubrir la primera celda.
	 */
	@DisplayName("Comprueba el estado del tablero al descubrir la celda inicial")
	@Test
	void comprobarEstadoAlDescubrirCeroCero() {
		descubrirCeldaInicial();
		String solucion = obtenerEstadoTrasDescubrirPrimeraCelda();
		String cadenaEsperada = limpiarCadena(solucion);
		String cadenaObtenida = limpiarCadena(tablero.toString());
		assertEquals(cadenaEsperada, cadenaObtenida,
				"El texto a mostrar tras descubrir la primera celda es incorrecto");
	}
	
	/**
	 * Obtiene el texto solución del tablero tras descubrir
	 * la primera celda.
	 * 
	 * @return texto solución con el estado del tablero
	 */
	abstract String obtenerSolucionTrasDescubrirPrimeraCelda();
	
	/**
	 * Comprueba el estado solución al descubrir la celda inicial.
	 */
	@DisplayName("Comprueba el estado de la solución del tablero al descubrir la celda inicial")
	@Test
	void comprobarSolucionAlDescubrirCeroCero() {
		descubrirCeldaInicial();
		String solución = this.obtenerSolucionTrasDescubrirPrimeraCelda();
		String cadenaEsperada = limpiarCadena(solución);
		String cadenaObtenida = limpiarCadena(tablero.obtenerSolucion());
		assertEquals(cadenaEsperada, cadenaObtenida,
				"El texto solución a mostrar tras descubrir la primera celda es incorrecto");
	}
	
	/**
	 * Obtiene el texto con el estado del tablero tras marcar 
	 * todas las minas.
	 * 
	 * @return texto con el estado del tablero
	 */
	abstract String obtenerEstadoTrasMarcarTodasLasMinas();
	
	
	
	/**
	 * Marcar todas las minas con bandera después de descubrir la celda inicial.
	 */
	@DisplayName("Marcar todas las minas con bandera después de descubrir la celda inicial")
	@Test
	void comprobarMarcarTodasLasMinas() {
		descubrirCeldaInicial();
		marcarTodasLasMinas();
		assertThat("El número de banderas no es correcto.", tablero.contarBanderas(), is(10));
		String solución = this.obtenerEstadoTrasMarcarTodasLasMinas();
		String cadenaEsperada = limpiarCadena(solución);
		String cadenaObtenida = limpiarCadena(tablero.toString());
		assertEquals(cadenaEsperada, cadenaObtenida, "El texto a mostrar tras marcar todas las minas es incorrecto");
	}
	
	/**
	 * Obtiene estado tras descubrir todas las minas.
	 * 
	 * @return texto con el estado
	 */
	abstract String obtenerEstadoTrasDescubirTodasLasMinas();

	/**
	 * Descubrir todas las minas justo después de descubrir la celda inicial.
	 */
	@DisplayName("Descubrir todas las minas justo después de descubrir la celda inicial")
	@Test
	void comprobarDescubrirTodasLasMinas() {
		descubrirCeldaInicial();
		descubrirTodasLasMinas();
		String solución = this.obtenerEstadoTrasDescubirTodasLasMinas();
		String cadenaEsperada = limpiarCadena(solución);
		String cadenaObtenida = limpiarCadena(tablero.toString());
		assertEquals(cadenaEsperada, cadenaObtenida, "El texto a mostrar tras descubrir todas las minas es incorrecto");
	}
}
