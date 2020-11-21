package juego.control;

import juego.modelo.Celda;

public class DistanciaChebyshev {
    public int calcular(Celda origen, Celda elegida){
        int distancia=0;
        distancia= Math.max(Math.abs(origen.obtenerFila()-elegida.obtenerFila()),Math.abs(origen.obtenerColumna()-elegida.obtenerColumna()));
        return distancia;
    }
}
