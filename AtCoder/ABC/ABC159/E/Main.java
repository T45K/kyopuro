package AtCoder.ABC.ABC159.E;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int k = scanner.nextInt();

        final int[][] table = new int[h][w];
        for (int i = 0; i < h; i++) {
            final char[] array = scanner.next().toCharArray();
            table[i][0] = array[0] - '0';
            for (int j = 1; j < array.length; j++) {
                table[i][j] = table[i][j - 1] + array[j] - '0';
            }
        }

        final boolean[] horizontals = new boolean[h];
        final int answer = recursive(0, horizontals, table, h, w, k);
        System.out.println(answer);
    }

    private static int recursive(final int index, final boolean[] horizontals, final int[][] table, final int h, final int w, final int k) {
        if (index == h) {
            if (!meetPreliminary(table, horizontals, k, h, w)) {
                return Integer.MAX_VALUE;
            }

            int vertical = 0;
            int count = 0;
            for (int j = 0; j < w; j++) {
                int sum = 0;
                for (int i = 0; i < h; i++) {
                    sum += table[i][j] - (vertical == 0 ? 0 : table[i][vertical - 1]);
                    if (sum > k) {
                        vertical = j;
                        count++;
                    }
                    if (horizontals[i]) {
                        sum = 0;
                    }
                }
            }

            for (final boolean horizontal : horizontals) {
                if (horizontal) {
                    count++;
                }
            }
            return count;
        }

        horizontals[index] = true;
        int tmp = recursive(index + 1, horizontals, table, h, w, k);

        horizontals[index] = false;
        return Math.min(tmp, recursive(index + 1, horizontals, table, h, w, k));
    }

    // 1列でKを超えないかどうか
    private static boolean meetPreliminary(final int[][] table, final boolean[] horizontals, final int k, final int h, final int w) {
        for (int j = 0; j < w; j++) {
            int sum = 0;
            for (int i = 0; i < h; i++) {
                sum += table[i][j] - (j == 0 ? 0 : table[i][j - 1]);
                if (sum > k) {
                    return false;
                }
                if (horizontals[i]) {
                    sum = 0;
                }
            }
        }
        return true;
    }
}
