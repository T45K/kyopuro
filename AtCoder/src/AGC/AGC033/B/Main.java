package AGC.AGC033.B;

import java.util.Scanner;
import java.util.function.Function;

/*
グラフ座標 上下左右の4パターンに分けて考える 簡単
 */
public class Main {
    private static final char[] MOVE = {'L', 'U', 'R', 'D'};

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int n = scanner.nextInt();
        final int sr = scanner.nextInt();
        final int sc = scanner.nextInt();

        final String s = scanner.next();
        final String t = scanner.next();

        final int[] initialValues = {sc, sr};
        final Function<Integer, Integer> decrement = i -> i - 1;
        final Function<Integer, Integer> increment = i -> i + 1;
        final Function[] operators = {decrement, decrement, increment, increment};
        final int[] limits = {w, h};
        for (int i = 0; i < MOVE.length; i++) {
            if (!isYes(initialValues[i % 2], operators[i], operators[(i + 2) % 4], n, limits[i % 2], s, t, i)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static boolean isYes(final int initialValue, final Function<Integer, Integer> expression1, final Function<Integer, Integer> expression2, final int n, final int limit, final String s, final String t, final int tSelect) {
        int tmp = initialValue;
        for (int i = 0; i < n; i++) {
            final char si = s.charAt(i);
            if (si == MOVE[tSelect]) {
                tmp = expression1.apply(tmp);
            }
            if (!isInside(tmp, limit)) {
                return false;
            }
            final char ti = t.charAt(i);
            if (ti == MOVE[(tSelect + 2) % 4] && isInside(expression2.apply(tmp), limit)) {
                tmp = expression2.apply(tmp);
            }
        }
        return true;
    }

    private static boolean isInside(final int position, final int limit) {
        return 1 <= position && position <= limit;
    }
}
