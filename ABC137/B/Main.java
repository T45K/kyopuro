package ABC137.B;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int k = scanner.nextInt();
        final int x = scanner.nextInt();

        for (int i = x - k + 1; i < x + k; i++) {
            System.out.print(i + " ");
        }
    }
}
