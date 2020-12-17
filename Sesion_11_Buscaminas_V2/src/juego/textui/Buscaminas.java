package juego.textui;

import java.util.Scanner;

import juego.control.Arbitro;
import juego.control.ArbitroInseguro;
import juego.control.ArbitroSeguro;
import juego.modelo.Tablero;
import juego.util.CoordenadasIncorrectasException;

/**
 * Buscaminas en modo texto.
 * <p>
 * Se abusa del uso de static tanto en atributos como en métodos para comprobar
 * su similitud a variables globales y funciones globales de otros lenguajes.
 *
 * @author <a href="rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 * @since 2.0
 */
public class Buscaminas {

    /**
     * Arbitro.
     */
    private static Arbitro arbitro;

    /**
     * Lector por teclado.
     */
    private static Scanner scanner;

    /**
     * Flag de trampas.
     */
    private static boolean trampas;

    /**
     * Flag de modo seguro.
     */
    private static boolean seguro;

    /**
     * Método raíz.
     *
     * @param args argumentos de entrada
     */
    public static void main(String[] args) {
        trampas = comprobarTrampas(args);
        seguro = comprobarModoSeguro(args);
        mostrarMensajeBienvenida();
        inicializarPartida(comprobarModoSeguro(args));
        do {
            mostrarTableroEnPantalla();
            String texto = recogerJugada();
            if (comprobarSiFinalizaUsuarioPartida(texto)) {
                break;
            } else if (hayQueDescubrirOcultas(texto)) {
                descubrirTodaCeldasOcultas();
            } else {
                if (validarFormato(texto)) {
                    realizarJugada(texto);
                    hacerTrampas(comprobarTrampas(args));
                } else {
                    mostrarErrorEnFormatoDeEntrada();
                }
            }

        } while (!esFinDePartida());

        finalizarPartida();
    }

    /**
     * Descubre todas las celdas ocultas para finalizar partida.
     */
    private static void descubrirTodaCeldasOcultas() {
        arbitro.descubrirOcultas();
    }

    /**
     * Comprueba los argumentos de ejecución para comprobar si hacemos trampas o no.
     *
     * @param args argumentos
     * @return true si el usuario quiere jugar haciendo trampas mostrando la
     * solución en pantalla
     */
    private static boolean comprobarTrampas(String[] args) {
        boolean trampas = false;
        if (args.length == 1 && args[0].toLowerCase().equals("trampas")) {
            trampas = true;
        } else if (args.length == 2 && args[1].toLowerCase().equals("trampas")) {
            trampas = true;
        }
        return trampas;
    }

    /**
     * Comprueba los argumentos de ejecución para comprobar si jugamos en modo
     * seguro o inseguro.
     *
     * @param args argumentos
     * @return true si el usuario quiere jugar en modo seguro, o false en caso
     * contrario
     */
    private static boolean comprobarModoSeguro(String[] args) {
        boolean seguro = true; // por defecto en seguro
        // si hay un argumentos comprobamos el primero...
        if (args.length >= 1 && args[0].toLowerCase().equals("inseguro")) {
            seguro = false;
        }
        return seguro;

    }

    /**
     * Comprueba si ha finalizado la partida.
     *
     * @return true si ha explotado alguna mina o hemos desmarcado todas las celdas
     * ocultas menos las minas
     */
    private static boolean esFinDePartida() {
        return (arbitro.haExplotadoAlgunaMina() || arbitro.haFinalizadoConExito());
    }

    /**
     * Realiza la jugada introducida por teclado realizando las correspondientes
     * comprobaciones relativas a las reglas del juego.
     * <p>
     * Se supone que la jugada en cuanto al formato ya ha sido validada previamente.
     *
     * @param jugada jugada
     */
    private static void realizarJugada(String jugada) {
        try {
            int fila = leerFila(jugada);
            int columna = leerColumna(jugada);
            if (estaColocandoOQuitandoBandera(jugada)) {
                if (arbitro.esLegalMarcar(fila, columna) || arbitro.esLegalDesmarcar(fila, columna)) {
                    // si el movimiento de marcado es legal
                    arbitro.marcarODesmarcar(fila, columna);
                } else {
                    System.out.println(
                            "Movimiento ilegal.\nComprueba que no estás marcando una celda ya marcada o descubierta\n"
                                    + "o que no estás intentando colocar más banderas que las permitidas.");
                }
            } else {
                if (arbitro.esLegalDescubrir(fila, columna)) { // si el movimiento de descubrir es legal
                    arbitro.descubrir(fila, columna);
                } else {
                    System.out.println(
                            "Movimiento ilegal.\nComprueba que no estás descubriendo una celda ya descubierta o marcada.");
                }
            }
        } catch (CoordenadasIncorrectasException ex) {
            throw new RuntimeException("Error interno en lectura de valores de coordenadas.", ex);
        }
    }

    /**
     * Muestra el mensaje de bienvenida con instrucciones para finalizar la partida.
     */
    private static void mostrarMensajeBienvenida() {
        System.out.println("Bienvenido al juego del Buscaminas");
        System.out.println("Jugando en modo " + (seguro ? "seguro" : "inseguro"));
        System.out.println("Jugando " + (trampas ? "con trampas" : "sin trampas"));
        System.out.println("Para finalizar el juego introduzca \"salir\".");
        System.out.println("Disfrute de la partida...");
    }

    /**
     * Mostrar al usuario información de error en el formato de entrada, mostrando
     * ejemplos.
     */
    private static void mostrarErrorEnFormatoDeEntrada() {
        System.out.println();
        System.out.println("Error en el formato de entrada.");
        System.out.println(
                "El formato debe ser filacolumna y opcionalmente una m o M para marcar con bandera, por ejemplo 34 o 34m");
        System.out.println("Los números estarán en el rango [0,7] y la letra opcional deberá ser una m o M\n");
    }

