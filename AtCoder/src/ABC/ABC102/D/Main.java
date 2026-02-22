package ABC.ABC102.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long[] cumulativeSum = new long[n];
        cumulativeSum[0] = scanner.nextLong();

        for (int i = 1; i < n; i++) {
            cumulativeSum[i] = cumulativeSum[i - 1] + scanner.nextLong();
        }

        int firstIndex = 0;
        int thirdIndex = 2;
        long answer = Integer.MAX_VALUE;
        for (int secondIndex = 1; secondIndex < n - 2; secondIndex++) {
            for (; firstIndex < secondIndex - 1; firstIndex++) {
                if (Math.abs(cumulativeSum[secondIndex] - cumulativeSum[firstIndex] * 2) <= Math.abs((cumulativeSum[secondIndex] - cumulativeSum[firstIndex + 1] * 2))) {
                    break;
                }
            }

            for (; thirdIndex < n - 2; thirdIndex++) {
                if (Math.abs(cumulativeSum[n - 1] - cumulativeSum[thirdIndex] * 2 + cumulativeSum[secondIndex]) <= Math.abs(cumulativeSum[n - 1] - cumulativeSum[thirdIndex + 1] * 2 + cumulativeSum[secondIndex])) {
                    break;
                }
            }

            long a = cumulativeSum[firstIndex];
            long b = cumulativeSum[secondIndex] - cumulativeSum[firstIndex];
            long c = cumulativeSum[thirdIndex] - cumulativeSum[secondIndex];
            long d = cumulativeSum[n - 1] - cumulativeSum[thirdIndex];

            long tmp = max(a, b, c, d) - min(a, b, c, d);
            answer = Math.min(tmp, answer);
        }

        System.out.println(answer);
    }

    private static long max(final long a, final long b, final long c, final long d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }

    private static long min(final long a, final long b, final long c, final long d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }
}
