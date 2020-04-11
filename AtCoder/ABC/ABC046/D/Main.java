package AtCoder.ABC.ABC046.D;

import java.util.Scanner;

/*
気付けば簡単 気付くのも簡単
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        final int length = s.length();

        int count = 0;
        for (int i = 0; i < (length + 1) / 2; i++) {
            if (s.charAt(i) == 'p') {
                count--;
            }
        }
        for (int i = (length + 1) / 2; i < length; i++) {
            if (s.charAt(i) == 'g') {
                count++;
            }
        }
        System.out.println(count);
    }
}
