package musica;
/**
 * @author Axel Rubio González
 *
 * */
public class Cuerda extends Instrumento {
    public Cuerda(float precio) {
        super(precio);
    }

    @Override
    public void tocar(Nota nota) {
        System.out.printf("Cuerda.tocar(): %s%n", nota.toString());
    }

    @Override
    public String toString() {
        return "[" + this.consultarPrecio() +  ":Cuerda]";
    }
}
