package AtCoder.ABC.ABC153.C;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        Arrays.sort(array);

        long sum = 0;
        for (int i = 0; i < n - k; i++) {
            sum += array[i];
        }

        System.out.println(sum);
    }
}
