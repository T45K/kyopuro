package AtCoder.ABC.ABC159.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        if (isPalindrome(0, s.length() - 1, s) && isPalindrome(0, (s.length() - 1) / 2 - 1, s) && isPalindrome((s.length() + 3) / 2 - 1, s.length() - 1, s)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static boolean isPalindrome(final int start, final int end, final String s) {
        for (int i = 0; ; i++) {
            if (start + i > end - i) {
                return true;
            }
            if (s.charAt(start + i) != s.charAt(end - i)) {
                return false;
            }
        }
    }
}
