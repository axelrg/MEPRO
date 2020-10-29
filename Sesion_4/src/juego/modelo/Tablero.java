package juego.modelo;

import juego.util.Direccion;

import java.util.Arrays;

/**
 * (El codigo completamente comentado es el de pieza).
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class Tablero {
    Celda [][] tablero;

    public Tablero(int filas, int columnas){

        if (filas<0)
            filas=-filas;

        if (columnas<0)
            columnas=-columnas;

        if (columnas==0)
            columnas=1;

        if (filas==0)
            filas=1;


        this.tablero= new Celda[filas][columnas];

        for (int fila = 0 ; fila < obtenerNumeroFilas(); fila++){
            for (int columna = 0 ; columna < obtenerNumeroColumnas(); columna++){
                tablero[fila][columna] = new Celda(fila, columna);

            }
        }
    }

    public void colocar(Pieza pieza, Celda celda){
        pieza.establecerCelda(celda);
        celda.establecerPieza(pieza);
    }

    public Celda obtenerCelda(int fila, int columna){
        if (estaEnTablero(fila, columna))
            return tablero[fila][columna];

        return null;
    }

    public boolean estaEnTablero(int fila, int columna){
        if (fila<0 | columna<0)
            return false;

        if (fila < obtenerNumeroFilas() & columna< obtenerNumeroColumnas())
            return true;

        return false;
    }

    private int piezas(Celda celda, int d1, int d2){
        int piezas=0;
        int i = 0;
        for (int j=0;j<2;j++){

            if (j > 0) {
                d2=-d2;
                d1=-d1;
                i = 1;
            }
            
            while (estaEnTablero(celda.obtenerFila()+i*d1, celda.obtenerColumna() + i*d2)) {
                if (celda.obtenerPieza() != null && tablero[celda.obtenerFila()+i*d1][celda.obtenerColumna() + i*d2].obtenerPieza() != null && celda.obtenerPieza().obtenerColor() == tablero[celda.obtenerFila()+i*d1][celda.obtenerColumna() + i*d2].obtenerPieza().obtenerColor()) {
                    piezas++;
                    i++;
                } else
                    break;
            }
        }




        while (estaEnTablero(celda.obtenerFila()+i*d1, celda.obtenerColumna() + i*d2)) {
            if (celda.obtenerPieza() != null && tablero[celda.obtenerFila()+i*d1][celda.obtenerColumna() + i*d2].obtenerPieza() != null && celda.obtenerPieza().obtenerColor() == tablero[celda.obtenerFila()+i*d1][celda.obtenerColumna() + i*d2].obtenerPieza().obtenerColor()) {
                piezas++;
                i++;
            } else
                break;
        }
        return piezas;
    }

    public int contarPiezas(Celda celda, Direccion direccion){
        int piezas = 0;
        switch (direccion) {


            case HORIZONTAL:
                piezas=piezas(celda,0,1);
                break;

            case VERTICAL:
                piezas=piezas(celda,1,0);
                break;

            case DIAGONAL_NO_SE:
                piezas=piezas(celda,1,1);
                break;

            case DIAGONAL_SO_NE:
                piezas=piezas(celda,1,-1);
                break;
        }


        return piezas;
    }

    public int obtenerNumeroPiezas(Color color){
        int piezas =0;
        for (int i=0; i< obtenerNumeroFilas(); i++) {
            for (int j = 0; j < obtenerNumeroColumnas(); j++) {
                if (tablero[i][j].obtenerPieza() != null && tablero[i][j].obtenerPieza().obtenerColor() == color) {
                    piezas++;
                }
            }
        }
        return piezas;
    }
    public int obtenerNumeroFilas(){
        return tablero.length;
    }

    public int obtenerNumeroColumnas(){
        return tablero[0].length;
    }

    public boolean estaCompleto(){
        for (int i=0; i< obtenerNumeroFilas(); i++) {
            for (int j = 0; j < obtenerNumeroColumnas(); j++) {
                if (tablero[i][j].estaVacia()) {
                    return false;
                }
            }
        }
        return true;
    }



    public String toString() {
        String string = "";
        for (int fila = 0 ; fila < obtenerNumeroFilas(); fila++){
            if (fila != 0)
                string = string + '\n';
            for (int columna = 0 ; columna < obtenerNumeroColumnas(); columna++){
                if (!tablero[fila][columna].estaVacia())
                    string = string + tablero[fila][columna].obtenerPieza().obtenerColor().toChar();
                else
                    string = string + '-';
            }
        }

        return string;
    }

}

