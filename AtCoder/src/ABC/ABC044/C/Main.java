package ABC.ABC044.C;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
三次元DP 簡単 値更新の際にMaxをとらないので-1で初期化しないほうが良い
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int a = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());
        final int sum = list.stream()
                .mapToInt(Integer::intValue)
                .sum();

        final long[][][] dpCube = new long[n][sum + 1][n + 1];
        dpCube[0][list.get(0)][1] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < dpCube[i].length; j++) {
                System.arraycopy(dpCube[i - 1][j], 0, dpCube[i][j], 0, dpCube[i][j].length);
            }
            final int value = list.get(i);
            dpCube[i][value][1]++;
            for (int j = 0; j <= sum - value; j++) {
                for (int k = 0; k < n; k++) {
                    if (dpCube[i - 1][j][k] == 0) {
                        continue;
                    }
                    dpCube[i][j + value][k + 1] += dpCube[i - 1][j][k];
                }
            }
        }

        long answer = 0;
        for (int i = a; i <= sum; i++) {
            for (int j = 1; j <= n; j++) {
                if (!isJustAvg(i, j, a)) {
                    continue;
                }
                answer += dpCube[n - 1][i][j];
            }
        }

        System.out.println(answer);
    }

    private static boolean isJustAvg(final long divided, final long divide, final long avg) {
        return divided == divide * avg;
    }
}
