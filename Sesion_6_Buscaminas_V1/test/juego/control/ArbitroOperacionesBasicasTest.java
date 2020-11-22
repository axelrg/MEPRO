package juego.control;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias sobre el estado inicial del árbitro. Este conjunto de
 * pruebas se centra en verificar el estado del árbitro justo después de
 * instanciar.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena Sánchez</a>
 * @version 1.0 20190813
 */
@DisplayName("Tests sobre las operaciones básicas del árbitro empezando en celda (0,0)")
public class ArbitroOperacionesBasicasTest {
	
	// Se deja como "chuleta" del tablero solución con el que se ejecutan los tests.
	// @formatter:off
	/** Solución. */
   	private static final String SOLUCION = 
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
	
	/** Posiciones de las minas. */ 
	private static final int[][] POSICIONES_MINAS = { { 1, 4 }, { 2, 2 }, { 3, 4 }, { 4, 0 }, { 4, 6 }, { 5, 2 },
			{ 5, 6 }, { 7, 0 }, { 7, 1 }, { 7, 7 } };

	/** Árbitro de testing. */
	private Arbitro arbitro;

	/** Generación del árbitro para testing. */
	@BeforeEach
	void inicializar() {
		arbitro = new Arbitro();
	}

	/**
	 * Comprueba la derrota tras explotar mina después del primer movimiento.
	 */
	@DisplayName("Comprobar derrota tras explotar mina después del primer movimiento")
	@Test
	void comprobarDerrotaAlDescubrirAlSegundoMovimiento() {
		arbitro.descubrir(0, 0);
		arbitro.descubrir(1, 4); // descubrimos una mina a la primera...

		assertAll("estadoFinalTrasExplotarMina",
				() -> assertTrue(arbitro.haExplotadoAlgunaMina(), "Debería haber explotado una mina en (1,4)"),
				() -> assertFalse(arbitro.haFinalizadoConExito(),
						"NO puede haber finalizado con éxito al descubrir una mina"));
	}

	/**
	 * Comprueba la derrota tras descubrir todas las ocultas tras primer movimiento.
	 */
	@DisplayName("Comprobar derrota tras descubrir todas las ocultas tras primer movimiento")
	@Test
	void comprobarDerrotaAlDescubrirTodasLasOcultasTrasPrimerDescubrir() {
		arbitro.descubrir(0, 0);
		arbitro.descubrirOcultas(); // descubrimos todas las ocultas... (alguna mina explota)

		assertAll("estadoFinalTrasDescubrirTodasLasOcultas",
				() -> assertTrue(arbitro.haExplotadoAlgunaMina(), "Deberían haber explotado todas las minas"),
				() -> assertFalse(arbitro.haFinalizadoConExito(),
						"NO puede haber finalizado con éxito al descubrir todas las minas"));
	}

	

	/**
	 * Comprueba victoria tras marcar todas las minas y descubrir ocultas.
	 */
	@DisplayName("Comprobar victoria tras marcar todas las minas y descubrir ocultas")
	@Test
	void comprobarVictoria() {
		arbitro.descubrir(0, 0);
		marcarTodasLasMinas();
		arbitro.descubrirOcultas();

		assertAll("estadoFinalTrasMarcarTodasYDescubrirOcultas",
				() -> assertFalse(arbitro.haExplotadoAlgunaMina(), "No puede tener minas explotadas"),
				() -> assertTrue(arbitro.haFinalizadoConExito(),
						"Debería finalizar en victoria si marcamos todas las minas y descubrimos las ocultas"));
	}

	/**
	 * Comprueba movimiento ilegal sobre celda ya previamente descubierta.
	 */
	@DisplayName("Comprobar movimiento ilegal sobre celda ya previamente descubierta")
	@Test
	void comprobarMovimientoLegalSobreCeldaYaDescubierta() {
		arbitro.descubrir(0, 0);
		assertAll("legalidadAlIntentarDescubrirOMarcarCeldaDescubierta",
				() -> assertFalse(arbitro.esLegalDescubrir(0, 0),
						"No se puede descubrir una celda ya descubierta"),
				() -> assertFalse(arbitro.esLegalMarcar(0, 0),
						"No se puede marcar una celda ya descubierta")				
				);
	}
	
