package genericidad;

import musica.*;
/**
 * @author Axel Rubio González
 */
public class Principal {
    public static void main(String[] args){
        Lista<Nota> notasMusicales = new Lista<Nota>(10);
        rellenarNotasMusicales(notasMusicales);
        mostrarNotasMusicales(notasMusicales);
        mostrar(notasMusicales);

        Lista<Musical> instrumentos = new Lista<Musical>(10);
        rellenarInstrumentosMusicales(instrumentos);
        mostrarInstrumentosMusicales(instrumentos);
        mostrar(instrumentos);
    }

    private static void mostrar(Lista<?> lista) {
        for (int i=0; i<lista.size();i++){
            System.out.println(lista.get(i));
        }
    }

    private static void rellenarInstrumentosMusicales(Lista<Musical> instrumentos) {
        for (int i=0; i< instrumentos.size(); i++){
            int aleatorio = (int) (Math.random() *3);
            Musical musical;
            switch (aleatorio){
                case 0: musical =new Viento(10.F);
                        break;
                case 1: musical =new Cuerda(10.F);
                    break;
                case 2: musical =new Metal(10.F);
                    break;
                default:    musical =new Metal(10.F);
            }

            instrumentos.set(musical,i);
        }
    }

    private  static void  mostrarInstrumentosMusicales(Lista<Musical> instrumentos){
        for (int i=0;i< instrumentos.size();i++){
            Musical musical = instrumentos.get(i);
            System.out.println(instrumentos.get(i).toString());
        }
    }

    private static  void  rellenarNotasMusicales(Lista<Nota> lista){
        for (int i=0; i< lista.size();i++){
            int aleatoria=(int) (Math.random()*Nota.values().length);
            Nota notaAleatoria= Nota.values()[aleatoria];
            lista.set(notaAleatoria,i );
        }

    }

    private  static void  mostrarNotasMusicales(Lista<Nota> lista){
        for (int i=0;i< lista.size();i++){
            System.out.println(lista.get(i).toString());
        }
    }
}
