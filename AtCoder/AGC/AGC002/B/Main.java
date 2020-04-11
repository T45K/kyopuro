package AtCoder.AGC.AtCoder.AGC.AGC002.B;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int[] balls = new int[n + 1];
        Arrays.fill(balls, 1, n + 1, 1);
        final boolean[] isContainingRedBall = new boolean[n + 1];
        isContainingRedBall[1] = true;

        for (int i = 0; i < m; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();

            isContainingRedBall[y] |= isContainingRedBall[x];
            balls[x]--;
            balls[y]++;
            if (balls[x] == 0) {
                isContainingRedBall[x] = false;
            }
        }

        final long answer = IntStream.rangeClosed(1, n)
                .mapToObj(i -> isContainingRedBall[i])
                .filter(Boolean::booleanValue)
                .count();
        System.out.println(answer);
    }
}