    /**
     * Comprueba si el usuario quiere finalizar la partida.
     *
     * @param jugada jugada en formato texto
     * @return true si el usuario introduce salir, false en caso contrario
     */
    private static boolean comprobarSiFinalizaUsuarioPartida(String jugada) {
        return jugada.equalsIgnoreCase("salir");
    }

    /**
     * Comprueba si el usuario quiere descubrir celdas ocultas.
     *
     * @param jugada jugada en formato texto
     * @return true si el usuario introduce salir, false en caso contrario
     */
    private static boolean hayQueDescubrirOcultas(String jugada) {
        return jugada.equalsIgnoreCase("descubrir");
    }

    /**
     * Finaliza la partida informando al usuario y cerrando recursos abiertos.
     */
    private static void finalizarPartida() {
        if (arbitro.haExplotadoAlgunaMina()) {
            System.out.println(arbitro.consultarTablero().toString());
            System.out.println("Lo siento. ¡Has perdido la partida!");
            System.out.println(arbitro.consultarTablero().obtenerSolucion());
        } else if (arbitro.haFinalizadoConExito()) {
            System.out.println(arbitro.consultarTablero().toString());
            System.out.println("¡Enhorabuena! Has ganado la partida.");
        }
        cerrarRecursos();
    }

    /**
     * Cierra partida y recursos abiertos.
     */
    private static void cerrarRecursos() {
        System.out.println("Partida finalizada.");
        if (scanner != null) {
            scanner.close(); // cerramos recurso
        }
    }

    /**
     * Muestra el tablero en pantalla con su estado actual.
     */
    private static void mostrarTableroEnPantalla() {
        System.out.println(arbitro.consultarTablero().toString());
        System.out.println();
    }

    /**
     * Inicializa el estado de los elementos de la partida.
     *
     * @param seguro flag para jugar en modo seguro o inseguro
     */
    private static void inicializarPartida(boolean seguro) {
        // Inicializaciones
        if (seguro) {
            arbitro = new ArbitroSeguro(new Tablero());
        } else {
            arbitro = new ArbitroInseguro(new Tablero());
        }
        // Abrimos la lectura desde teclado
        scanner = new Scanner(System.in);
    }

    /**
     * Recoge jugada del teclado.
     *
     * @return jugada jugada en formato texto
     */
    private static String recogerJugada() {
        System.out.print("Introduce jugada: (máscara fc / fc(M|m)): ");
        return scanner.next();
    }

    /**
     * Valida la corrección del formato de la jugada. Solo comprueba la corrección
     * del formato de entrada. La jugada tiene que tener como máximo tres caracteres
     * y contener dos números en el rango del tamaño del tablero y una tercera letra
     * opcional para indicar que se quiere poner o quitar la bandera.
     * <p>
     * Otra mejor solución alternativa es el uso de expresiones regulares (se verán
     * en la asignatura de 3º Procesadores del Lenguaje).
     *
     * @param jugada a validar
     * @return true si el formato de la jugada es correcta
     */
    private static boolean validarFormato(String jugada) {
        boolean estado = false;
        if (jugada.length() >= 2 && esNumeroValido(jugada.charAt(0)) && esNumeroValido(jugada.charAt(1))) {
            if (jugada.length() == 2) {
                estado = true;
            } else if (jugada.length() == 3 && esLetraValida(jugada.charAt(jugada.length() - 1))) {
                estado = true;
            }
        }
        return estado;
    }

    /**
     * Comprueba si la letra es una m o M.
     *
     * @param letra letra a comprobar
     * @return true si la letra no indica marcar con m o M
     */
    private static boolean esLetraValida(char letra) {
        return letra == 'm' || letra == 'M';
    }

    /**
     * Comprueba si el número (en formato letra) está fuera del rango [0,7].
     *
     * @param numero numero
     * @return true si el número no está en el rango, false en caso contrario
     */
    private static boolean esNumeroValido(char numero) {
        return numero >= '0' && numero <= '7';
    }

    /**
     * Obtiene el número de fila.
     *
     * @param jugada texto con la jugada
     * @return número de fila
     * @throws NumberFormatException si el carácter no puede convertirse a número
     */
    private static int leerFila(String jugada) throws NumberFormatException {
        return Integer.parseInt(Character.toString(jugada.charAt(0)));
    }

    /**
     * Obtiene el número de columna.
     *
     * @param jugada texto con la jugada
     * @return número de columna
     * @throws NumberFormatException si el carácter no puede convertirse a número
     */
    private static int leerColumna(String jugada) throws NumberFormatException {
        return Integer.parseInt(Character.toString(jugada.charAt(1)));
    }

    /**
     * Comprueba si la cadena de texto tiene un tercer carácter válido que indica
     * que se está colocando o quitando bandera.
     *
     * @param jugada texto con la jugada
     * @return true si la cadena tiene 3 caracteres y el último caracter es una
     * letra válida
     */
    private static boolean estaColocandoOQuitandoBandera(String jugada) {
        return jugada.length() == 3 && esLetraValida(jugada.charAt(jugada.length() - 1));
    }

    /**
     * Comprueba si se juega en modo mostrar solución.
     *
     * @param flag bandera para mostrar la solución o no
     */
    private static void hacerTrampas(boolean flag) {
        if (flag) {
            System.out.println("*** SOLUCIÓN ***");
            System.out.println(arbitro.consultarTablero().obtenerSolucion());
        }
    }

}
