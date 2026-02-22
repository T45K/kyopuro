package other.CodeFestival2016.C;

import java.util.Scanner;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[] tArray = new int[n];
        final int[] aArray = new int[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            final int t = scanner.nextInt();
            max = Math.max(t, max);
            tArray[i] = t;
        }

        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            max = Math.max(a, max);
            aArray[i] = a;
        }

        long sum = 1;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            final int t = tArray[i], a = aArray[i];
            if (t == max && a == max) {
                flag = true;
            }

            if (i > 0 && i < n - 1) {
                if (t != tArray[i - 1] || a != aArray[i + 1]) {
                    continue;
                }
                sum = sum * Math.min(t, a) % MOD;
            }
        }

        System.out.println(flag ? sum : 0);
    }
}
