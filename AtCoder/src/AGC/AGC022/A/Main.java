package AGC.AGC022.A;

import java.util.Scanner;

public class Main {
    private static int ALL_ALPHABET_NUMBER = 26;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();

        if (s.length() == 26) {
            final boolean[] isNotExisting = new boolean[ALL_ALPHABET_NUMBER];
            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = 0; j < isNotExisting.length; j++) {
                    if (isNotExisting[j] && s.charAt(i) < 'a' + j) {
                        System.out.println(s.substring(0, i) + (char) ('a' + j));
                        return;
                    }
                }
                isNotExisting[s.charAt(i) - 'a'] = true;
            }
            System.out.println(-1);
            return;
        }

        final boolean[] isExisting = new boolean[ALL_ALPHABET_NUMBER];

        for (int i = 0; i < s.length(); i++) {
            isExisting[s.charAt(i) - 'a'] = true;
        }

        for (int i = 0; i < isExisting.length; i++) {
            if (!isExisting[i]) {
                System.out.println(s + (char) ('a' + i));
                return;
            }
        }
    }
}
