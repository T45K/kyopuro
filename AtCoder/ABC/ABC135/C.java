package AtCoder.ABC.ABC135;

import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long[] cities = new long[n + 1];
        final long[] braves = new long[n];

        for (int i = 0; i < n + 1; i++) {
            cities[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            braves[i] = scanner.nextInt();
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            if (braves[i] > cities[i]) {
                result += cities[i];
                final long rest = braves[i] - cities[i];
                if (rest > cities[i + 1]) {
                    result += cities[i + 1];
                    cities[i + 1] = 0;
                } else {
                    cities[i + 1] -= rest;
                    result += rest;
                }
            } else {
                result += braves[i];
            }
        }

        System.out.println(result);
    }
}
