package AtCoder.ABC.ABC115;

import java.util.Scanner;

public class D {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long x = scanner.nextLong();

        System.out.println(recursive(0, n, x));
    }

    private static long recursive(final long answer, final int level, final long point) {
        if (level == 0) {
            return answer + 1;
        }

        final long burger = 4 * (long) Math.pow(2, level) - 3;
        final long patty = 2 * (long) Math.pow(2, level - 1) - 1;

        if (point == 1) {
            return answer;
        }

        if (point == burger) {
            return answer + 2 * patty + 1;
        }

        if (point == (burger + 1) / 2) {
            return answer + patty + 1;
        }

        if (point < (burger + 1) / 2) {
            return recursive(answer, level - 1, point - 1);
        }

        return recursive(answer + patty + 1, level - 1, point - (burger + 1) / 2);
    }
}
