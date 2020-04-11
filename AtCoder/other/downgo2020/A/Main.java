package downgo2020.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final String[] names = new String[n];
        final int[] times = new int[n];

        for (int i = 0; i < n; i++) {
            names[i] = scanner.next();
            times[i] = scanner.nextInt();
        }

        final String name = scanner.next();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (name.equals(names[i])) {
                for (int j = n - 1; j > i; j--) {
                    count += times[j];
                }

                System.out.println(count);
                return;
            }
        }
    }
}
