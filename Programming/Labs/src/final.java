import java.util.Random;
import java.util.Arrays;
import java.lang.Math;

public class MyFinal {
    // 1D Array №1
    final static short wStarting = 4;
    final static short wEnding = 22;
    final static int W_SIZE = ((wEnding - wStarting) / 2) + 1; // 10

    // 1D Array №2
    final static float xStarting = -15;
    final static float xEnding = 15;
    final static int X_SIZE = 18;

    // 2D Array №3 => Matrix
    final static int array3_col = 10;
    final static int array3_row = 18;
    final static int DECIMAL = 2;

    public static void main(String[] args) {
        // Print first Array (w)
        short[] w = createWArray(wStarting, wEnding);
        System.out.println("First array:\n" + Arrays.toString(w));
        System.out.println("#".repeat(40) + "\n" + "✅✅✅ The First array is done\n\n");
        // Print second Array (x)
        float[] x = createXArray(X_SIZE, xStarting, xEnding);
        System.out.println("Second array:\n" + Arrays.toString(x));
        System.out.println("#".repeat(40) + "\n" + "✅✅✅ The 2nd array is done\n\n");
        // Print Matrix
        var matrix = create2DArray(array3_col, array3_row, w, x);
        System.out.println("Resulting 2D matrix:");
        printMatrix(matrix, DECIMAL);
    }
// to create w array 
    private static short[] createWArray(short start, short end) {
        var result = new short[W_SIZE];
        var temp = start;
        for (int i = 0; i < W_SIZE; i++) {
            result[i] = temp;
            temp += 2;
        }
        return result;
    }
// to create x array 
    private static float[] createXArray(int size, float start, float end) {
        var result = new float[size];
        var rand = new Random();
        for (int i = 0; i < size; i++) {
            result[i] = (float) (rand.nextDouble() * (end - start) + start);
        }
        return result;
    }
//  
    private static double[][] create2DArray(int columnsSize, int rowsSize, short[] p, float[] x) {
        var result = new double[columnsSize][rowsSize];
        for (int i = 0; i < columnsSize; i++) {
            for (int j = 0; j < rowsSize; j++) {
                result[i][j] = calculateElement(p[i], x[j]);
            }
        }
        return result;
    }
// to create calculateElement
    private static double calculateElement(short p, float x) {
    if (p == 18) {
        float value = (float)Math.pow(Math.atan(Math.pow(x / (3 * Math.E) + 1, 2)),  2 - Math.pow(x, (x + 3) / 4.0) / (Math.cos(Math.tan(x)) - 1) );
        return  value;
    }
    if (p == 4 || p == 12 || p == 14 || p == 20 || p == 22) {
        return Math.log(Math.pow(Math.PI * (Math.sqrt(Math.abs(x)) + 1),Math.sin(Math.log(Math.abs(x)))));
    }
    return Math.log(Math.pow(Math.cos(Math.pow(Math.E, Math.cos(x))),2 * Math.cos(Math.sin(x))));
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
