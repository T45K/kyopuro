package AtCoder.AGC.AGC034.B;

import java.util.Scanner;

/*
文字列操作 左から貪欲 Aが移動していくイメージ
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();

        long count = 0;
        long constraintA = 0;

        int i = 0;
        while (i < s.length()) {
            final char c = s.charAt(i);
            if (c == 'A') {
                long tmp = i;
                while (i < s.length() && s.charAt(i) == 'A') {
                    i++;
                }
                final long aCount = i - tmp + constraintA;

                tmp = i;
                while (i < s.length() - 1 && s.charAt(i) == 'B' && s.charAt(i + 1) == 'C') {
                    i += 2;
                }
                final long bcCount = (i - tmp) / 2;
                count += aCount * bcCount;
                constraintA = aCount;
            } else {
                constraintA = 0;
                i++;
            }
        }
        System.out.println(count);
    }
}
