package ABC057.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long n = scanner.nextLong();

        for (long i = (int) Math.sqrt(n); i >= 2; i--) {
            if (n % i == 0) {
                System.out.println(Long.toString(n / i).length());
                return;
            }
        }

        System.out.println(Long.toString(n).length());
    }
}
