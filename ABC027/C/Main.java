package ABC027.C;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final char[] array = new char[n];
        final List<String> list = new LinkedList<>();

        recursive(0, array, list);
        for (final String s : list) {
            System.out.println(s);
        }
    }

    private static void recursive(final int index, final char[] array, final List<String> list) {
        if (index == array.length) {
            list.add(new String(array));
            return;
        }

        array[index] = 'a';
        recursive(index + 1, array, list);
        array[index] = 'b';
        recursive(index + 1, array, list);
        array[index] = 'c';
        recursive(index + 1, array, list);
    }
}
