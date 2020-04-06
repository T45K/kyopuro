package ABC043.D;

import java.util.Scanner;

/*
文字列 とても簡単 証明はよく分からん
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        if (s.length() == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                System.out.println("1 2");
            } else {
                System.out.println("-1 -1");
            }
            return;
        }

        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 2)) {
                System.out.println((i - 1) + " " + (i + 1));
                return;
            }
            if (s.charAt(i) == s.charAt(i - 1)) {
                System.out.println(i + " " + (i + 1));
                return;
            }
        }

        System.out.println("-1 -1");
    }
}
