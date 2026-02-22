package ABC.ABC064.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
        final char[] array = scanner.next().toCharArray();

        int leftCount = 0;
        int rightCount = 0;
        for (final char c : array) {
            if (c == ')') {
                if (leftCount > 0) {
                    leftCount--;
                } else {
                    rightCount++;
                }
            } else {
                leftCount++;
            }
        }

        for (int i = 0; i < rightCount; i++) {
            System.out.print('(');
        }
        for (final char c : array) {
            System.out.print(c);
        }
        for (int i = 0; i < leftCount; i++) {
            System.out.print(')');
        }
    }
}
