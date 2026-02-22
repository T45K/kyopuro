package AGC.AGC039.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String line = scanner.nextLine();
        final long k = scanner.nextLong();

        if (all(line)) {
            System.out.println(line.length() * k / 2);
            return;
        }

        long cost = 0;
        for (int i = 0; i < line.length() - 1; i++) {
            if (line.charAt(i) != line.charAt(i + 1)) {
                continue;
            }

            cost++;
            i++;
        }

        cost *= k;

        if (line.charAt(0) == line.charAt(line.length() - 1)) {
            final char begin = line.charAt(0);
            int a;
            for (a = 1; a < line.length() && line.charAt(a) == begin; a++) ;

            final char end = line.charAt(line.length() - 1);
            int b;
            for (b = 1; b < line.length() && line.charAt(line.length() - 1 - b) == end; b++) ;

            cost += (k - 1) * ((a + b) / 2 - a / 2 - b / 2);
        }

        System.out.println(cost);
    }

    private static boolean all(final String string) {
        final char c = string.charAt(0);
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) != c) {
                return false;
            }
        }
        return true;
    }
}
