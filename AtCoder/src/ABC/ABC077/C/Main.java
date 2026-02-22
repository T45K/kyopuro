package ABC.ABC077.C;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        Arrays.sort(a);

        final int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        Arrays.sort(b);
        final long[] bCount = new long[n];
        for (int i = 0; i < n; i++) {
            bCount[i] = binarySearch(a, 0, n - 1, b[i]) + 1;
            if (i != 0) {
                bCount[i] += bCount[i - 1];
            }
        }

        long counter = 0;
        for (int i = 0; i < n; i++) {
            final int c = scanner.nextInt();
            final int bi = binarySearch(b, 0, n - 1, c);
            if (bi == -1) {
                continue;
            }
            counter += bCount[bi];
        }

        System.out.println(counter);
    }

    // targetと比較して真に小さい最大の値を探す
    private static int binarySearch(final int[] array, final int begin, final int end, final int target) {
        if (end - begin <= 1) {
            if (array[end] < target) {
                return end;
            }
            if (array[begin] < target) {
                return begin;
            }
            return begin - 1;
        }
        final int mid = (begin + end) / 2;
        if (target > array[mid]) {
            return binarySearch(array, mid, end, target);
        } else {
            return binarySearch(array, begin, mid, target);
        }
    }
}

/*
1 3 5 7 9

5の時
mid=5

6
 */