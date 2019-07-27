package ABC135;

import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] cities = new int[n + 1];
        final int[] braves = new int[n];

        for (int i = 0; i < n + 1; i++) {
            cities[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            braves[i] = scanner.nextInt();
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (braves[i] > cities[i]) {
                res += cities[i];
                final int rest = braves[i] - cities[i];
                if (rest > cities[i + 1]) {
                    res += cities[i + 1];
                    cities[i + 1] = 0;
                } else {
                    cities[i + 1] -= rest;
                    res += rest;
                }
            } else {
                res += braves[i];
            }
        }

        System.out.println(res);
    }
}
