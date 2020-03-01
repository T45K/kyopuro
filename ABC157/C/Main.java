package ABC157.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Pair[] pairs = new Pair[m];
        for (int i = 0; i < m; i++) {
            pairs[i] = new Pair(scanner.nextInt() - 1, scanner.nextInt());
        }

        if (n == 1) {
            for (int i = 0; i <= 9; i++) {
                if (isValid(i, pairs)) {
                    System.out.println(i);
                    return;
                }
            }
        } else if (n == 2) {
            for (int i = 10; i <= 99; i++) {
                if (isValid(i, pairs)) {
                    System.out.println(i);
                    return;
                }
            }
        } else {
            for (int i = 100; i <= 999; i++) {
                if (isValid(i, pairs)) {
                    System.out.println(i);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isValid(final int i, final Pair[] pairs) {
        final String str = String.valueOf(i);
        for (final Pair pair : pairs) {
            if (str.charAt(pair.s) - '0' != pair.c) {
                return false;
            }
        }

        return true;
    }

    static class Pair {
        int s;
        int c;

        Pair(final int s, final int c) {
            this.s = s;
            this.c = c;
        }
    }
}
