package ABC.ABC124.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char[] chars = scanner.nextLine().toCharArray();

        int resultA = 0;
        int resultB = 0;

        for (int i = 0; i < chars.length; i++) {
            if (i % 2 == 0) {
                if (chars[i] == '0') {
                    resultA++;
                } else {
                    resultB++;
                }
            } else {
                if (chars[i] == '1') {
                    resultA++;
                } else {
                    resultB++;
                }
            }
        }

        System.out.println(Math.min(resultA, resultB));
    }
}
