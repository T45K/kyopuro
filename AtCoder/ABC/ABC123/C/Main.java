package AtCoder.ABC.ABC123.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long number = scanner.nextLong();
        long min = Long.MAX_VALUE;

        for (int i = 0; i < 5; i++) {
            final long l = scanner.nextLong();
            if (min >= l) {
                min = l;
            }
        }

        System.out.println((number + min - 1)/min + 4);
    }
}
