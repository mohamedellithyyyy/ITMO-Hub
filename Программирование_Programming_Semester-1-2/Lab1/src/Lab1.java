import java.util.Random;
import java.util.Arrays;
import java.lang.Math;

public class Lab1 {
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
    final static int DECIMAL = 3;
    public static void main(String[] args) {
        var array1 = makeArray1(arr1_starting, arr1_ending);
        System.out.println("First array:\n" + Arrays.toString(array1) + "\n");
        var array2 = makeArray2(arr2_size, arr2_starting, arr2_ending);
        System.out.println("Second array:\n" + Arrays.toString(array2) + "\n");
        long[] p = Arrays.stream(array1).asLongStream().toArray();
        var array3 = makeArray3(array3_col, array3_row, p, array2);
        System.out.println("Third matrix:");
        printMatrix(array3, DECIMAL);
    }
    // Create D1 Array1
    public static int[] makeArray1(int start, int end) {
        var size = (end - start) / 2 + 1;
        var result = new int[size];
        var temp = start;
        for (int i = 0; i < size; i++) {
            result[i] = temp;
            temp += 2;
        }
        return result;
    }
    // Create D1 Array2 || a random 1D array
    private static double[] makeArray2(int size, double start, double end) {
        var result = new double[size];
        var rand = new Random();
        for (int i = 0; i < size; i++) {
            result[i] = rand.nextDouble() * (end - start) + start;
        }
        return result;
    }
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
        return Math.atan(Math.sin(Math.pow(3 - Math.exp(x), 2)));
    }
    if (p == 10 || p == 12 || p == 14 || p == 20 || p == 22) {
        return Math.log(Math.pow(Math.PI * (Math.sqrt(Math.abs(x)) + 1),
            Math.sin(Math.log(Math.abs(x)))));
    }
    return Math.log(Math.pow(Math.cos(Math.pow(Math.E, Math.cos(x))),
         2 * Math.cos(Math.sin(x))));
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
