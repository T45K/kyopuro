package ABC.ABC146.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final char[] s = scanner.next().toCharArray();
        for (int i = 0; i < s.length; i++) {
            final char c = s[i];
            char tmp = (char) ((c + n) % 'A' + 'A');
            s[i] = tmp > 'Z' ? (char) (tmp - 26) : tmp;
        }

        System.out.println(new String(s));
    }
}
