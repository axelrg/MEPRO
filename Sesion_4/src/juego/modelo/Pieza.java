/**
 *
 * @author Axel Rubio Gonzalez
 *
 */
package juego.modelo;

public class Pieza {
    Color color;
    Celda celda;

    public Pieza(Color color) {
        this.color = color;
    }

    public Color obtenerColor() {
        return color;
    }

    private void establecerColor(Color color) {
        this.color = color;
    }

    public Celda obtenerCelda() {
        return celda;
    }

    public void establecerCelda(Celda celda) {
        this.celda = celda;
    }

    public String toString() {
        return String.format("%c", color.toChar());
    }
}
