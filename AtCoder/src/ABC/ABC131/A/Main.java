package ABC.ABC131.A;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();

        char before = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (before == s.charAt(i)) {
                System.out.println("Bad");
                return;
            }

            before = s.charAt(i);
        }

        System.out.println("Good");
    }
}
