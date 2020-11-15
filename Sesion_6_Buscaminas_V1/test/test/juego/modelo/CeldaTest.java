package test.juego.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;
import java.util.Random;

import juego.modelo.Celda;
import juego.modelo.Estado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


/**
 * Pruebas unitarias sobre la celda.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena Sánchez</a>
 * @version 1.0 20181008
 * 
 */
@DisplayName("Tests sobre Celda")
public class CeldaTest {
	
	/** Generador de aleatorios. */
	private static Random aleatorios = new SecureRandom();
	
	/** Celda. */
	private Celda celda;
	
	/**
	 * Inicializa celda.
	 */
	@BeforeEach
	void inicializar() {
		celda = new Celda(1,2);
	}

	/**
	 * Test del constructor.
	 */
	@DisplayName("Constructor con estado inicial correcto")
	@Test
	void constructor() {
		final int TAMAÑO = 8;
		for (int fila = 0; fila < TAMAÑO; fila++) {
			for (int columna = 0; columna < TAMAÑO; columna++) {
				Celda celda = new Celda(fila, columna);
				assertThat("Fila mal inicializada", celda.obtenerFila(), is(fila));
				assertThat("Columnna mal inicializada", celda.obtenerColumna(), is(columna));
				assertTrue(!celda.tieneMina(), "Inicialmente no está vacía.");
			}
		}
	}

	/**
	 * Test del método toString.
	 */
	@DisplayName("Formato de texto")
	@Test
	void probarToString() {
		for (int fila = 0; fila < 10; fila++) {
			for (int columna = 0; columna < 10; columna++) {
				Celda celda = new Celda(fila, columna);
				String actual = celda.toString().replaceAll("\\s", "");
				assertThat("Texto incorrecto." + celda.toString(), actual, is("[(" + fila + "," + columna + ")-0-OCULTA]"));
			}
		}
	}

