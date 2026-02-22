package ABC.ABC094.C;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[n];
        final int[] sortedArray = new int[n];

        for (int i = 0; i < n; i++) {
            final int tmp = scanner.nextInt();
            array[i] = tmp;
            sortedArray[i] = tmp;
        }

        Arrays.sort(sortedArray);
        final int formerMedian = sortedArray[(n - 1) / 2];
        final int latterMedian = sortedArray[n / 2];

        for (final int value : array) {
            if (value <= formerMedian) {
                System.out.println(latterMedian);
            } else {
                System.out.println(formerMedian);
            }
        }
    }
}
