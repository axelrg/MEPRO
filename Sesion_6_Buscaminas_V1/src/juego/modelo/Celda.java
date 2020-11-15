package juego.modelo;

import java.util.Objects;

public class Celda {
    public static final String TEXTO_MINA = " M ";
    private int fila;
    private int columna;
    private Estado estado;
    private boolean tieneMina;
    private int numeroMinas;


    public Celda(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.estado = Estado.OCULTA;
        tieneMina = false;
    }


    public Celda clonar() {
        Celda celda = new Celda(this.fila, this.columna);
        celda.estado=estado;
        celda.tieneMina = tieneMina();
        celda.numeroMinas = obtenerNumeroMinasAdyacentes();
        return celda;
    }

    public void colocarMina() {
        tieneMina = true;
    }

    public void establecerNumeroMinasAdyacentes(int numeroMinas) {
        this.numeroMinas = numeroMinas;
    }

    public void establecerSiguienteEstadoDescubrir() {
        if (estaOculta()) {
            estado = Estado.DESCUBIERTA;
        }
    }

    public void establecerSiguienteEstadoMarcar() {
        if (estaOculta()) {
            estado = Estado.MARCADA;
        } else if (estaMarcada()) {
            estado = Estado.OCULTA;
        }
    }


    public boolean estaDescubierta() {
        if (estado == Estado.DESCUBIERTA) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estaMarcada() {
        if (estado == Estado.MARCADA) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estaOculta() {
        if (estado == Estado.OCULTA) {
            return true;
        } else {
            return false;
        }
    }

    public int obtenerColumna() {
        return columna;
    }

    public int obtenerFila() {
        return fila;
    }

    public int obtenerNumeroMinasAdyacentes() {
        return numeroMinas;
    }

    public String obtenerTextoEstado() {
        if (estado == Estado.DESCUBIERTA) {
            if (tieneMina()) {
                return TEXTO_MINA;
            } else {
                if (obtenerNumeroMinasAdyacentes() != 0) {
                    return " " + obtenerNumeroMinasAdyacentes() + " ";
                } else {
                    return " . ";
                }
            }
        } else {
            return " " + estado.obtenerLetra() + " ";
        }

    }

    public String obtenerTextoSolucion() {
        if (tieneMina()) {
            return TEXTO_MINA;
        } else {
            if (obtenerNumeroMinasAdyacentes() != 0) {
                return " " + obtenerNumeroMinasAdyacentes() + " ";
            } else {
                return " - ";
            }
        }

    }

    public boolean tieneCoordenadasIguales(Celda celda) {
        if (celda.obtenerFila() == obtenerFila() && celda.obtenerColumna() == obtenerColumna()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Celda celda = (Celda) o;
        return fila == celda.fila &&
                columna == celda.columna &&
                tieneMina == celda.tieneMina &&
                estado == celda.estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna, estado, tieneMina);
    }

    public boolean tieneMina() {
        return tieneMina;
    }

    public String toString() {
        return "[(" + fila + "," + columna + ")-" + obtenerNumeroMinasAdyacentes() + "-" + estado + "]";
    }
}
