package AtCoder.ABC.ABC071.D;

import java.util.Scanner;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String s1 = scanner.next();
        final String s2 = scanner.next();

        boolean isVertical = false;
        long count = 1;
        for (int i = 0; i < n; ) {
            final char c1 = s1.charAt(i);
            final char c2 = s2.charAt(i);

            if (i == 0) {
                isVertical = c1 == c2;
                count = isVertical ? 3 : 6;
            } else {
                if (c1 == c2) {
                    count *= isVertical ? 2 : 1;
                    isVertical = true;
                } else {
                    count *= isVertical ? 2 : 3;
                    isVertical = false;
                }
            }

            count %= MOD;
            i += isVertical ? 1 : 2;
        }

        System.out.println(count);
    }
}
