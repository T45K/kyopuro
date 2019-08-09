package ABC130;

import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextLong();

        final long[] array = new long[n];

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextLong();
        }

        long res = 0;
        int rightIndex = -1;
        int leftIndex = -1;
        long sum = 0;

        while (true) {
            while (sum < k & rightIndex < n - 1) {
                sum += array[++rightIndex];
            }

            if (sum < k) {
                break;
            }

            res += n - rightIndex;

            while (true) {
                sum -= array[++leftIndex];
                if (sum >= k) {
                    res += n - rightIndex;
                } else {
                    break;
                }
            }

            if (rightIndex == n - 1) {
                break;
            }
        }

        System.out.println(res);
    }
}
