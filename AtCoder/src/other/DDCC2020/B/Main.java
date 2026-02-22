package other.DDCC2020.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long[] array = new long[n];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            final long tmp = scanner.nextLong();
            array[i] = tmp;
            sum += tmp;
        }

        long tmp = 0;
        int i;
        for (i = 0; i < n; i++) {
            tmp += array[i];
            if (tmp * 2 == sum) {
                System.out.println(0);
                return;
            }

            if (tmp * 2 > sum) {
                break;
            }
        }

        System.out.println(Math.min(tmp * 2 - sum, sum - (tmp - array[i]) * 2));
    }
}
