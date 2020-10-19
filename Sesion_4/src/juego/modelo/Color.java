/**
 *
 * @author Axel Rubio Gonzalez
 *
 */
package juego.modelo;

public enum Color {
    BLANCO('O'), NEGRO('X');
    private char caracter;

    private Color(char caracter) {
        this.caracter = caracter;
    }

    public char toChar() {
        return caracter;
    }

}
