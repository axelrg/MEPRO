
package juego.modelo;

/**
 * La clase jugador representa al jugador del juego.
 * El jugador tiene un nombre y un color
 *
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class Jugador {
    /**
     * String que contiene el nombre del jugador.
     */
    String nombre;
    /**
     * String que contiene el color de las fichas del jugador.
     */
    Color color;

    /**
     * Constructor de la clase, crea un jugador con su color de piezas y nombre.
     *
     * @param nombre nombre del jugador
     * @param color color de sus piezas
     */
    public Jugador(String nombre, Color color) {
        this.nombre = nombre;
        this.color = color;
    }

    /**
     * Getter del parametro color.
     *
     * @return devuelve el color de las piezas del jugador
     */
    public Color obtenerColor() {
        return color;
    }

    /**
     * Getter del parametro nombre.
     *
     * @return devuelve el nombre del jugador
     */
    public String obtenerNombre() {
        return nombre;
    }

    /**
     * Genera una pieza con el color del jugador.
     *
     * @return una pieza con ese color
     */
    public Pieza generarPieza() {
        Pieza pieza = new Pieza(color);
        return pieza;
    }

    /**
     * Genera un string con las propiedades del jugador.
     *
     * @return un string
     */
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", color=" + color +
                '}';
    }
}
