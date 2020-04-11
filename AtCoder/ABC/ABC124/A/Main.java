package AtCoder.ABC.ABC124.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[] a = {scanner.nextInt()};
        final int[] b = {scanner.nextInt()};

        System.out.println(get(a, b) + get(a, b));
    }

    private static int get(final int[] a, final int[] b) {
        if (a[0] > b[0]) {
            return a[0]--;
        }

        return b[0]--;
    }
}