	/** Comprueba la igualdad de coordenadas entre celdas. */
	@DisplayName("Tienen las mismas coordenadas celdas coincidentes en posición")
	@Test
	void comprobarIgualdadDeCoordenadas() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				Celda celda1 = new Celda(i, j);
				Celda celda2 = new Celda(i, j);
				assertTrue(celda1.tieneCoordenadasIguales(celda2));
				assertTrue(celda2.tieneCoordenadasIguales(celda1));
			}
		}
	}

	/** Comprueba la desigualdad de coordenadas entre celdas. */
	@DisplayName("Tienen diferentes coordenadas celdas NO coincidentes en posición")
	@Test
	void comprobarDesigualdadDeCoordenadas() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				Celda celda1 = new Celda(i, j);
				int desplazamiento1 = aleatorios.nextInt(10) + 1;
				int desplazamiento2 = aleatorios.nextInt(10) + 1;
				Celda celda2 = new Celda(i + desplazamiento1, j + desplazamiento2);
				assertFalse(celda1.tieneCoordenadasIguales(celda2));
				assertFalse(celda2.tieneCoordenadasIguales(celda1));
				Celda celda3 = new Celda(i, j + desplazamiento2);
				assertFalse(celda1.tieneCoordenadasIguales(celda3));
				assertFalse(celda3.tieneCoordenadasIguales(celda1));
				Celda celda4 = new Celda(i + desplazamiento1, j);
				assertFalse(celda1.tieneCoordenadasIguales(celda4));
				assertFalse(celda4.tieneCoordenadasIguales(celda1));
			}
		}
	}

	/** Test de comprobación de igualdad de coordenadas. */
	@DisplayName("Comprobación de igualdad de coordenadas")
	@Test
	void probarIgualdadDeCoordenadas() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Celda celda = new Celda(i, j);
				Celda celda2 = new Celda(i, j);
				assertTrue(celda.tieneCoordenadasIguales(celda2), "Comparación de igualdad incorrecta para " + celda.toString());
				assertFalse(celda == null, "Es igual a nulo");

			}
		}
	}

	
	/**
	 * Test de colocación de mina.
	 */
	@DisplayName("Colocación de mina")
	@Test
	void probarColocarMina() {
		Celda celda = new Celda(0,0);
		celda.colocarMina();
		assertThat("La celda debería contener mina una vez colocada.",  celda.tieneMina(), is(true));
	}
	
	/**
	 * Test de estado inicial.
	 */
	@DisplayName("Estado inicial de una celda recién instanciada")
	@Test
	void probarEstadoInicial() {
		assertAll("estadoInicial",
				() -> assertThat("La celda no debería contener mina inicialmente.",  celda.tieneMina(), is(false)),
				() -> assertThat("La fila no se ha inicializado correctamente.",  celda.obtenerFila(), is(1)),
				() -> assertThat("La columna no se ha inicializado correctamente.",  celda.obtenerColumna(), is(2)),
				() -> assertThat("El número de minas no se ha inicializado correctamente.",  celda.obtenerNumeroMinasAdyacentes(), is(0)),
				() -> assertThat("El estado no se ha inicializado correctamente.",  celda.estaOculta(), is(true)));
	}
	
	
	/**
	 * Test de textos iniciales.
	 */
	@DisplayName("Textos iniciales para una celda recién instanciada")
	@Test
	void probarTextosIniciales() {
		assertAll("textosIniciales",
				() -> assertThat("El texto para una celda inicialmente oculta no es correcto.",  celda.obtenerTextoEstado(), is(" - ")),
				() -> assertThat("El texto solución para una celda oculta con 0 celdas adyacentes no es correcto.",  celda.obtenerTextoSolucion(), is(" - ")),
				() -> assertThat("El texto para mostrar en depuración no es correcto.",  celda.toString(), is("[(1,2)-0-OCULTA]")));
	}
	
	
	/**
	 * Test de descubrir.
	 */
	@DisplayName("Descubrir celda")
	@Test
	void probarDescubrir() {
		assertThat(celda.estaOculta(), is(true));
		celda.establecerSiguienteEstadoDescubrir();
		assertThat(celda.estaDescubierta(), is(true));
	}
	
	/**
	 * Test de marcar.
	 */
	@DisplayName("Marcar celda")
	@Test
	void probarMarcar() {
		assertThat(celda.estaOculta(), is(true));
		celda.establecerSiguienteEstadoMarcar();
		assertThat(celda.estaMarcada(), is(true));
	}
	
	/**
	 * Test de marcar y desmarcar.
	 */
	@DisplayName("Marcar y desmarcar celda")
	@Test
	void probarMarcarYDesmarcar() {
		assertThat(celda.estaOculta(), is(true));
		celda.establecerSiguienteEstadoMarcar();
		assertThat(celda.estaMarcada(), is(true));
		celda.establecerSiguienteEstadoMarcar();
		assertThat(celda.estaOculta(), is(true));
	}
	
	
	/**
	 * Test de descubrir celda descubierta sin efecto.
	 */
	@DisplayName("Descubrir celda oculta sin efecto")
	@Test
	void probarDescubrirCeldaDescubierta() {
		assertThat(celda.estaOculta(), is(true));
		celda.establecerSiguienteEstadoDescubrir();
		assertThat(celda.estaDescubierta(), is(true));
		celda.establecerSiguienteEstadoDescubrir();
		assertThat(celda.estaDescubierta(), is(true));
	}
	
	/**
	 * Test de marcar celda descubierta sin efecto.
	 */
	@DisplayName("Marcar celda oculta sin efecto")
	@Test
	void probarMarcarCeldaDescubierta() {
		assertThat(celda.estaOculta(), is(true));
		celda.establecerSiguienteEstadoDescubrir();
		assertThat(celda.estaDescubierta(), is(true));
		celda.establecerSiguienteEstadoMarcar();
		assertThat(celda.estaDescubierta(), is(true));
	}
	
	/**
	 * Test de establecer número minas adyacentes.
	 * 
	 * @param numero numero
	 */
	@DisplayName("Establecer el número de minas adyacentes")
	@ParameterizedTest
	@CsvSource({ "1", "2", "3", "4", "5", "6", "7", "8"})
	void probarEstablecerNumeroMinasAdyacentes(int numero) {		
		celda.establecerNumeroMinasAdyacentes(numero);
		assertThat("Número de minas adyacentes mal inicializado", celda.obtenerNumeroMinasAdyacentes(), is(numero));
	}
	
	/**
	 * Test de clonación.
	 */
	@DisplayName("Clonación de celda vacía")
	@Test
	void probarClonacionCeldaVacía() {
		Celda clon = celda.clonar();
		assertAll("estadoInicialTrasClonación",
			() -> assertNotSame(clon, celda, "Las referencias del clon y el original no pueden coincidir."),
			() -> assertThat("El número de fila no coincide", clon.obtenerFila(), is(celda.obtenerFila())),
			() -> assertThat("El número de columna no coincide", clon.obtenerColumna(), is(celda.obtenerColumna())),
			() -> assertThat("El número de columna no coincide", clon.tieneMina(), is(celda.tieneMina())),
			() -> assertThat("El estado oculta no coincide", clon.estaOculta(), is(celda.estaOculta())),
			() -> assertThat("El estado marcada no coincide", clon.estaMarcada(), is(celda.estaMarcada())),
			() -> assertThat("El estado descubierta no coincide", clon.estaDescubierta(), is(celda.estaDescubierta()))
			);
	}
	
	/**
	 * Comprueba el método equals.
	 */
	@DisplayName("Método equals")
	@Test
	void probarEquals() {
		Celda celdaAux = new Celda(2,2);
		Celda celdaAux2 = new Celda(1,0);
		Celda celdaIgual = new Celda(1,2);
		assertAll("testEquals",
			() -> assertTrue(celda.equals(celda), "Una celda debería ser igual en contenido a sí misma"),
			() -> assertTrue(celda.equals(celdaIgual), "Las celdas son iguales por tener el mismo contenido"),
			() -> assertFalse(celdaAux.equals(celda), "Las celdas deberían ser diferentes en contenido"),
			() -> assertFalse(celdaAux2.equals(celda), "Las celdas deberían ser diferentes en contenido"),
			() -> assertFalse(celdaAux.equals(null), "Las celdas deberían ser diferentes en contenido"),
			() -> assertFalse(celdaAux.equals(new Object()), "Las celdas deberían ser diferentes en contenido"));
	}
	
	
	/**
	 * Test de métodos de igualdad sobre clonación.
	 */
	@DisplayName("Igualdad de hashCode sobre clonación")
	@Test
	void probarMetodosDeIgualdadSobreClonación() {
		Celda clon = celda.clonar();
		assertThat("Coincidencia de hashCodes", clon.hashCode(), is(celda.hashCode()));
	}

	/**
	 * Test de texto solución generado para celda descubierta.
	 */
	@DisplayName("Texto solución generado para celda descubierta")
	@Test
	void probarCambioTextoSolucionAlDescubrir() {
		assertThat("El texto solución para una celda oculta con 0 celdas adyacentes no es correcto.",  celda.obtenerTextoSolucion(), is(" " + Estado.OCULTA.obtenerLetra() + " "));
		celda.establecerSiguienteEstadoDescubrir();
		assertThat("El texto solución para una celda descubierta con 0 celdas adyacentes no es correcto.",  celda.obtenerTextoSolucion(), is(" " + Estado.OCULTA.obtenerLetra() + " "));
	}
	
	/**
	 * Test de texto a mostrar generado para celda descubierta.
	 */
	@DisplayName("Texto a mostrar generado para celda descubierta")
	@Test
	void probarCambioTextoAMostrarAlDescubrir() {
		assertThat("El texto a mostar para una celda oculta con 0 celdas adyacentes no es correcto.",  celda.obtenerTextoEstado(), is(" " + Estado.OCULTA.obtenerLetra() + " "));
		celda.establecerSiguienteEstadoDescubrir();
		assertThat("El texto a mostrar para una celda descubierta con 0 celdas adyacentes no es correcto.",  celda.obtenerTextoEstado(), is(" . "));
	}
	
	
	/**
	 * Test de texto solución generado para celda descubierta con mina.
	 */
	@DisplayName("Texto solución generado para celda descubierta con mina")
	@Test
	void probarCambioTextoSolucionAlDescubrirConMina() {
		celda.colocarMina(); // colocamos una mina...
		assertThat("El texto solución para una celda oculta con mina no es correcto.",  celda.obtenerTextoSolucion(), is(Celda.TEXTO_MINA));
		celda.establecerSiguienteEstadoDescubrir();
		assertThat("El texto solución para una celda descubierta con mina no es correcto.",  celda.obtenerTextoSolucion(), is(Celda.TEXTO_MINA));
	}
	
	/**
	 * Test de texto a mostrar generado para celda descubierta con mina.
	 */
	@DisplayName("Texto solución generado para celda descubierta con mina")
	@Test
	void probarCambioTextoAMostrarAlDescubrirConMina() {
		celda.colocarMina(); // colocamos una mina...
		assertThat("El texto a mostrar para una celda oculta con mina no es correcto.",  celda.obtenerTextoEstado(), is(" " + Estado.OCULTA.obtenerLetra() + " "));
		celda.establecerSiguienteEstadoDescubrir();
		assertThat("El texto a mostrar para una celda descubierta con mina no es correcto.",  celda.obtenerTextoEstado(), is(Celda.TEXTO_MINA));
	}
	
	
	/**
	 * Test de texto solución generado para celda descubierta con un número de minas adyacentes.
	 * 
	 * @param número número
	 */
	@DisplayName("Texto solución generado para celda descubierta con número de minas adyacentes")
	@ParameterizedTest
	@CsvSource({ "1", "2", "3", "4", "5", "6", "7", "8"})
	void probarCambioTextoSolucionAlDescubrirConMinasAdyacentes(int número) {
		celda.establecerNumeroMinasAdyacentes(número); 
		assertThat("El texto solución para una celda oculta con número de minas adyacentes no es correcto.",  celda.obtenerTextoSolucion(), is(" " + número + " "));
		celda.establecerSiguienteEstadoDescubrir();
		assertThat("El texto solución para una celda descubierta con número de minas adyacentes no es correcto.",  celda.obtenerTextoSolucion(), is(" " + número + " "));
	}
	
	/**
	 * Test de texto a mostrar generado para celda descubierta con un número de minas adyacentes.
	 * 
	 * @param número número
	 */
	@DisplayName("Texto a mostrar generado para celda descubierta con número de minas adyacentes")
	@ParameterizedTest
	@CsvSource({ "1", "2", "3", "4", "5", "6", "7", "8"})
	void probarCambioTextoAMostrarAlDescubrirConMinasAdyacentes(int número) {
		celda.establecerNumeroMinasAdyacentes(número); 
		assertThat("El texto a mostrar para una celda oculta con número de minas adyacentes no es correcto.",  celda.obtenerTextoEstado(), is(" " + Estado.OCULTA.obtenerLetra() + " "));
		celda.establecerSiguienteEstadoDescubrir();
		assertThat("El texto a mostrar para una celda descubierta con número de minas adyacentes no es correcto.",  celda.obtenerTextoEstado(), is(" " + número + " "));
	}
	
	/**
	 * Test de texto a mostrar generado para celda marcada y desmarcada con bandera.
	 */
	@DisplayName("Texto a mostrar generado para celda marcada y desmarcada con bandera")
	@Test
	void probarCambioTextoAMostrarAlMarcarConBandera() {
		assertThat("El texto a mostrar para una celda oculta con 0 minas adyacentes no es correcto.",  celda.obtenerTextoEstado(), is(" " + Estado.OCULTA.obtenerLetra() + " "));
		celda.establecerSiguienteEstadoMarcar();
		assertThat("El texto a mostrar para una celda marcada con bandera con 0 minas  no es correcto.",  celda.obtenerTextoEstado(), is(" " + Estado.MARCADA.obtenerLetra() + " "));
		celda.establecerSiguienteEstadoMarcar();
		assertThat("El texto a mostrar al quitar la bandera en una celda oculta con 0 minas adyacentes no es correcto.",  celda.obtenerTextoEstado(), is(" " + Estado.OCULTA.obtenerLetra() + " "));
	}
	
	
	
}
