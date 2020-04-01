package ABC145.E;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainAlt {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int t = scanner.nextInt();

        final List<Food> list = IntStream.range(0, n)
                .mapToObj(i -> new Food(scanner.nextInt(), scanner.nextInt()))
                .collect(Collectors.toList());

        final int[][] naturalDp = dp(list, n, t);
        Collections.reverse(list);
        final int[][] reverseDp = dp(list, n, t);
        Collections.reverse(list);

        int max = 0;
        for (int i = 0; i < n; i++) {
            final int deliciousness = list.get(i).deliciousness;
            for (int j = 0; j < t; j++) {
                final int current = (i > 0 ? naturalDp[i - 1][j] : 0) + (i < n - 1 ? reverseDp[n - i - 2][t - 1 - j] : 0) + deliciousness;
                max = Math.max(max, current);
            }
        }

        System.out.println(max);
    }

    private static int[][] dp(final List<Food> list, final int n, final int t) {
        final int[][] dp = new int[n][t];
        for (int i = list.get(0).time; i < t; i++) {
            dp[0][i] = list.get(0).deliciousness;
        }
        for (int i = 1; i < n; i++) {
            System.arraycopy(dp[i - 1], 0, dp[i], 0, t);
            final Food food = list.get(i);
            for (int j = 0; j < t - food.time; j++) {
                dp[i][j + food.time] = Math.max(dp[i][j + food.time], dp[i - 1][j] + food.deliciousness);
            }
        }

        return dp;
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
