package ABC072.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[] array = new int[100_001];
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            if (a != 0) {
                array[a - 1]++;
            }
            array[a]++;
            array[a + 1]++;
        }

        int max = -1;
        for (final int value : array) {
            if (value > max) {
                max = value;
            }
        }

        System.out.println(max);
    }
}
