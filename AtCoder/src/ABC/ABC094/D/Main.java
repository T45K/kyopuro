package ABC.ABC094.D;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        Arrays.sort(array);
        final int a = array[n - 1];
        int b = array[0];

        for (int i = 1; i < n - 1; i++) {
            if (a % 2 == 0) {
                if (Math.abs(a / 2 - array[i]) < Math.abs(a / 2 - b)) {
                    b = array[i];
                }
            } else if (Math.abs(a / 2 - array[i]) < Math.abs(a / 2 - b) || Math.abs(a / 2 + 1 - array[i]) < Math.abs(a / 2 + 1 - b)) {
                b = array[i];
            }
        }

        System.out.println(a + " " + b);
    }
}
