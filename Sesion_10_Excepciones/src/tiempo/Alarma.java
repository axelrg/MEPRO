package tiempo;

/**
 * Ejercicio de lanzamiento de excepciones.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:cpardo@ubu.es">Carlos Pardo</a>
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class Alarma {

    /**
     * Hora.
     */
    private int hora;

    /**
     * Minuto.
     */
    private int minuto;

    /**
     * Inicializa la hora y minuto de una alarma.
     *
     * @param hora   hora
     * @param minuto minuto
     * @throws Exception si se intenta establecer una alarma con valores incorrectos
     */
    public Alarma(int hora, int minuto) throws Exception {
        validar(hora, minuto);
        this.hora = hora;
        this.minuto = minuto;
    }

    private void validar(int hora, int minuto) throws Exception {
        if (hora < 0 || hora > 23) {
            if (minuto < 0 || minuto > 59) {
                throw new Exception("Hora y minuto incorrectos: " + hora + ":" + minuto);
                //Fallan las dos
            } else {
                throw new Exception("Hora incorrecta: " + hora);
            }
        } else if (minuto < 0 || minuto > 59) {
            throw new Exception("Minuto incorrecto: " + minuto);
            //fallan los minutos
        }
    }

    /**
     * Consulta la hora.
     *
     * @return hora
     */
    public int consultarHora() {
        return hora;
    }

    /**
     * Consulta el minuto.
     *
     * @return alarma
     */
    public int consultarMinuto() {
        return minuto;
    }
}
