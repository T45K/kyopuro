package pana2020.E;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String a = scanner.next();
        final String b = scanner.next();
        final String c = scanner.next();

        int min = Integer.MAX_VALUE;
        for (final int candidate : Arrays.asList(solve(a, b, c), solve(a, c, b), solve(b, a, c), solve(b, c, a), solve(c, a, b), solve(c, b, a))) {
            min = Math.min(min, candidate);
        }

        System.out.println(min);
    }

    private static int solve(final String a, final String b, final String c) {
        final int all = a.length() + b.length() + c.length();
        final boolean[] ab = init(all, a, b);
        final boolean[] ac = init(all, a, c);
        final boolean[] bc = init(all, b, c);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= Math.max(b.length(), a.length() - i); j++) {
                if (ab[i] && bc[j] && ac[i + j]) {
                    min = Math.min(min, max(a.length(), i + b.length(), i + j + c.length()));
                }
            }
        }
        return min;
    }

    private static int max(final int... array) {
        int max = 0;
        for (final int i : array) {
            max = Math.max(i, max);
        }
        return max;
    }

    private static boolean[] init(final int length, final String a, final String b) {
        final boolean[] array = new boolean[length];
        Arrays.fill(array, true);
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length() && i + j < a.length(); j++) {
                if (!match(a.charAt(i + j), b.charAt(j))) {
                    array[i] = false;
                    break;
                }
            }
        }

        return array;
    }

    private static boolean match(final char a, final char b) {
        return a == '?' || b == '?' || a == b;
    }
}
