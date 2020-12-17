package juego.control;

import juego.modelo.Celda;

/**
 * La clase DistanciaChebyshev es la encargada de calcular la distancia entre dos celdas.
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class DistanciaChebyshev {

    /**
     * Método que calcula la distancia entre dos celdas.
     *
     * @param origen  celda que descubre primero el jugador
     * @param elegida celda a la que calculamos la distancia desde origen
     * @return entero con la distancia entre las dos celdas
     */
    public int calcular(Celda origen, Celda elegida) {
        return Math.max(Math.abs(origen.obtenerFila() - elegida.obtenerFila()), Math.abs(origen.obtenerColumna() - elegida.obtenerColumna()));
    }
}
