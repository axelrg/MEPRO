package juego.control;

import juego.modelo.Tablero;

public class Arbitro {
    private Tablero tablero;

    public Arbitro() {
        tablero = new Tablero();
    }

    public Tablero consultarTablero() {
        return tablero.clonar();
    }

    public void descubrir(int fila, int columna) {
        if (esLegalDescubrir(fila, columna)) {
            tablero.descubrir(fila, columna);
        }

    }


    public void descubrirOcultas() {
        for (int i = 0; i < tablero.NUMERO_FILAS; i++) {
            for (int j = 0; j < tablero.NUMERO_COLUMNAS; j++) {
                if (esLegalDescubrir(i, j)) {
                    tablero.obtenerCelda(i, j).establecerSiguienteEstadoDescubrir();
                }
            }
        }
    }

    public boolean esLegalDescubrir(int fila, int columna) {
        if (tablero.obtenerCelda(fila, columna).estaOculta()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean esLegalDesmarcar(int fila, int columna) {
        if (tablero.obtenerCelda(fila, columna).estaMarcada()) {
            return true;
        } else {
            return false;
        }

    }

    private int contarCeldasMarcadas() {
        int celdasMarcadas = 0;
        for (int i = 0; i < tablero.NUMERO_FILAS; i++) {
            for (int j = 0; j < tablero.NUMERO_COLUMNAS; j++) {
                if (tablero.obtenerCelda(i, j).estaMarcada()) {
                    celdasMarcadas++;
                }
            }
        }
        return celdasMarcadas;
    }

    public boolean esLegalMarcar(int fila, int columna) {
        if (tablero.obtenerCelda(fila, columna).estaOculta() && contarCeldasMarcadas() < 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean haExplotadoAlgunaMina() {
        if (tablero.contarMinasExplotadas() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean haFinalizadoConExito() {
        if (haExplotadoAlgunaMina()) {
            return false;
        }
        for (int i = 0; i < tablero.NUMERO_FILAS; i++) {
            for (int j = 0; j < tablero.NUMERO_COLUMNAS; j++) {
                if (tablero.obtenerCelda(i, j).tieneMina() && tablero.obtenerCelda(i, j).estaMarcada() || !tablero.obtenerCelda(i, j).tieneMina() && tablero.obtenerCelda(i, j).estaDescubierta()) {
                    continue;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    public void marcarODesmarcar(int fila, int columna) {
        tablero.obtenerCelda(fila, columna).establecerSiguienteEstadoMarcar();
    }

}
