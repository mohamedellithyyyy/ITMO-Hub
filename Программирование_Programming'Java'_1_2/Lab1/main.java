import java.util.Random;
import java.util.Arrays;
import java.lang.Math;
public class Lab1{
    // Starting and ending for 1D Array№1
    static int arr1_starting = 4;
    static int arr1_ending = 22;
    // Starting, ending and size for 1D Array№2
    double arr2_starting = -15.0;
    double arr2_ending = 15.0;
    int arr2_size = 18;
    // Size for 2D Array№2
    int array3_col = 10;
    int array3_row = 18; 

    public static void main(String[] args) {
        var array1 =  makeArray1(arr1_starting, arr1_ending);
        System.out.println("First array:\n" + Arrays.toString(array1) + "\n\n");
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
    // Create D1 Array2
    // private static arr2(){

    // }
    // // Create D2 Array3
    // private static arr3(){

    // }


    
}

