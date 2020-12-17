package juego.control;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import juego.modelo.Celda;
import juego.modelo.Tablero;
import juego.util.CoordenadasIncorrectasException;

/**
 * Pruebas unitarias sobre el estado inicial del árbitro. Este conjunto de
 * pruebas se centra en verificar el estado del árbitro justo después de
 * instanciar.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena Sánchez</a>
 * @version 1.0 20190813
 */
@DisplayName("Tests sobre el estado inicial del árbitro")
public class ArbitroEstadoInicialTest {

	/** Árbitro de testing. */
	private Arbitro arbitro;

	/** Generación del árbitro para testing. */
	@BeforeEach
	void inicializar() {
		arbitro = new ArbitroSeguro(new Tablero());
	}

	/**
	 * Comprobar estado inicial de la partida tras instanciar un arbitr.
	 */
	@DisplayName("Comprobar estado inicial de la partida tras instanciar un arbitro")
	@Test
	void comprobarEstadoInicial() {
		// no se han colocado minas todavía...
		assertAll("estadoInicial",
				() -> assertFalse(arbitro.haExplotadoAlgunaMina(), "No puede tener minas explotadas al iniciar"),
				() -> assertFalse(arbitro.haFinalizadoConExito(),
						"NO puede haber finalizado al comenzar sin hacer ningún movimiento"));
	}

	/**
	 * Descubrir una primera celda en todas las posibles posiciones de inicio.
	 * 
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero
	 */
	@DisplayName("Descubrir una primera celda en todas las posibles posiciones de inicio")
	@Test
	void descubrirPrimeraCelda() throws CoordenadasIncorrectasException{
		// descubrimos una primera celda para colocar las minas en todos los posibles
		// primeros movimientos
		for (int i = 0; i < arbitro.consultarTablero().obtenerNumeroFilas(); i++) {
			for (int j = 0; j < arbitro.consultarTablero().obtenerNumeroColumnas(); j++) {
				Arbitro arbitro = new ArbitroSeguro(new Tablero()); // arbitro local
				arbitro.descubrir(i, j);
				
				Celda celda = arbitro.consultarTablero().clonarCelda(i, j); // pera mensaje informativo
				assertAll("nuncaDeberíaExplotarUnaMinaEnPrimerMovimiento",
						() -> assertFalse(arbitro.haFinalizadoConExito(),
								"No puede ganar si desmarcamos una primera celda " + celda.toString()),
						() -> assertFalse(arbitro.haExplotadoAlgunaMina(),
								"No debería haber explotado una mina al descubrir la primera celda " + celda.toString()));
			}
		}
	}

	/**
	 * Descubrir una primera celda y luego todas las ocultas, iterando sobre todas las posibles posiciones de inicio.
	 * 
	 * @throws CoordenadasIncorrectasException si las coordenadas están fuera del tablero 
	 */
	@DisplayName("Descubrir una primera celda y luego todas las ocultas, iterando sobre todas las posibles posiciones de inicio")
	@Test
	void descubrirOcultas() throws CoordenadasIncorrectasException {
		for (int i = 0; i < arbitro.consultarTablero().obtenerNumeroFilas(); i++) {
			for (int j = 0; j < arbitro.consultarTablero().obtenerNumeroColumnas(); j++) {
				Arbitro arbitro = new ArbitroSeguro(new Tablero()); // arbitro local
				// descubrimos una primera celda para colocar las minas
				arbitro.descubrir(i, j);
				// descubrimos todo el resto de celdas ocultas, deberíamos perder siempre
				// porque alguna mina debería explotar
				arbitro.descubrirOcultas();
				
				Celda celda = arbitro.consultarTablero().clonarCelda(i, j); // pera mensaje informativo
				assertAll("perderDesmarcandoTodasLasOcultasTrasDescubrirPrimeraCelda",
						() -> assertFalse(arbitro.haFinalizadoConExito(),
								"No puede ganar si desmarcamos todas las celdas al iniciar con " + celda.toString()),
						() -> assertTrue(arbitro.haExplotadoAlgunaMina(),
								"Debería haber explotado alguna mina al descubrir todas, despues de iniciar en "
										+ celda.toString()));
			}
		}
	}
}
