public class ArrayTriangular {

    public static void main(String[] args) {
        int [][] triangular = { { 1, 2, 3} , { 4, 5 }, { 6 } };
        /*
        * 1, 2, 3
        * 4, 5
        * 6
        * */

        for (int i=0;i<triangular.length;i++){
            for (int j=0;j<triangular[i].length;j++)
                System.out.print(triangular[i][j]);

            System.out.println();
        }



    }
}
