package juego.modelo;

/**
 * La clase pieza que representa una pieza del tres en raya.
 * Cada pieza cuenta con un color y una celda a la que esta asignada.
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class Pieza {
    /**Color de la pieza.*/
    Color color;
    /**Celda en que esta la pieza.*/
    Celda celda;

    /**
     * Constructor de la clase, crea una pieza con un color determinado.
     * @param color color de la pieza
     */
    public Pieza(Color color) {
        this.color = color;
    }

    /**
     * Getter del parametro color.
     * @return devuelve el color de la pieza
     */
    public Color obtenerColor() {
        return color;
    }

    /**
     * Setter del parametro color.
     * @param color color de la pieza
     */
    private void establecerColor(Color color) {
        this.color = color;
    }

    /**
     * Getter de la celda en la que esta una pieza.
     * @return la celda en la que esta la pieza
     */
    public Celda obtenerCelda() {
        return celda;
    }

    /**
     * Setter de la celda en la que esta una pieza.
     * @param celda  la celda en la que esta la pieza
     */
    public void establecerCelda(Celda celda) {
        this.celda = celda;
    }

    /**
     * Devuelve un string con las caracteristicas del objeto.
     */
    public String toString() {
        return String.format("%c", color.toChar());
    }
}
