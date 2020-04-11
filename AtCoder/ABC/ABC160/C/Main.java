package AtCoder.ABC.ABC160.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int k = scanner.nextInt();
        final int n = scanner.nextInt();

        final int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            final int tmp = scanner.nextInt();
            array[i] = tmp;
        }

        int min = array[n - 1] - array[0];
        for (int i = 0; i < array.length - 1; i++) {
            min = Math.min(min, k + array[i] - array[i + 1]);
        }

        System.out.println(min);
    }
}
