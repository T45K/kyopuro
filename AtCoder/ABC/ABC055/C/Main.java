package AtCoder.ABC.ABC055.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();

        if (m <= n * 2) {
            System.out.println(m / 2);
            return;
        }

        System.out.println(n + (m - 2 * n) / 4);
    }
}
