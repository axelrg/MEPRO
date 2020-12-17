package excepcion;

import java.util.Scanner;

/**
 * Principal.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author Axel Rubio Gonzalez
 * @version 1.0
 */
public class Principal {

    /**
     * Método raíz.
     *
     * @param args argumentos
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // COMPLETAR SEGÚN INDICACIONES DEL ENUNCIADO
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            String s = scanner.next();
            Conversor conversor = new Conversor();
            System.out.println(conversor.aMinusculas(s));
            System.out.println(conversor.aMayusculas(s));
            scanner.close();
        }
		/*
		catch (CException ex){
			System.err.println("Error de tipo CException");
			System.err.println(ex.toString());
		}
		catch (BException ex){
			System.err.println("Error de tipo BException");
			System.err.println(ex.toString());
		}
		*/ catch (Exception ex) {
            throw new Exception("Error en tiempo de ejecucion");
        } finally {
            if (scanner != null) {//Si no ha abierto el recurso seguira a null
                scanner.close();
            }
        }

    }
}