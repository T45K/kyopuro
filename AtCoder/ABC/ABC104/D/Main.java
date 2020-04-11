package AtCoder.ABC.ABC104.D;

import java.util.Scanner;

public class Main {
    private static final int MOD = 1000000007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();

        final long[][] dpTable = new long[s.length() + 1][4];
        dpTable[s.length()][3] = 1;

        for (int i = s.length() - 1; i >= 0; i--) {
            final char character = s.charAt(i);

            dpTable[i][3] = character == '?' ? 3 * dpTable[i + 1][3] : dpTable[i + 1][3];
            dpTable[i][3] %= MOD;

            dpTable[i][2] = character == '?' ? 3 * dpTable[i + 1][2] : dpTable[i + 1][2];
            dpTable[i][2] += (character == '?' || character == 'C') ? dpTable[i + 1][3] : 0;
            dpTable[i][2] %= MOD;

            dpTable[i][1] = character == '?' ? 3 * dpTable[i + 1][1] : dpTable[i + 1][1];
            dpTable[i][1] += (character == '?' || character == 'B') ? dpTable[i + 1][2] : 0;
            dpTable[i][1] %= MOD;

            dpTable[i][0] = character == '?' ? 3 * dpTable[i + 1][0] : dpTable[i + 1][0];
            dpTable[i][0] += (character == '?' || character == 'A') ? dpTable[i + 1][1] : 0;
            dpTable[i][0] %= MOD;
        }

        System.out.println(dpTable[0][0]);
    }
}
