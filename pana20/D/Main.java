package pana20.D;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final List<String> list = new LinkedList<>();
        final char[] array = new char[n];
        recursive(0, array, list, 0);
        for (final String s : list) {
            System.out.println(s);
        }
    }

    private static void recursive(final int index, final char[] array, final List<String> list, final int max) {
        if (index == array.length) {
            list.add(new String(array));
            return;
        }

        for (int i = 0; i < index + 1 && i <= max + 1; i++) {
            array[index] = (char) ('a' + i);
            recursive(index + 1, array, list, Math.max(i, max));
        }
    }
}
