package ABC132.A;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();
        final int[] counter = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'A']++;
        }

        for (final int value : counter) {
            if (value != 0 && value != 2) {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }
}
