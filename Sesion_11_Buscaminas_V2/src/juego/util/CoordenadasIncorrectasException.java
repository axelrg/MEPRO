package juego.util;

@SuppressWarnings("serial")
public class CoordenadasIncorrectasException extends Exception{
   private final long serialVersionUID = 1L;

   public CoordenadasIncorrectasException(){
   }

    public CoordenadasIncorrectasException(String message){
        super(message);
    }

    public CoordenadasIncorrectasException(Throwable cause){
        super(cause);
    }

    public CoordenadasIncorrectasException(String message, Throwable cause){
        super(message, cause);
    }
}
