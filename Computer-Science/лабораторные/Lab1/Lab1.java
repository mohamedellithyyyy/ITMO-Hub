import java.util.ArrayList;
import java.util.Scanner;
public class lab1 {
    public static int factorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    public static String decimalToFactorial(int number) {
        ArrayList<Integer> digits = new ArrayList<>();
        int n = 1;
        while (factorial(n) <= number) {
            n++;
        }
        n--;
        int remaining = number;
        // Compute digits
        for (int i = n; i >= 1; i--) {
            int f = factorial(i);
            int digit = remaining / f;
            remaining = remaining % f;
            digits.add(digit);
        }
        // Convert to string
        StringBuilder sb = new StringBuilder();
        for (int d : digits) {
            sb.append(d);
        }
        return sb.toString();
    }
    public static int readNumber() {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("Введите десятичное число: ");
            if (sc.hasNextInt()) {
                number = sc.nextInt();
                valid = true; // valid integer
            } else {
                System.out.println("Некорректный ввод! Пожалуйста, введите целое число.");
                sc.next(); // discard invalid input
            }
        }
        return number;
    }
    public static void main(String[] args) {
        int number = readNumber();
        String factorialBase = decimalToFactorial(number);
        System.out.println("Число в факториальной системе: " + factorialBase);
    }
}
