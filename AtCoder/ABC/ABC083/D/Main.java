package AtCoder.ABC.ABC083.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        int k = s.length();
        char prev = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != prev) {
                k = Math.min(k, Math.max(i, s.length() - i));
                prev = s.charAt(i);
            }
        }

        System.out.println(k);
    }
}
