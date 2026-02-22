package ABC.ABC122;

import java.util.Scanner;

public class D {
    private static final int MOD = 1000000007;
    private static final int T = 0;
    private static final int A = 1;
    private static final int G = 2;
    private static final int C = 3;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();

        final int[][][][] table = new int[length + 1][4][4][4];

        table[0][T][T][T] = 1;

        for (int i = 0; i < length; i++) {
            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < 4; b++) {
                    for (int c = 0; c < 4; c++) {
                        for (int d = 0; d < 4; d++) {
                            if (b == A && c == G && d == C
                                    || b == A && c == C && d == G
                                    || b == G && c == A && d == C) {
                                continue;
                            }

                            if (a == A && c == G && d == C
                                    || a == A && b == G && d == C) {
                                continue;
                            }

                            table[i + 1][b][c][d] = (table[i + 1][b][c][d] + table[i][a][b][c]) % MOD;
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                for (int c = 0; c < 4; c++) {
                    result = (result + table[length][a][b][c]) % MOD;
                }
            }
        }
        System.out.println(result);
    }
}
