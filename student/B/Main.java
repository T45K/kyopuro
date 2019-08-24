package student.B;

import java.util.Scanner;

public class Main {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextLong();

        final int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        long all = 0;
        long latter = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] > array[j]) {
                    if (i < j) {
                        latter++;
                    }
                    all++;
                }
            }
        }

        System.out.println(((all * (k * (k - 1) / 2 % MOD)) %MOD + latter * k % MOD) % MOD);
    }
}
