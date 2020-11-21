package musica;

/**
 * Musica.
 *
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @version 1.0
 */
public class Musica {
	/*
	private static void afinar(Viento v){
		v.tocar(Nota.DO);
	}
	private static void afinar(Cuerda c){
		c.tocar(Nota.DO);
	}
	private static void afinar(Metal m){
		m.tocar(Nota.DO);
	}
	*/
	private static void afinar(Musical i) {
		// ...
		i.tocar(Nota.DO);
	}

	public static void main(String args[]){
		Viento viento = new Viento(100.0F);
		Cuerda cuerda = new Cuerda(200.0F);
		Metal metal = new Metal(300.0F);
		afinar(viento);
		afinar(cuerda);
		afinar(metal);
	}

}