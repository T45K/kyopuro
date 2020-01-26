package ABC076.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char[] s = scanner.next().toCharArray();
        final char[] t = scanner.next().toCharArray();

        for (int i = s.length - t.length; i >= 0; i--) {
            if (!check(s, t, i)) {
                continue;
            }

            final StringBuilder builder = new StringBuilder();
            for (int j = 0; j < i; j++) {
                builder.append(s[j] == '?' ? 'a' : s[j]);
            }
            for (int j = i; j < i + t.length; j++) {
                builder.append(t[j - i]);
            }
            for (int j = i + t.length; j < s.length; j++) {
                builder.append(s[j] == '?' ? 'a' : s[j]);
            }
            System.out.println(builder);
            return;
        }
        System.out.println("UNRESTORABLE");
    }

    private static boolean check(final char[] s, final char[] t, final int index) {
        for (int i = index; i < index + t.length; i++) {
            if (s[i] == '?') {
                continue;
            }

            if (s[i] != t[i - index]) {
                return false;
            }
        }
        return true;
    }
}
