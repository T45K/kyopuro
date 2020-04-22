package AtCoder.ARC.ARC046.B;

import java.util.Scanner;

/*
ゲーム理論 相手のマネをするのが最適になるパターン
 */
public class Main {
    private static final String TAKAHASHI = "Takahashi";
    private static final String AOKI = "Aoki";

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int t = scanner.nextInt();
        final int a = scanner.nextInt();

        System.out.println(isTakahashiWinner(n, t, a) ? TAKAHASHI : AOKI);
    }

    private static boolean isTakahashiWinner(final int n, final int t, final int a) {
        return t == a && n % (t + 1) > 0 || n <= t || t > a;
    }
}
    