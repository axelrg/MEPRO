/**
 * 
 * @author Axel Rubio Gonzalez
 * 
 */
public class Palindromo{
	
	public static void main(String[] args) {
		char[] texto= "ana".toCharArray();
		System.out.println(texto);
		if(esPalindromo(texto)){
			System.out.println("Es Palindromo");
		}
		else
			System.out.println("No es Palindromo");
			
	}
	
	static boolean esPalindromo(char[] texto) {
		for(int i = 0; i <= texto.length-1; i++)
			if (texto[i] != texto[texto.length-1-i])
				return false;

		return true;
	}
}