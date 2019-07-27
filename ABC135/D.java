package ABC135;

import java.util.Scanner;

public class D {
    private static int MOD = 1000000007;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();

        final int[][] table = new int[s.length()][13];

        {
            final char c = getReverseIndex(s, 0);
            if (isNum(c)) {
                final int num = getNum(c);
                final int num2 = (num + 8) % 13;
                table[0][num2] = 1;
            } else {
                for (int i = 0; i < 10; i++) {
                    final int num2 = (i + 8) % 13;
                    table[0][num2] = 1;
                }
            }
        }

        for (int i = 1; i < s.length(); i++) {
            final char c = getReverseIndex(s, i);
            if (c >= '0' && c <= '9') {
                final int i1 = intPow(i) * getNum(c) % 13;
                for (int j = 0; j < 13; j++) {
                    table[i][(j + i1) % 13] = (table[i][(j + i1) % 13] + table[i - 1][j]) % MOD;

                }
            } else {
                for (int k = 0; k < 10; k++) {
                    final int i1 = intPow(i) * k % 31;
                    for (int j = 0; j < 13; j++) {
                        table[i][(j + i1) % 13] = (table[i][(j + i1) % 13] + table[i - 1][j]) % MOD;
                    }
                }
            }
        }

        System.out.println(table[s.length() - 1][0]);
    }

    private static int intPow(final int b) {
        int c = b % 5;
        return (int) Math.pow(10, c);
    }

    private static int getNum(final char c) {
        return c - '0';
    }

    private static boolean isNum(final char c) {
        return c >= '0' && c <= '9';
    }

    private static char getReverseIndex(final String s, final int index) {
        return s.charAt(s.length() - index - 1);
    }
}
