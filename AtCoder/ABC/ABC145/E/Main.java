package AtCoder.ABC.ABC145.E;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
DP 王道
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int t = scanner.nextInt();

        final List<Food> list = IntStream.range(0, n)
                .mapToObj(i -> new Food(scanner.nextInt(), scanner.nextInt()))
                .sorted(Comparator.comparingInt(food -> food.time))
                .collect(Collectors.toList());

        final int[][] dp = new int[n][t + 1];
        for (final int[] array : dp) {
            Arrays.fill(array, -1);
        }
        if (list.get(0).time < t) {
            dp[0][list.get(0).time] = list.get(0).deliciousness;
        } else {
            dp[0][t] = list.get(0).deliciousness;
        }
        dp[0][0] = 0;
        for (int i = 1; i < n; i++) {
            System.arraycopy(dp[i - 1], 0, dp[i], 0, t + 1);
            final Food food = list.get(i);
            for (int j = 0; j < t; j++) {
                if (dp[i - 1][j] < 0) {
                    continue;
                }
                if (j + food.time < t) {
                    dp[i][j + food.time] = Math.max(dp[i][j + food.time], dp[i - 1][j] + food.deliciousness);
                } else {
                    dp[i][t] = Math.max(dp[i][t], dp[i - 1][j] + food.deliciousness);
                }
            }
        }

        int max = 0;
        for (final int value : dp[n - 1]) {
            max = Math.max(max, value);
        }

        System.out.println(max);
    }

    private static class Food {
        final int time;
        final int deliciousness;

        Food(final int time, final int deliciousness) {
            this.time = time;
            this.deliciousness = deliciousness;
        }
    }
}
