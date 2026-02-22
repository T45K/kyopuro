package ABC.ABC067.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        long sum = 0;
        final long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            array[i] = a;
            sum += a;
        }

        long min = Long.MAX_VALUE;
        long sum2 = 0;
        for (int i = 0; i < n - 1; i++) {
            final long tmp = array[i];
            sum2 += tmp;
            sum -= tmp;

            min = Math.min(min, Math.abs(sum - sum2));
        }

        System.out.println(min);
    }
}
