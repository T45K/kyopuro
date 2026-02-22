package ABC.ABC136.D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char[] chars = scanner.nextLine().toCharArray();
        final int[] answer = new int[chars.length];

        final List<Integer> rIndexes = new ArrayList<>();
        final List<Integer> lIndexes = new ArrayList<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'R') {
                rIndexes.add(i);
            } else {
                lIndexes.add(i);
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'R') {
                final int lIndex = findLIndex(i, lIndexes);
                if ((lIndex - i) % 2 == 0) {
                    answer[lIndex]++;
                } else {
                    answer[lIndex - 1]++;
                }
            } else {
                final int rIndex = findRIndex(i, rIndexes);
                if ((i - rIndex) % 2 == 0) {
                    answer[rIndex]++;
                } else {
                    answer[rIndex + 1]++;
                }
            }
        }

        for (final int i : answer) {
            System.out.print(i + " ");
        }

    }

    private static int findRIndex(final int index, final List<Integer> rIndexes) {
        final int i = -(Collections.binarySearch(rIndexes, index) + 2);
        return rIndexes.get(i);
    }

    private static int findLIndex(final int index, final List<Integer> lIndexes) {
        final int i = -(Collections.binarySearch(lIndexes, index) + 1);
        return lIndexes.get(i);
    }
}
