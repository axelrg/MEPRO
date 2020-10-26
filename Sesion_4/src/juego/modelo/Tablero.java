package juego.modelo;

import juego.util.Direccion;

import java.util.Arrays;

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

    public int contarPiezas(Celda celda, Direccion direccion){
        int piezas = 0;
        switch (direccion) {


            case HORIZONTAL:
                /**
                 *   0  1  2
                 * 0 -  -  -            N
                 * 1 -  -  -        O       E
                 * 2 -  -  -            S
                 */
                int i = 0;
                while (estaEnTablero(celda.obtenerFila(), celda.obtenerColumna() + i)) {
                    if (celda.obtenerPieza() != null && tablero[celda.obtenerFila()][celda.obtenerColumna() + i].obtenerPieza() != null && celda.obtenerPieza().obtenerColor() == tablero[celda.obtenerFila()][celda.obtenerColumna() + i].obtenerPieza().obtenerColor()) {
                        piezas++;
                        i++;
                    } else
                        break;

                }
                i = 1;

                while (estaEnTablero(celda.obtenerFila(), celda.obtenerColumna() - i)) {
                    if (celda.obtenerPieza() != null && tablero[celda.obtenerFila()][celda.obtenerColumna() - i].obtenerPieza() != null && celda.obtenerPieza().obtenerColor() == tablero[celda.obtenerFila()][celda.obtenerColumna() - i].obtenerPieza().obtenerColor()){
                        piezas++;
                    i++;
                    }else
                        break;
                }


                break;

            case VERTICAL:
                /**
                 *   0  1  2
                 * 0 -  -  -            N
                 * 1 -  -  -        O       E
                 * 2 -  -  -            S
                 */
                int i_v = 0;
                while (estaEnTablero(celda.obtenerFila()+ i_v, celda.obtenerColumna() )) {
                    if (celda.obtenerPieza() != null && tablero[celda.obtenerFila()+ i_v][celda.obtenerColumna() ].obtenerPieza() != null && celda.obtenerPieza().obtenerColor() == tablero[celda.obtenerFila()+ i_v][celda.obtenerColumna() ].obtenerPieza().obtenerColor()) {
                        piezas++;
                        i_v++;
                    } else
                        break;

                }
                i_v = 1;

                while (estaEnTablero(celda.obtenerFila() - i_v, celda.obtenerColumna())) {
                    if (celda.obtenerPieza() != null && tablero[celda.obtenerFila()- i_v][celda.obtenerColumna() ].obtenerPieza() != null && celda.obtenerPieza().obtenerColor() == tablero[celda.obtenerFila()- i_v][celda.obtenerColumna() ].obtenerPieza().obtenerColor()){
                        piezas++;
                        i_v++;
                    }else
                        break;
                }

                break;

            case DIAGONAL_NO_SE:
                int i_no_se = 0;
                while (estaEnTablero(celda.obtenerFila()+ i_no_se, celda.obtenerColumna()+i_no_se )) {
                    if (celda.obtenerPieza() != null && tablero[celda.obtenerFila()+ i_no_se][celda.obtenerColumna()+ i_no_se].obtenerPieza() != null && celda.obtenerPieza().obtenerColor() == tablero[celda.obtenerFila()+ i_no_se][celda.obtenerColumna()+ i_no_se ].obtenerPieza().obtenerColor()) {
                        piezas++;
                        i_no_se++;
                    } else
                        break;

                }
                i_no_se = 1;

                while (estaEnTablero(celda.obtenerFila() - i_no_se, celda.obtenerColumna()- i_no_se)) {
                    if (celda.obtenerPieza() != null && tablero[celda.obtenerFila()- i_no_se][celda.obtenerColumna()- i_no_se ].obtenerPieza() != null && celda.obtenerPieza().obtenerColor() == tablero[celda.obtenerFila()- i_no_se][celda.obtenerColumna()- i_no_se ].obtenerPieza().obtenerColor()){
                        piezas++;
                        i_no_se++;
                    }else
                        break;
                }
                break;

            case DIAGONAL_SO_NE:
                int i_so_ne = 0;
                while (estaEnTablero(celda.obtenerFila()+ i_so_ne, celda.obtenerColumna()-i_so_ne )) {
                    if (celda.obtenerPieza() != null && tablero[celda.obtenerFila()+ i_so_ne][celda.obtenerColumna()- i_so_ne].obtenerPieza() != null && celda.obtenerPieza().obtenerColor() == tablero[celda.obtenerFila()+ i_so_ne][celda.obtenerColumna()- i_so_ne ].obtenerPieza().obtenerColor()) {
                        piezas++;
                        i_so_ne++;
                    } else
                        break;

                }
                i_so_ne = 1;

                while (estaEnTablero(celda.obtenerFila() - i_so_ne, celda.obtenerColumna()+ i_so_ne)) {
                    if (celda.obtenerPieza() != null && tablero[celda.obtenerFila() - i_so_ne][celda.obtenerColumna() + i_so_ne].obtenerPieza() != null && celda.obtenerPieza().obtenerColor() == tablero[celda.obtenerFila() - i_so_ne][celda.obtenerColumna() + i_so_ne].obtenerPieza().obtenerColor()) {
                        piezas++;
                        i_so_ne++;
                    } else
                        break;
                }
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

