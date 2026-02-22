package ABC.ABC019.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[] distancesFrom1 = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            System.out.println("? 1 " + i);
            final int distance = scanner.nextInt();
            distancesFrom1[i] = distance;
        }

        int max = 0;
        int index = 0;
        for (int i = 2; i <= n; i++) {
            if (distancesFrom1[i] > max) {
                max = distancesFrom1[i];
                index = i;
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (i == index) {
                continue;
            }

            System.out.println("? " + index + " " + i);
            final int distance = scanner.nextInt();
            answer = Math.max(answer, distance);
        }

        System.out.println("! " + answer);
    }
}
