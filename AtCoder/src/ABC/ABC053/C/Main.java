package ABC.ABC053.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long x = scanner.nextLong();
        final long div = x / 11L;
        final int mod = (int) (x % 11);

        if (mod == 0) {
            System.out.println(2 * div);
        } else if (mod <= 6) {
            System.out.println(2 * div + 1);
        } else {
            System.out.println(2 * div + 2);
        }
    }
}
