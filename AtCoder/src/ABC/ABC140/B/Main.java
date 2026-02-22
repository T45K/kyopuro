package ABC.ABC140.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[] a = new int[n];
        final int[] b = new int[n];
        final int[] c = new int[n - 1];

        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt() - 1;
        }

        for (int i = 0; i < n; i++) {
            b[a[i]] = scanner.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            c[i] = scanner.nextInt();
        }

        int answer = 0;
        int previous = -2;
        for (int i = 0; i < n; i++) {
            final int index = a[i];
            if (index == previous + 1) {
                answer += c[index - 1];
            }

            previous = index;
            answer += b[index];
        }

        System.out.println(answer);
    }
}
