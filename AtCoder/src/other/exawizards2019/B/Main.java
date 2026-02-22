package other.exawizards2019.B;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        final char[] hats = scanner.nextLine().toCharArray();
        int r = 0;

        for (final char hat : hats) {
            if (hat == 'R') {
                r++;
            }
        }

        if (r * 2 > hats.length) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        scanner.close();
    }
}
