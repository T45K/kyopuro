package AtCoder.AGC.AGC039.B;

import java.util.Scanner;

/*
グラフ ワーシャルフロイドは自明 解説が意味不明
ある点から距離が二通り以上で表される時，それらの偶奇が違うとグラフを構築できない
答えはグラフの直径 + 1になる
 */
public class Main {
    private static final int INITIAL_VALUE = Integer.MAX_VALUE / 2;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[][] table = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            final String s = scanner.next();
            for (int j = 1; j <= n; j++) {
                final int value = s.charAt(j - 1) - '0';
                table[i][j] = value == 0 ? INITIAL_VALUE : 1;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        table[i][j] = 0;
                    }
                    if (table[i][j] < INITIAL_VALUE && table[i][k] + table[k][j] < INITIAL_VALUE
                            && table[i][j] % 2 != (table[i][k] + table[k][j]) % 2) {
                        System.out.println(-1);
                        return;
                    }
                    table[i][j] = Math.min(table[i][j], table[i][k] + table[k][j]);
                }
            }
        }

        int max = 0;
        for (final int[] array : table) {
            for (final int value : array) {
                max = Math.max(max, value + 1);
            }
        }

        System.out.println(max);
    }
}
