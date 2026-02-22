package ABC.ABC046.C;

import java.util.Scanner;

/*
算数 簡単 ほんまに水Diffか？
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        long t = scanner.nextInt();
        long a = scanner.nextInt();

        for (int i = 0; i < n - 1; i++) {
            final long tTmp = scanner.nextInt();
            final long aTmp = scanner.nextInt();

            if (t * aTmp == a * tTmp) {
                continue;
            }

            final long tDiv = (t + tTmp - 1) / tTmp;
            final long aDiv = (a + aTmp - 1) / aTmp;

            if (aTmp * tDiv > a) {
                t = tTmp * tDiv;
                a = aTmp * tDiv;
            } else {
                t = tTmp * aDiv;
                a = aTmp * aDiv;
            }
        }

        System.out.println(t + a);
    }
}
