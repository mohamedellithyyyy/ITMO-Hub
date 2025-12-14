import java.util.ArrayList;
import java.util.Scanner;

public class lab1 {
    public static ArrayList<Integer> generateFibonacciUpTo(int number) {
        ArrayList<Integer> fibs = new ArrayList<>();
        fibs.add(1); // F1
        fibs.add(2); // F2
        while (true) {
            int next = fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2);
            if (next > number) break;
            fibs.add(next);
        }
        return fibs;
    }
    public static String decimalToFibonacci(int number) {
        ArrayList<Integer> fibs = generateFibonacciUpTo(number);
        StringBuilder sb = new StringBuilder();
        int remaining = number;
        for (int i = fibs.size() - 1; i >= 0; i--) {
            int f = fibs.get(i);
            if (f <= remaining) {
                sb.append("1");
                remaining -= f;
            } else {
                if (sb.length() > 0) sb.append("0");
            }
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
                valid = true;
            } else {
                System.out.println("Некорректный ввод! Пожалуйста, введите целое число.");
                sc.next(); 
            }
        }
        return number;
    }
    public static void main(String[] args) {
        int number = readNumber();
        String fibonacciBase = decimalToFibonacci(number);
        System.out.println("Число в Фибоначчиевой системе: " + fibonacciBase);
    }
}
