package GC2019.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();
        final int z = scanner.nextInt();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            if (a >= x && b >= y && a + b >= z) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
