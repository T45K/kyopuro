package ABC133;

import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] dum = new int[n];

        int firstDum = 0;

        for (int i = 0; i < n; i++) {
            dum[i] = scanner.nextInt();

            if (i % 2 == 0) {
                firstDum += dum[i];
            } else {
                firstDum -= dum[i];
            }
        }

        System.out.print(firstDum);
        int dum2 = firstDum;
        for (int i = 0; i < n - 1; i++) {
            dum2 = 2*(dum[i] - dum2 / 2);
            System.out.print(" " + dum2);
        }
    }
}
