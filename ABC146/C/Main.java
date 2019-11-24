package ABC146.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long a = scanner.nextInt();
        final long b = scanner.nextInt();
        final long x = scanner.nextLong();

        if (a + b > x) {
            System.out.println(0);
            return;
        }

        System.out.println(binarySearch(1, 1000000000, a, b, x));
    }

    private static long binarySearch(final long begin, final long end, final long a, final long b, final long money) {
        if (end - begin <= 1) {
            final int endLength = Long.toString(end).length();
            if (a * end + b * endLength <= money) {
                return end;
            }
            return begin;
        }

        final long mid = (end + begin) / 2;
        final int midLength = Long.toString(mid).length();
        if (a * mid + b * midLength > money) {
            return binarySearch(begin, mid, a, b, money);
        }

        return binarySearch(mid, end, a, b, money);
    }
}
