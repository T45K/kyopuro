package ABC.ABC144.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long n = scanner.nextLong();
        final long sqrt = (long) Math.sqrt(n) + 1;

        long answer = Long.MAX_VALUE;
        for (long i = sqrt; i >= 1; i--) {
            if (n % i == 0) {
                answer = Math.min(answer, n / i + i - 2);
            }
        }

        System.out.println(answer);
    }
}
