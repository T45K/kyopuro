package ABC087.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] array1 = new int[n];
        final int[] array2 = new int[n];

        for (int i = 0; i < n; i++) {
            array1[i] = scanner.nextInt();
        }

        int sum1 = 0;
        for (int i = 0; i < n; i++) {
            final int tmp = scanner.nextInt();
            array2[i] = tmp;
            sum1 += tmp;
        }

        int answer = 0;
        int sum2 = 0;
        for (int i = 0; i < n; i++) {
            sum2 += array1[i];
            answer = Math.max(answer, sum1 + sum2);
            sum1 -= array2[i];
        }

        System.out.println(answer);
    }
}
