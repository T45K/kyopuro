package ABC.ABC159.F;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int s = scanner.nextInt();

        final int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        final long[][] dpTable = new long[n][s];
        for (final long[] array : dpTable) {
            Arrays.fill(array, -1);
        }
        long count = 0;
        if (a[0] < s) {
            dpTable[0][a[0]] = 1;
        } else if (a[0] == s) {
            count = n;
        }

        for (int i = 1; i < n; i++) {
            // a[i]を選ばない場合
            System.arraycopy(dpTable[i - 1], 0, dpTable[i], 0, s);

            // a[i] が s を超過した場合何もしない
            if (a[i] > s) {
                continue;
            }
            // a[i] が s だと，a[i] を含む全ての区間の組み合わせを足す
            else if (a[i] == s) {
                count = (count + (i + 1) * (n - i)) % MOD;
                continue;
            }

            // a[i] を足して s になる場合，始点の距離の総数 * 終点の距離の総数を足せる
            if (dpTable[i - 1][s - a[i]] != -1) {
                count = (count + dpTable[i - 1][s - a[i]] * (n - i)) % MOD;
            }

            // a[i] を選ぶ場合
            for (int j = 1; j < s - a[i]; j++) {
                if (dpTable[i - 1][j] == -1) {
                    continue;
                }
                // L が確定している時に a[i] を選ぶ場合
                if (dpTable[i][j + a[i]] == -1) {
                    dpTable[i][j + a[i]] = 0;
                }
                dpTable[i][j + a[i]] += dpTable[i - 1][j];
                dpTable[i][j + a[i]] %= MOD;
            }

            // 新たに始点を始める場合
            if (dpTable[i][a[i]] == -1) {
                dpTable[i][a[i]] = 0;
            }
            dpTable[i][a[i]] += i + 1;
            dpTable[i][a[i]] %= MOD;
        }

        System.out.println(count);
    }
}
