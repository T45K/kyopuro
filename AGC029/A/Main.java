package AGC029.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char[] s = scanner.nextLine().toCharArray();
        long result = 0;
        long whiteCount = 0;
        for (int i = s.length - 1; i >= 0; i--) {
            if (s[i] == 'W') {
                whiteCount++;
            } else {
                result += whiteCount;
            }
        }

        System.out.println(result);
    }
}
