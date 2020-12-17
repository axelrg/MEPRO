package juego.modelo;

import java.util.Objects;

/**
 * La clase celda representa las celdas que componen el tablero.
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class Celda {
    /**
     * Constante tipo String con el texto para mina.
     */
    public static final String TEXTO_MINA = " M ";
    /**
     * Entero que guarda la fila de la celda.
     */
    private int fila;
    /**
     * Entero que guarda la columna de la celda.
     */
    private int columna;
    /**
     * guarda el estado de la celda.
     */
    private Estado estado;
    /**
     * Booleano para indicar si la celda tiene mina o no.
     */
    private boolean tieneMina;
    /**
     * Entero para indicar el numero de minas en las celdas adyacentes.
     */
    private int numeroMinas;

    /**
     * Constructor de la clase celda.
     *
     * @param fila    fila de la celda
     * @param columna columna de la celda
     */
    public Celda(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.estado = Estado.OCULTA;
        tieneMina = false;
    }

    /**
     * Clona la celda.
     *
     * @return devuelve una celda identica
     */
    public Celda clonar() {

        /**
         * Creamos un nuevo objeto celda
         * */
        Celda celda = new Celda(this.fila, this.columna);
        celda.estado = estado;
        celda.tieneMina = tieneMina();
        celda.numeroMinas = obtenerNumeroMinasAdyacentes();
        return celda;
    }

    /**
     * Coloca una mina en la celda.
     */
    void colocarMina() {
        tieneMina = true;
    }

    /**
     * Setter de numeroMinas.
     *
     * @param numeroMinas entero con las minas adyacentes
     */
    void establecerNumeroMinasAdyacentes(int numeroMinas) {

        this.numeroMinas = numeroMinas;
    }

    /**
     * Cambia el estado a descubierta (solo si esta oculta).
     */
    void establecerSiguienteEstadoDescubrir() {
        if (estaOculta()) {
            estado = Estado.DESCUBIERTA;
        }
    }

    /**
     * Cambia el estado a marcado (solo si esta oculta).
     */
    void establecerSiguienteEstadoMarcar() {
        if (estaOculta()) {
            estado = Estado.MARCADA;
        } else if (estaMarcada()) {
            estado = Estado.OCULTA;
        }
    }

    /**
     * Devuelve verdadero si esta descubierta la celda.
     *
     * @return booleano para indicar true si esta descubierta y false si no
     */
    public boolean estaDescubierta() {
        return estado == Estado.DESCUBIERTA;
    }

    /**
     * Devuelve verdadero si esta marcada la celda.
     *
     * @return booleano para indicar true si esta marcada y false si no
     */
    public boolean estaMarcada() {
        return estado == Estado.MARCADA;
    }

    /**
     * Devuelve verdadero si esta oculta la celda.
     *
     * @return booleano para indicar true si esta oculta y false si no
     */
    public boolean estaOculta() {
        return estado == Estado.OCULTA;
    }

    /**
     * Gettter de la columna de la celda.
     *
     * @return entero con la columna
     */
    public int obtenerColumna() {
        return columna;
    }

    /**
     * Getter de  la fila de la celda.
     *
     * @return entero con la fila
     */
    public int obtenerFila() {
        return fila;
    }

    /**
     * Getter del número de minas adyacentes.
     *
     * @return entero con el numero de minas
     */
    public int obtenerNumeroMinasAdyacentes() {
        return numeroMinas;
    }

    /**
     * Imprime el texto con el estado de la celda.
     *
     * @return string con el estado de la celda
     */
    public String obtenerTextoEstado() {
        if (estado == Estado.DESCUBIERTA) {
            if (tieneMina()) {
                return TEXTO_MINA;
            } else {
                if (obtenerNumeroMinasAdyacentes() != 0) {
                    return " " + obtenerNumeroMinasAdyacentes() + " ";
                } else {
                    return " . ";
                }
            }
        } else {
            return " " + estado.obtenerLetra() + " ";
        }

    }

    /**
     * Imprime el texto con la solucion de la celda.
     *
     * @return string con la solucion de la celda
     */
    public String obtenerTextoSolucion() {
        if (tieneMina()) {
            return TEXTO_MINA;
        } else {
            if (obtenerNumeroMinasAdyacentes() != 0) {
                return " " + obtenerNumeroMinasAdyacentes() + " ";
            } else {
                return " - ";
            }
        }

    }

    /**
     * Compara si dos celdas tienen las mismas coordenadas.
     *
     * @param celda celda a comparar
     * @return booleano, verdadero si son iguales, falso si no.
     */
    public boolean tieneCoordenadasIguales(Celda celda) {
        return celda.obtenerFila() == obtenerFila() && celda.obtenerColumna() == obtenerColumna();
    }

    /**
     * Metodo equals.
     * Hay que usar Override ya que el metodo existe en la clase object.
     *
     * @param o objeto
     * @return booleano
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Celda celda = (Celda) o;
        return fila == celda.fila &&
                columna == celda.columna &&
                tieneMina == celda.tieneMina &&
                numeroMinas == celda.numeroMinas &&
                estado == celda.estado;
    }

    /**
     * Metodo hashCode.
     * Hay que usar Override ya que el metodo existe en la clase object.
     *
     * @return entero con un hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(fila, columna, estado, tieneMina, numeroMinas);
    }

    /**
     * Getter de tiene mina.
     *
     * @return booleano, true si la celda tiene mina, falso si no
     */
    public boolean tieneMina() {
        return tieneMina;
    }

    /**
     * Devuelve el estado (todas las variables) de la celda.
     * Hay que usar Override ya que el metodo existe en la clase object.
     *
     * @return string
     */
    @Override
    public String toString() {
        return "[(" + fila + "," + columna + ")-" + obtenerNumeroMinasAdyacentes() + "-" + estado + "]";
    }


}
