package ABC.ABC151.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final boolean[] acArray = new boolean[n + 1];
        final int[] waArray = new int[n + 1];
        for (int i = 0; i < m; i++) {
            final int p = scanner.nextInt();
            final String result = scanner.next();
            if (acArray[p]) {
                continue;
            }

            if ("WA".equals(result)) {
                waArray[p]++;
            } else {
                acArray[p] = true;
            }
        }

        int acceptedCount = 0;
        int wrongAnswerCount = 0;
        for (int i = 1; i < n + 1; i++) {
            if (!acArray[i]) {
                continue;
            }

            acceptedCount++;
            wrongAnswerCount += waArray[i];
        }

        System.out.println(acceptedCount + " " + wrongAnswerCount);
    }
}
