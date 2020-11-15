package juego.modelo;


import java.util.Arrays;
import java.util.Random;

import juego.control.DistanciaChebyshev;

public class Tablero {
    public final int NUMERO_FILAS = 8;
    public final int NUMERO_COLUMNAS = 8;
    Celda[][] tablero;

    public Tablero() {
        tablero = new Celda[NUMERO_FILAS][NUMERO_COLUMNAS];
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                tablero[i][j] = new Celda(i, j);
            }
        }
    }
/*
  Ejemplo for each

  Estado[] valores = Estado.values()

  for(Estado valor : valores){
      sout("%c%n", valor.obtenerLetra());
  }
 */

    private boolean estaEnTablero(int fila, int columna) {
        if (fila < 0 || fila >= NUMERO_FILAS || columna < 0 || columna >= NUMERO_COLUMNAS) {
            return false;
        } else {
            return true;
        }
    }

    public Tablero clonar() {
        Tablero tablero1= new Tablero();
        for (int i=0; i<NUMERO_FILAS; i++ ){
            for (int j =0 ; j<NUMERO_COLUMNAS; j++){
                tablero1.tablero[i][j]=this.clonarCelda(i,j);
            }
        }
        return tablero1;
    }

    public Celda clonarCelda(int fila, int columna) {
        if (estaEnTablero(fila, columna)) {
            Celda celda = tablero[fila][columna].clonar();
            return celda;
        } else {
            return null;
        }
    }

    public Celda[] clonarCeldas() {
        Celda[] array= new Celda[NUMERO_COLUMNAS*NUMERO_FILAS];
        for (int i=0; i<NUMERO_FILAS;i++){
            for (int j=0; j<NUMERO_COLUMNAS; j++){
                array[i*NUMERO_FILAS +j]=tablero[i][j];
            }
        }

        return array;
    }

    public void colocarMinas(Celda inicio, DistanciaChebyshev distancia, Random rd) {

    }

    public int contarBanderas() {
        int total = 0;
        for (int i = 0; i < obtenerNumeroFilas(); i++) {
            for (int j = 0; j < obtenerNumeroColumnas(); j++) {
                if (tablero[i][j].estaMarcada()) {
                    total++;
                }
            }
        }
        return total;
    }

    public int contarCeldasDescubiertas() {
        int total = 0;
        for (int i = 0; i < obtenerNumeroFilas(); i++) {
            for (int j = 0; j < obtenerNumeroColumnas(); j++) {
                if (tablero[i][j].estaDescubierta()) {
                    total++;
                }
            }
        }
        return total;
    }

    public int contarMinas() {
        int total = 0;
        for (int i = 0; i < obtenerNumeroFilas(); i++) {
            for (int j = 0; j < obtenerNumeroColumnas(); j++) {
                if (tablero[i][j].tieneMina()) {
                    total++;
                }
            }
        }
        return total;
    }

    public int contarMinasExplotadas() {
        int total = 0;
        for (int i = 0; i < NUMERO_FILAS; i++) {
            for (int j = 0; j < NUMERO_COLUMNAS; j++) {
                if (tablero[i][j].estaDescubierta() && tablero[i][j].tieneMina()) {
                    total++;
                }
            }
        }
        return total;
    }

    public void descubrir(int fila, int columna) {

    }

    public void marcarDesmarcar(int fila, int columna) {
        tablero[fila][columna].establecerSiguienteEstadoMarcar();
    }

    public Celda obtenerCelda(int fila, int columna) {
        if (estaEnTablero(fila, columna)) {
            return tablero[fila][columna];
        } else {
            return null;
        }


    }

    public int obtenerNumeroColumnas() {
        return tablero[0].length;
    }

    public int obtenerNumeroFilas() {
        return tablero.length;
    }

    public String obtenerSolucion() {
        String texto="";
        for (int i=0; i<NUMERO_FILAS; i++){
            texto=texto+ '\n'+i;
            for (int j=0; j<NUMERO_COLUMNAS; j++){
                texto=texto + tablero[i][j].obtenerTextoSolucion();
            }
        }
        texto = texto +"01234567";
        return texto;
    };


    @Override
    public String toString() {
        String texto="";
        for (int i=0; i<NUMERO_FILAS; i++){
            texto=texto+ '\n'+i;
            for (int j=0; j<NUMERO_COLUMNAS; j++){
                texto=texto + tablero[i][j].obtenerTextoEstado();
            }
        }
        texto = texto +"01234567";
        return texto;
    }
}
