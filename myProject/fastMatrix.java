import java.util.LinkedList;

public class fastMatrix {

    double[][] matrix;

    fastMatrix(double[][] matrix){
        this.matrix = matrix;
    }
    public double[][] power(double power){
        double[][] matrix2 = matrix.clone();
        LinkedList<Integer> m = new LinkedList<>();
        while(power!=1){
            if(power%2==0){
                m.add(2);
                power/=2;
            }else {
                m.add(1);
                power-=1;
            }
        }
        for(int i = m.size()-1; i>=0; i--){
            if(m.get(i)==2){
                pow(matrix, matrix);
            }else {
                pow(matrix, matrix2);
            }
        }
        return matrix;
    }

    public void pow(double[][] matrix1, double[][] matrix2) {
        double[][]  ret=new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i<matrix1.length; i++) {
            for (int l = 0; l<matrix2[0].length; l++) {
                for (int j = 0; j<matrix1[0].length; j++) {
                    ret[i][l]+=matrix1[i][j]*matrix2[j][l];
                }
            }
        }
        matrix = ret;
    }

    public void print(){
        for(int i = 0; i< matrix[0].length; i++) {
            for (int t = 0; t < matrix.length; t++) {
                System.out.print(matrix[t][i]+" ");
            }
            System.out.println();
        }
    }
}
