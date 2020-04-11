package AtCoder.ABC.ABC158.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long n = scanner.nextLong();
        final long a = scanner.nextLong();
        final long b = scanner.nextLong();

        final long div = n / (a + b);
        final long mod = n % (a + b);
        System.out.println(a * div + Math.min(mod,a));
    }
}
