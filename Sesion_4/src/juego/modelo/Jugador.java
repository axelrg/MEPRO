/**
 *
 * @author Axel Rubio Gonzalez
 *
 */
package juego.modelo;

public class Jugador {
    String nombre;
    Color color;

    public Jugador(String nombre, Color color) {
        this.nombre = nombre;
        this.color = color;
    }

    public Color obtenerColor() {
        return color;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public Pieza generarPieza() {
        Pieza pieza = new Pieza(color);
        return pieza;
    }

    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", color=" + color +
                '}';
    }
}
