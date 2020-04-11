package AtCoder.ABC.ABC083.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        long x = scanner.nextLong();
        final long y = scanner.nextLong();

        int counter = 0;
        while (x <= y) {
            x *= 2L;
            counter++;
        }

        System.out.println(counter);
    }
}
