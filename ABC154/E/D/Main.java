package ABC154.E.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final double[] p = new double[1001];
        for (int i = 1; i < 1001; i++) {
            p[i] = i + p[i - 1];
        }

        final double[] array = new double[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += p[(int) array[i]] / array[i];
        }

        double max = sum;
        for (int i = 0; i < n - k; i++) {
            sum = sum - p[(int)array[i]]/array[i] + p[(int)array[k+i]]/array[k+i];
            max = Math.max(sum, max);
        }

        System.out.println(max);
    }
}
