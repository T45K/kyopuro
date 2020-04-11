package AtCoder.ABC.ABC113;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class D {

    private static long MOD = 1000000007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int k = scanner.nextInt();

        final List<boolean[]> patterns = new ArrayList<>();
        final boolean[] pattern = new boolean[w - 1];
        recursive(patterns, pattern, 0);

        final long[] counter = new long[w - 1];
        int all = 0;
        for (final boolean[] booleans : patterns) {
            all++;
            for (int i = 0; i < booleans.length; i++) {
                if (booleans[i]) {
                    counter[i]++;
                }
            }
        }

        final long[][] table = new long[h + 1][w];
        table[0][0] = 1;

        for (int i = 1; i < h + 1; i++) {
            for (int j = 0; j < w; j++) {
                int tmp = 0;
                if (j != 0) {
                    tmp += counter[j - 1];
                    table[i][j] += counter[j - 1] * table[i - 1][j - 1] % MOD;
                }

                if (j != w - 1) {
                    tmp += counter[j];
                    table[i][j] += counter[j] * table[i - 1][j + 1] % MOD;
                }

                table[i][j] += (all - tmp) * table[i - 1][j] % MOD;
            }
        }
        System.out.println(table[h][k - 1] % MOD);
    }

    private static void recursive(final List<boolean[]> patterns, final boolean[] pattern, final int index) {
        if (index == pattern.length) {
            patterns.add(pattern);
            return;
        }

        final boolean[] tmpPattern = Arrays.copyOf(pattern, pattern.length);
        tmpPattern[index] = true;
        recursive(patterns, pattern, index + 1);
        if (index == 0 || !pattern[index - 1]) {
            recursive(patterns, tmpPattern, index + 1);
        }
    }
}
