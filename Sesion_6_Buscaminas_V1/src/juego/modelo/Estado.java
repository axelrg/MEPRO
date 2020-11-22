package juego.modelo;

/**
 * Enumeracion con los estados de la celda.
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public enum Estado {

    /**
     * Estado Marcado
     */
    MARCADA('P'),

    /**
     * Estado Descubierta
     */
    DESCUBIERTA('0'),

    /**
     * Estado Oculta
     */
    OCULTA('-');

    /***
     * Caracter que indica el estado.
     */
    private char letra;

    /**
     * Constructor de la clase, es private, esta es una clase enumerada.
     *
     * @param letra letra de estado
     */
    private Estado(char letra) {
        this.letra = letra;
    }

    /**
     * Devuelve el valor de letra.
     *
     * @return un caracter
     */
    public char obtenerLetra() {
        return letra;
    }

}
