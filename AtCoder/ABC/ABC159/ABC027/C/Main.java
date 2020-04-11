package AtCoder.ABC.ABC159.AtCoder.ABC.ABC027.C;

import java.util.Scanner;

public class Main {
    private static final String TAKAHASHI = "Takahashi";
    private static final String AOKI = "Aoki";

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        final int digits = Long.toBinaryString(n).length() - 1;

        long begin = 1L << digits;
        long end = begin << 1L;
        long mid = (begin + end) / 2L;
        if (digits % 2 == 0) {
            while (true) {
                if (n < mid) {
                    System.out.println(TAKAHASHI);
                    return;
                }
                begin = mid;
                mid = (begin + end) / 2L;
                if (n >= mid) {
                    System.out.println(AOKI);
                    return;
                }
                end = mid;
                mid = (begin + end) / 2L;
            }
        } else {
            while (true) {
                if (n >= mid) {
                    System.out.println(TAKAHASHI);
                    return;
                }
                end = mid;
                mid = (begin + end) / 2L;
                if (n < mid) {
                    System.out.println(AOKI);
                    return;
                }
                begin = mid;
                mid = (begin + end) / 2L;
            }
        }
    }
}
