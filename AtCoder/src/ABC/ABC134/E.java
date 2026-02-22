package ABC.ABC134;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> colors = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            final int next = scanner.nextInt();
            insert(colors, next);
        }

        System.out.println(colors.size());
    }

    private static void insert(final List<Integer> list, final int value) {
        if (list.isEmpty()) {
            list.add(value);
            return;
        }

        if (value <= list.get(0)) {
            list.add(0, value);
            return;
        }

        if (value > list.get(list.size() - 1)) {
            list.set(list.size() - 1, value);
            return;
        }

        int index;
        for (index = 0; index < list.size(); index++) {
            if (list.get(index) >= value) {
                break;
            }
        }
        list.set(index - 1, value);
    }
}
