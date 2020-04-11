package AtCoder.ABC.ABC092.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[n + 1];

        int all = 0;
        for (int i = 1; i < n + 1; i++) {
            array[i] = scanner.nextInt();
            all += Math.abs(array[i] - array[i - 1]);
        }

        all += Math.abs(array[n]);

        for (int i = 1; i < n; i++) {
            System.out.println(all - Math.abs(array[i] - array[i - 1]) - Math.abs(array[i + 1] - array[i]) + Math.abs(array[i - 1] - array[i + 1]));
        }

        System.out.println(all - Math.abs(array[n] - array[n - 1]) - Math.abs(array[n]) + Math.abs(array[n - 1]));
    }
}
