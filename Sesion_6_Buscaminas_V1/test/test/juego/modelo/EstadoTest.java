package test.juego.modelo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import juego.modelo.Estado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests sobre la enumeración estado.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0 20181008
 */
@DisplayName("Tests sobre Estado")
public class EstadoTest {

	/**
	 * Comprueba la correcta inicialización de valores.
	 */
	@Test
	@DisplayName("Comprueba la inicialización de letras correcta en estado")
	void probarInicializacion() {
		assertAll(() -> assertThat("Letra incorrecta asignada", Estado.MARCADA.obtenerLetra() , is('P')),
				() -> assertThat("Letra incorrecta asignada", Estado.OCULTA.obtenerLetra() , is('-')),
				() -> assertThat("Letra incorrecta asignada", Estado.DESCUBIERTA.obtenerLetra(), is('0')));
	}
}
