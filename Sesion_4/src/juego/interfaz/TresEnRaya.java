
package juego.interfaz;

import juego.control.ArbitroTresEnRaya;
import juego.modelo.Tablero;
import juego.modelo.*;

import java.util.Scanner;


/**
 * Juego del tres en raya. Ejercicio práctico - Metodología de la Programación.
 * <p>
 * 2º GII - Curso 2020-2021
 *
 * @author Axel Rubio González
 */
public class TresEnRaya {

    /**
     * Número de argumentos máximo.
     */
    private static final int NUM_ARGUMENTOS = 2;


    /**
     * Arbitro.
     */
    private static ArbitroTresEnRaya arbitro;

    /**
     * Flujo de ejecución principal del juego.
     *
     * @param args nombres de los jugadores
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in); // teclado permite leer enteros con el método nextInt
        arbitro = new ArbitroTresEnRaya();

        arbitro.registrarJugador("Juan");
        arbitro.registrarJugador("Pepe");
        //Tablero tablero  = new Tablero(3,3);
        while (arbitro.estaAcabado() == false) {
            int x;
            int y;
            System.out.println(arbitro.tablero.toString());
            System.out.println("Introduce la coordenada x:");
            x = teclado.nextInt();
            System.out.println("Introduce la coordenada y:");
            y = teclado.nextInt();

            if (arbitro.esMovimientoLegal(x, y)) {
                Pieza pieza = arbitro.obtenerTurno().generarPieza();
                arbitro.tablero.colocar(pieza, arbitro.tablero.obtenerCelda(x, y));
            }


            // Inicialización del juego según los argumentos validados
            // COMPLETAR POR EL ALUMNO

            // Fase de juego, iteramos sobre las distintas jugadas.
            // COMPLETAR POR EL ALUMNO

            // mientras la partida no esté acabada leer de teclado fila, columna, y si la
            // jugada es válida, completarla
            // Tip: para leer de teclado un entero usaríamos: int valor = teclado.nextInt();

            // Informar del resultado final de la partida (ganador o tablas)
            // COMPLETAR POR EL ALUMNO


        } // número de argumentos correcto
        teclado.close();
    }

    /**
     * Muestra la ayuda en línea para la inicialización correcta del juego.
     */
    private static void mostrarAyuda() {
        // COMPLETAR...
    }

    /**
     * Muestra el estado actual del tablero.
     *
     * @param tablero tablero a pintar en pantalla.
     */
    private static void mostrarTablero(Tablero tablero) {
        // COMPLETAR
    } // mostrarTablero


    // AÑADIR MÁS MÉTODOS NECESARIOS PARA EL MODO TEXTO...
} // TresEnRaya
