package ABC.ABC160.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long x = scanner.nextInt();
        final long i = x / 500L;
        final long j = (x - i * 500L) / 5L;
        System.out.println(i * 1000L + j * 5L);
    }
}