	/**
	 * Comprueba la ilegalidad de marcar celda tras marcar todas las minas.
	 */
	@DisplayName("Comprobar ilegalidad de marcar celda tras marcar todas las minas")
	@Test
	void comprobarLegalidadDeMarcarMásDelMáximoNumeroDeMinas() {
		arbitro.descubrir(0, 0);
		System.out.println(arbitro.consultarTablero().toString());
		marcarTodasLasMinas();
		
		System.out.println(arbitro.consultarTablero().toString());

		assertAll("legalidadAlIntentarMarcarMásDelMáximoNumeroDeMinas",
				() -> assertFalse(arbitro.esLegalMarcar(0, 6),
						"No se puede marcar más del número máximo de minas")
				);
	}
	
	/**
	 * Comprueba la ilegalidad de marcar una celda ya marcada con bandera.
	 */
	@DisplayName("Comprobar ilegalidad de marcar celda ya marcada con bandera")
	@Test
	void comprobarLegalidadDeMarcarUnaCeldaYaMarcada() {
		arbitro.descubrir(0, 0);
		arbitro.marcarODesmarcar(0, 6);

		assertAll("legalidadAlIntentarMarcarCeldaYaMarcada",
				() -> assertFalse(arbitro.esLegalMarcar(0, 6),
						"No se puede marcar una celda ya marcada previamente")
				);
	}
	
	/**
	 * Comprueba la ilegalidad de descubrir celda ya marcada con bandera.
	 */
	@DisplayName("Comprobar ilegalidad de descubrir celda ya marcada con bandera")
	@Test
	void comprobarLegalidadDeDescubrirUnaCeldaYaMarcada() {
		arbitro.descubrir(0, 0);
		arbitro.marcarODesmarcar(0, 6);

		assertAll("legalidadAlIntentarMarcarCeldaYaMarcada",
				() -> assertFalse(arbitro.esLegalDescubrir(0, 6),
						"No se puede descubrir una celda ya marcada")
				);
	}
	
	/**
	 * Comprueba la legalidad de marcar todas la minas, desmarcarcar y volver a marcar alguna mina.
	 */
	@DisplayName("Comprueba la legalidad de marcar todas la minas, desmarcarcar y volver a marcar alguna mina")
	@Test
	void comprobarLegalidadDeMarcarTodasLasMinasDesmarcarUnaYVolverAMarcaDeNuevo() {
		arbitro.descubrir(0, 0);
		marcarTodasLasMinas();
		assertTrue(arbitro.esLegalDesmarcar(1, 4),
				"Debería poder desmarcar una bandera ya colocada en (1,4)");
		arbitro.marcarODesmarcar(1, 4); // desmarcamos, quitando la bandera (tendremos 9 banderas en el tablero)
		assertTrue(arbitro.esLegalMarcar(1, 4),
				"Debería poder marcar con la última bandera disponible");
	}
	
	/**
	 * Comprueba la ilegalidad de desmarcar banderas no colocadas.
	 */
	@DisplayName("Comprobar la ilegalidad de desmarcar banderas no colocadas.")
	@Test
	void comprobarIlegalidadDeDesmarcarBanderasNoColocadas() {
		arbitro.descubrir(0, 0);
		assertFalse(arbitro.esLegalDesmarcar(1, 4),
				"No debería poder desmarcar una bandera no colocada en (1,4)");
	}
	
	/**
	 * Comprueba la ilegalidad de descubrir celda ya descubierta.
	 */
	@DisplayName("Comprobar la ilegalidad de descubrir celda ya descubierta.")
	@Test
	void comprobarIlegalidadDeDescubrirCeldaYaDescubierta() {
		arbitro.descubrir(0, 0);
		assertFalse(arbitro.esLegalDescubrir(0, 0),
				"No debería poder descubrir una celda ya descubierta en (0,0)");
	}	
	
	/**
	 * Comprueba la legalidad de descubrir celda no descubierta.
	 */
	@DisplayName("Comprobar la legalidad de descubrir celda no descubierta.")
	@Test
	void comprobarLegalidadDeDescubrirCeldaYaDescubierta() {
		arbitro.descubrir(0, 0);
		assertTrue(arbitro.esLegalDescubrir(7, 7),
				"Debería poder descubrir una celda no descubierta en (7,7)");
	}	
	
	
	// Métodos de utilidad privados

	/** Marca todas las minas. */
	private void marcarTodasLasMinas() {
		// marcamos todas
		for (int i = 0; i < POSICIONES_MINAS.length; i++) {
			arbitro.marcarODesmarcar(POSICIONES_MINAS[i][0], POSICIONES_MINAS[i][1]);
		}
	}

}
