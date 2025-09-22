import java.util.Random;
import java.util.Arrays;

public class FinalTask {
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
    
}
