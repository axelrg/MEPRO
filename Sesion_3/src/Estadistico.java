public class Estadistico {

    public static void main(String[] args) {
        double[] numeros = { 1.0, 1.0, 2.0, 3.0 };
        System.out.printf("El valor máximo es %.2f%n", obtenerMaximo(numeros));
        System.out.printf("El valor mínimo es %.2f%n", obtenerMinimo(numeros));
        System.out.printf("La media arimética es %.2f%n", obtenerMedia(numeros));
    }

    static double obtenerMaximo(double[]numeros){
        double maximo = Double.MIN_VALUE;
        for (int i=0; i<= numeros.length-1;i++)
            if (maximo < numeros[i])
                maximo=numeros[i];

        return maximo;
    }

    static double obtenerMinimo(double[] numeros){
        double minimo = Double.MAX_VALUE;
        for (int i=0; i<= numeros.length-1;i++)
            if (minimo > numeros[i])
                minimo=numeros[i];

        return minimo;
    }

    static double obtenerMedia(double[] numeros){
        double media = 0;
        for (int i=0; i<= numeros.length-1;i++)
            media=media+numeros[i];

        if (numeros.length != 0)
            media=media/numeros.length;

        return media;
    }
}
