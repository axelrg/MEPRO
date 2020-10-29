package juego.modelo;

/**
 * (El codigo completamente comentado es el de pieza).
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
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
