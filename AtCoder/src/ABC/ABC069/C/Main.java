package ABC.ABC069.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        int numOfFourMultiple = 0;
        int numOfTwoMultiple = 0;
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            if (a % 4 == 0) {
                numOfFourMultiple++;
            } else if (a % 2 == 0) {
                numOfTwoMultiple++;
            }
        }

        final int notFour = n - numOfFourMultiple - (numOfTwoMultiple >= 2 ? numOfTwoMultiple - 1 : 0);
        if (numOfFourMultiple >= notFour - 1) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
