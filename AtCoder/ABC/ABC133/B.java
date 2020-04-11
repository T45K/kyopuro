package AtCoder.ABC.ABC133;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int d = scanner.nextInt();

        final int[][] point = new int[n][d];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < d; j++) {
                point[i][j] = scanner.nextInt();
            }
        }

        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < d; k++) {
                    sum += (point[i][k] - point[j][k]) * (point[i][k] - point[j][k]);
                }
                if (isIntSqrt(sum)) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    private static boolean isIntSqrt(final int a) {
        final double sqrt = Math.sqrt(a);
        return Math.floor(sqrt) == sqrt;
    }
}
