package genericidad;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Axel Rubio González
 */
public class Lista<E>{

    private List<E> arrayImplementacion = new ArrayList();

    public Lista(int numero) {
        arrayImplementacion = new ArrayList();
        for (int i = 0; i < numero; i++) {
            arrayImplementacion.add(null);
        }
    }

    public void set(E elemento, int posicion) {
        if (posicion >= 0 && posicion < size()) {
            arrayImplementacion.set(posicion, elemento);
        }
    }

    public E get(int posicion) {
        if (posicion >= 0 && posicion < size()) {
            return arrayImplementacion.get(posicion);
        } else {
            return null;
        }
    }

    public int size(){
        return arrayImplementacion.size();
    }


}
