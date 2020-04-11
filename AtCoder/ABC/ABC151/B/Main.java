package AtCoder.ABC.ABC151.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int m = scanner.nextInt();

        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += scanner.nextInt();
        }

        if (n * m > sum + k) {
            System.out.println(-1);
        } else {
            System.out.println(Math.max(0, n * m - sum));
        }
    }
}
