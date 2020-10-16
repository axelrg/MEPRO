/**
 * 
 * @author Axel Rubio Gonzalez
 * 
 */

public class Tabla {
	
	public static void main(String[] args) {
		int[][] tabla = new int[3][3]; // declaración y reserva
		inicializar(tabla); // inicializa el array
		mostrar(tabla); // muestra en pantalla el estado actual
		elevarAlCuadrado(tabla); // eleva al cuadrado cada posición
		mostrar(tabla); // muestra en pantalla el estado actual
		}
	
	static void inicializar(int[][] tabla) {
		int numero = 1;
		for (int i=0; i<tabla.length; i++)
			for (int j=0; j<tabla[i].length; j++) {
				tabla[i][j] = numero++;
			}
	}
	
	static void mostrar(int[][] tabla) {
		for (int i=0; i<tabla.length; i++) {
			System.out.println();
			for (int j=0; j<tabla[i].length; j++)
				System.out.printf(" "+tabla[i][j]);		
		}	
		System.out.println();
	}
	
	static void elevarAlCuadrado(int[][] tabla) {
		for (int i=0; i<tabla.length; i++) 
			for (int j=0; j<tabla[i].length; j++)
				tabla[i][j]=tabla[i][j]*tabla[i][j];
		
	}

}