import java.util.Random;
import java.util.Arrays;
import java.lang.Math;
public class Lab1{
    // Starting and ending for 1D Array№1
    static int arr1_starting = 4;
    static int arr1_ending = 22;
    // Starting, ending and size for 1D Array№2
    static double arr2_starting = -15.0;
    static double arr2_ending = 15.0;
    static int arr2_size = 18;
    // Size for 2D Array№2
    int array3_col = 10;
    int array3_row = 18; 

    public static void main(String[] args) {
        var array1 =  makeArray1(arr1_starting, arr1_ending);
        System.out.println("First array:\n" + Arrays.toString(array1) + "\n\n");
        var array2 =  makeArray2(arr2_size,arr2_starting, arr2_ending);
        System.out.println("Second array:\n" + Arrays.toString(array2) + "\n\n");
    }
// Create Arrays
    // Create D1 Array1
    public static int[] makeArray1(int start, int end){
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
    // // Create D2 Array3
    // private static arr3(){

    // }
// Calculate Value
    private static double calculateValue(long p, double x) {
        if (p == 12) {
            return Math.pow(
                    Math.pow((0.5 * Math.pow(2 * x, 2)), 3),
                    Math.sin(Math.pow(x, ((2.0 / 3.0) * (3.0 - x))))
            );
        }
        if (p == 4 || p == 8 || p == 10) {
            return Math.log(Math.pow(Math.E, Math.pow(Math.E, Math.pow(x, x))));
        }
        return Math.pow(
                Math.pow(Math.tan(Math.tan(x)) * (Math.pow(((1.0 / 4.0) / Math.tan(x)), 3) - 1.0), 3),
                2
        );
    }


    
}

