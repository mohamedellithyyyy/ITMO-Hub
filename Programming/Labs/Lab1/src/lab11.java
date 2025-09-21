import java.util.Random;
import java.util.Arrays;
import java.lang.Math;

public class lab11 {
    // Starting and ending for 1D Array№1
    final static int arr1_starting = 4;
    final static int arr1_ending = 22;
    // Starting, ending and size for 1D Array№2
    final static double arr2_starting = -15.0;
    final static double arr2_ending = 15.0;
    final static int arr2_size = 18;
    // Size for 2D Array№3
    final static int array3_col = 10;
    final static int array3_row = 18;
    final static int DECIMAL = 2;
    public static void main(String[] args){
        // Print the 1st array 
        var array1 = makeArray1(arr1_starting, arr1_ending);
        System.out.println("First array:\n" + "#".repeat(40)+"\n"+ Arrays.toString(array1));
        System.out.println("#".repeat(40)+"\n"+"✅✅✅The First array had is done"+"\n".repeat(2));
        // Print the 2nd array 
        var array2 = makeArray2(arr2_size, arr2_starting, arr2_ending);
        System.out.println("Second array:\n" + Arrays.toString(array2));
        System.out.println("#".repeat(40)+"\n"+"✅✅✅The 2nd array had is done"+"\n".repeat(2));
        // Print Matrix
        long[] p = Arrays.stream(array1).asLongStream().toArray();
        var array3 = makeArray3(array3_col, array3_row, p, array2);
        System.out.println("Third matrix:");
        printMatrix(array3, DECIMAL);
    }
    // ###################Funcation to create D1 Array1#####################
    private static int[] makeArray1(int start, int end) {
        var size = (end - start) / 2 + 1;
        var result = new int[size];
        var temp = start;
        for (int i = 0; i < size; i++) {
            result[i] = temp;
            temp += 2;
        }
        return result;
    }
    // ###################Funcation to Create D1 Array2 || a random 1D array#####################
    private static double[] makeArray2(int size, double start, double end) {
        var result = new double[size];
        var rand = new Random();
        for (int i = 0; i < size; i++) {
            result[i] = rand.nextDouble() * (end - start) + start;
        }
        
        return result;
    }
    // ###################Funcation to create D1 Array1#####################
    // Create D2 Array3
    private static double[][] makeArray3(int columnsSize, int rowsSize, long[] p, double[] x) {
        var result = new double[columnsSize][rowsSize];
        for (int i = 0; i < columnsSize; i++) {
            for (int j = 0; j < rowsSize; j++) {
                result[i][j] = calculateValue(p[i], x[j]);
            }
        }
        return result;
    }
    // Calculate Value
private static double calculateValue(long p, double x) {
    if (p == 18) {
        double value = Math.pow(Math.atan(Math.pow(((x/3)*Math.E)+1, 2)), 2 - ((Math.pow(x, (x+3)/4))/(Math.cos(Math.tan(x))-1)));
        return value;
    }
    else if (p == 4 || p == 12 || p == 14 || p == 20 || p == 22) {
        return Math.cbrt(Math.log(Math.acos((x / 3.0) * Math.E + 1)));
    }
    else return Math.sin(Math.cbrt(Math.tan(Math.pow(x, (x + 1) / 12.0))));
}
    // Print matrix with given decimal places
private static void printMatrix(double[][] matrix, int decimalPlaces) {
        String format = "%." + decimalPlaces + "f ";
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.printf(format, value);
            }
            System.out.println();
        }
    }

}