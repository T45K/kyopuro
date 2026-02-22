package ABC.ABC162.F;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
DP 基本的には1つ飛ばしで数字を選んでいく nが偶数だと2つ飛ばし1回，奇数だと2つ飛ばし*2 or 3つ飛ばしができると考えてDP
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final List<Long> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextLong())
                .collect(Collectors.toList());

        if (n % 2 == 0) {
            final long[][] dpTable = new long[n][2];
            dpTable[0][0] = list.get(0);
            dpTable[1][1] = list.get(1);
            for (int i = 2; i < n; i++) {
                final long tmp = list.get(i);
                dpTable[i][0] = dpTable[i - 2][0] + tmp;
                if (i >= 3) {
                    dpTable[i][1] = max(dpTable[i - 2][1], dpTable[i - 3][0]) + tmp;
                }
            }

            System.out.println(max(dpTable[n - 1][1], dpTable[n - 2][0]));
            return;
        }

        final long[][] dpTable = new long[n][3];
        dpTable[0][0] = list.get(0);
        dpTable[1][1] = list.get(1);
        dpTable[2][2] = list.get(2);
        for (int i = 2; i < n; i++) {
            final long tmp = list.get(i);
            dpTable[i][0] = dpTable[i - 2][0] + tmp;
            if (i >= 3) {
                dpTable[i][1] = max(dpTable[i - 2][1], dpTable[i - 3][0]) + tmp;
            }
            if (i >= 4) {
                dpTable[i][2] = max(dpTable[i - 2][2], dpTable[i - 3][1], dpTable[i - 4][0]) + tmp;
            }
        }

        System.out.println(max(dpTable[n - 1][2], dpTable[n - 2][1], dpTable[n - 3][0]));
    }

    private static long max(long... numbers) {
        return Arrays.stream(numbers).max().orElseThrow();
    }
}
