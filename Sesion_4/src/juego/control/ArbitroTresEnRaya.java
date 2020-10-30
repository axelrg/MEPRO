package juego.control;

import juego.modelo.*;
import juego.util.*;

/**
 * La clase arbitro representa las reglas del juego.
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class ArbitroTresEnRaya {
    private static final int NUM_GANADOR = 3;
    private int numeroJugadores = 0;
    public Tablero tablero = new Tablero(3, 3);
    private boolean juegoAcabado = false;
    private Jugador jugador1;
    private Jugador jugador2;


    public ArbitroTresEnRaya() {
    }

    public void registrarJugador(String nombre) {
        if (numeroJugadores >= 2)
            return;

        if (numeroJugadores == 0) {
            jugador1 = new Jugador(nombre, Color.BLANCO);
        }

        if (numeroJugadores == 1) {
            jugador2 = new Jugador(nombre, Color.NEGRO);
        }
        numeroJugadores++;

    }

    public Jugador obtenerTurno() {
        if (numeroJugadores == 2) {
            if (tablero.obtenerNumeroPiezas(Color.BLANCO) > tablero.obtenerNumeroPiezas(Color.NEGRO)) {
                return jugador2;
            }

            if (tablero.obtenerNumeroPiezas(Color.NEGRO) > tablero.obtenerNumeroPiezas(Color.BLANCO)) {
                return jugador1;
            }
            return jugador1;
        }
        return null;
    }

    public Jugador obtenerGanador() {
        Jugador jugador = null;
        for (int fila = 0; fila < tablero.obtenerNumeroFilas(); fila++) {
            for (int columna = 0; columna < tablero.obtenerNumeroColumnas(); columna++) {
                for (Direccion direccion : Direccion.values()) {
                    int i = 0;
                    i = tablero.contarPiezas(tablero.obtenerCelda(fila, columna), direccion);
                    if (i == 3) {
                        if (tablero.obtenerCelda(fila, columna).obtenerPieza().obtenerColor() == jugador1.obtenerColor())
                            jugador = jugador1;
                        if (tablero.obtenerCelda(fila, columna).obtenerPieza().obtenerColor() == jugador2.obtenerColor())
                            jugador = jugador2;

                    }

                }
            }
        }

        return jugador;
    }


    public void jugar(int x, int y) {
        if (esMovimientoLegal(x, y))
            tablero.colocar(obtenerTurno().generarPieza(), tablero.obtenerCelda(x, y));

        estaAcabado();

    }

    public boolean esMovimientoLegal(int x, int y) {
        if (juegoAcabado == true)
            return false;

        if (tablero.estaEnTablero(x, y) & tablero.obtenerCelda(x, y).obtenerPieza() == null)
            return true;
        else
            return false;
    }

    public Tablero obtenerTablero() {
        return tablero;
    }

    public boolean estaAcabado() {
        if (obtenerGanador() != null)
            return true;

        if (tablero.estaCompleto())
            return true;

        return false;
    }

}
