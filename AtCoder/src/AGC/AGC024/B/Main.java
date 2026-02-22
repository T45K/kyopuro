package AGC.AGC024.B;

import java.util.Scanner;

/*
数字並び替え 簡単
昇順に並んでいる最長の列以外の数字を動かす
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[] indices = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            indices[scanner.nextInt()] = i;
        }

        int count = 0;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (indices[i] > indices[i - 1]) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 1;
            }
        }
        System.out.println(n - max);
    }
}
