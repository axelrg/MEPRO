package juego.modelo;

import java.util.Random;
import juego.control.DistanciaChebyshev;
import juego.util.Sentido;

/**
 * La clase tablero representa el tablero del juego.
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class Tablero {
    /**
     * Constante con el numero de filas.
     */
    public final int NUMERO_FILAS = 8;
    /**
     * Constante con el numero de columnas.
     */
    public final int NUMERO_COLUMNAS = 8;
    /**
     * Matriz formada por celdas que representa el tablero.
     */
    Celda[][] tablero;

    /**
     * Constructor de la clase tablero.
     * Crea cada objeto celda que compone el tablero.
     */
    public Tablero() {
        tablero = new Celda[NUMERO_FILAS][NUMERO_COLUMNAS];
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                tablero[i][j] = new Celda(i, j);
            }
        }
    }

    /**
     * Indica si la celda esta en el tablero.
     *
     * @param fila    fila de la celda
     * @param columna columna de la celda
     * @return booleano true si las cordenadas estan en el tablero
     */
    private boolean estaEnTablero(int fila, int columna) {
        return fila >= 0 && fila < NUMERO_FILAS && columna >= 0 && columna < NUMERO_COLUMNAS;
    }

    /**
     * Clona el tablero.
     *
     * @return un clon del tablero
     */
    public Tablero clonar() {
        /**
         * Clon del tablero
         */
        Tablero tablero1 = new Tablero();
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                tablero1.tablero[i][j] = this.clonarCelda(i, j);
            }
        }
        return tablero1;
    }

    /**
     * Clona la celda.
     *
     * @param fila    fila de la celda
     * @param columna columna de la celda
     * @return un clon de la celda o null si no esta en el tablero la fila y columa dada
     */
    public Celda clonarCelda(int fila, int columna) {
        if (estaEnTablero(fila, columna)) {
            return obtenerCelda(fila, columna).clonar();
        } else {
            return null;
        }
    }

    /**
     * Clona todas las celdas del tablero en un array unidimensional.
     *
     * @return un array con todos los clones de las celdas
     */
    public Celda[] clonarCeldas() {
        /**
         * Array de celdas
         */
        Celda[] array = new Celda[NUMERO_COLUMNAS * NUMERO_FILAS];
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                array[i * NUMERO_FILAS + j] = obtenerCelda(i,j);
            }
        }

        return array;
    }

    /**
     * Coloca las minas en las celdas del tablero segun la semilla, cumpliendo la distancia.
     *
     * @param inicio    celda de inicio
     * @param distancia objeto de la clase DistanciaChebyshev
     * @param rd        objeto de la clase Random
     */
    public void colocarMinas(Celda inicio, DistanciaChebyshev distancia, Random rd) {
        /**
         * Contador que lleva el recuento del numero de minas ya colocadas
         */
        int numeroMinas = 0;
        /**
         * Variable para guardar coordenadas x
         */
        int x;
        /**
         * Variable para guardar coordenadas y
         */
        int y;
        /**
         * Establecemos la semilla
         */
        rd.setSeed(inicio.obtenerFila() * 10L + inicio.obtenerColumna());
        while (numeroMinas < 10){
            x = rd.nextInt(NUMERO_FILAS);
            y = rd.nextInt(NUMERO_COLUMNAS);
            /**
             * Creamos la celda fin con las coordenadas aleatorias
             */
            Celda fin = new Celda(x, y);
            if (x != inicio.obtenerFila() || y != inicio.obtenerColumna())
                if (distancia.calcular(fin, inicio) >= 2 && estaEnTablero(x, y) && !tablero[x][y].tieneMina()) {
                    obtenerCelda(x,y).colocarMina();
                    numeroMinas++;
                }
        }
        calcularMinasAdyacentes();
    }

    /**
     * Calcula el numero de minas adyacentes a cada celda del tablero.
     * Si la celda tiene una mina establece el valor -1.
     */
    private void calcularMinasAdyacentes() {
        /**
         * Array que guarda todos los sentidos
         */
        Sentido[] sentidos = Sentido.values();

        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                /**
                 * Contador para el numero de minas
                 */
                int minas = 0;
                for (Sentido sentido : sentidos) {
                    if (estaEnTablero(i + sentido.obtenerDesplazamientoEnFilas(), j + sentido.obtenerDesplazamientoEnColumnas()) && tablero[i + sentido.obtenerDesplazamientoEnFilas()][j + sentido.obtenerDesplazamientoEnColumnas()].tieneMina()) {
                        minas++;
                    }
                }
                if (obtenerCelda(i,j).tieneMina()) {
                    obtenerCelda(i,j).establecerNumeroMinasAdyacentes(-1);
                } else {
                    obtenerCelda(i,j).establecerNumeroMinasAdyacentes(minas);
                }
            }
        }
    }

    /**
     * Cuenta las banderas que hay en el tablero.
     *
     * @return entero con el numero de banderas
     */
    public int contarBanderas() {
        /**
         * Contador local para el numero de banderas
         */
        int total = 0;
        for (int i = 0; i < obtenerNumeroFilas(); i++) {
            for (int j = 0; j < obtenerNumeroColumnas(); j++) {
                if (obtenerCelda(i,j).estaMarcada()) {
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * Cuenta las celdas descubiertas que hay en el tablero.
     *
     * @return entero con el numero de celdas descubiertas
     */
    public int contarCeldasDescubiertas() {
        /**
         * Contador local para el numero de celdas descubiertas
         */
        int total = 0;
        for (int i = 0; i < obtenerNumeroFilas(); i++) {
            for (int j = 0; j < obtenerNumeroColumnas(); j++) {
                if (obtenerCelda(i,j).estaDescubierta()) {
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * Cuenta las minas que hay en el tablero.
     *
     * @return entero con el numero de minas
     */
    public int contarMinas() {
        /**
         * Contador local para el numero de minas
         */
        int total = 0;
        for (int i = 0; i < obtenerNumeroFilas(); i++) {
            for (int j = 0; j < obtenerNumeroColumnas(); j++) {
                if (obtenerCelda(i,j).tieneMina()) {
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * Cuenta las minas que hay en el tablero.
     *
     * @return entero con el numero de minas
     */
    public int contarMinasExplotadas() {
        /**
         * Contador local para el numero de minas explotadas
         */
        int total = 0;
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                if (obtenerCelda(i,j).estaDescubierta() && obtenerCelda(i,j).tieneMina()) {
                    total++;
                }
            }
        }
        return total;
    }

    /*
      Ejemplo for each

      Estado[] valores = Estado.values()

      for(Estado valor : valores){
          sout("%c%n", valor.obtenerLetra());
      }
     */

    /**
     * Descubre las celdas siguiendo las reglas del buscaminas.
     * Si no hay minas invoca a la función que las coloca.
     *
     * @param fila    fila de la celda
     * @param columna columna de la celda
     */
    public void descubrir(int fila, int columna) {

        if (estaEnTablero(fila, columna)) {
            if (contarMinas() == 0) {
                /**
                 * Creacion de un nuevo objeto distancia
                 */
                DistanciaChebyshev distancia = new DistanciaChebyshev();
                /**
                 * Creacion de un nuevo objeto random
                 */
                Random rd = new Random();
                colocarMinas(obtenerCelda(fila, columna), distancia, rd);
            }

            if (!obtenerCelda(fila, columna).tieneMina() && obtenerCelda(fila, columna).obtenerNumeroMinasAdyacentes() == 0) {
                obtenerCelda(fila, columna).establecerSiguienteEstadoDescubrir();
                Sentido[] sentidos = Sentido.values();
                for (Sentido sentido : sentidos) {
                    /**
                     * Contador para alterar la fila segun avanza el algoritmo
                     */
                    int fila1 = fila + sentido.obtenerDesplazamientoEnFilas();
                    /**
                     * Contador para alterar la columna segun avanza el algoritmo
                     */
                    int columna1 = columna + sentido.obtenerDesplazamientoEnColumnas();

                    if (estaEnTablero(fila1, columna1)) {
                        if (!obtenerCelda(fila1, columna1).tieneMina()) {
                            if (obtenerCelda(fila1,columna1).estaOculta()) {
                                if (obtenerCelda(fila1,columna1).obtenerNumeroMinasAdyacentes() != 0) {
                                    obtenerCelda(fila1,columna1).establecerSiguienteEstadoDescubrir();
                                } else {
                                    descubrir(fila1, columna1);
                                }
                            }
                        }
                    }
                }
            } else {
                tablero[fila][columna].establecerSiguienteEstadoDescubrir();
            }
        }
    }

    /**
     * Marca o desmarca según sea el estado inicial de la celda.
     *
     * @param fila    fila de la celda
     * @param columna columna de la celda
     */
    public void marcarDesmarcar(int fila, int columna) {
        obtenerCelda(fila,columna).establecerSiguienteEstadoMarcar();
    }

    /**
     * Getter de la celda.
     * Si las coordenadas no estan en el tablero devuelve null.
     *
     * @param fila    fila de la celda
     * @param columna columna de la celda
     * @return la celda con las coordenadas dadas
     */
    Celda obtenerCelda(int fila, int columna) {
        if (estaEnTablero(fila, columna)) {
            return tablero[fila][columna];
        } else {
            return null;
        }
    }

    /**
     * Calcula el numero de columnas.
     *
     * @return  entero con el numero de columnas
     */
    public int obtenerNumeroColumnas() {
        return tablero[0].length;
    }

    /**
     * Calcula el numero de filas.
     *
     * @return  entero con el numero de filas
     */
    public int obtenerNumeroFilas() {
        return tablero.length;
    }

    /**
     * Devuelve la solucion del juego.
     *
     * @return  string con la solucion del juego
     */
    public String obtenerSolucion() {
        /**
         * String en el que se guardan resultados parciales hasta el final
         */
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < NUMERO_FILAS; i++) {
            texto.append('\n').append(i).append('\t');
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                texto.append(obtenerCelda(i,j).obtenerTextoSolucion());
            }
        }
        texto.append('\n').append('\n').append('\t').append(" 0  1  2  3  4  5  6  7 ").append('\n');
        return texto.toString();
    }

    /**
     * Devuelve el tablero no solucionado del juego.
     *
     * @return  string con el estado actual del tablero
     */
    @Override
    public String toString() {
        /**
         * String en el que se guardan resultados parciales hasta el final
         */
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < NUMERO_FILAS; i++) {
            texto.append('\n').append(i).append('\t');
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                texto.append(obtenerCelda(i,j).obtenerTextoEstado());
            }
        }
        texto.append('\n').append('\n').append('\t').append(" 0  1  2  3  4  5  6  7 ").append('\n');
        return texto.toString();
    }
}
