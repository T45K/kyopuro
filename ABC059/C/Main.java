package ABC059.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        long sum1 = 0, sum2 = 0;
        long cost1 = 0, cost2 = 0;
        long sign1 = 1, sign2 = -1;
        for (int i = 0; i < n; i++) {
            final long a = scanner.nextLong();
            sum1 += a;
            sum2 += a;

            if (sign1 == 1 && sum1 < 1) {
                cost1 += 1 - sum1;
                sum1 = 1;
            } else if (sign1 == -1 && sum1 > -1) {
                cost1 += sum1 + 1;
                sum1 = -1;
            }

            if (sign2 == 1 && sum2 < 1) {
                cost2 += 1 - sum2;
                sum2 = 1;
            } else if (sign2 == -1 && sum2 > -1) {
                cost2 += sum2 + 1;
                sum2 = -1;
            }

            sign1 *= -1;
            sign2 *= -1;
        }

        System.out.println(Math.min(cost1, cost2));
    }
}
