package ABC139.E;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final List[] combination = new List[n];

        for (int i = 0; i < n; i++) {
            combination[i] = new List();
            for (int j = 0; j < n - 1; j++) {
                combination[i].list.add(scanner.nextInt() - 1);
            }
        }

        int counter = 0;
        while (true) {
            counter++;

            final Set<Integer> skip = new HashSet<>();
            boolean doneOnceFlag = false;
            boolean doneAllFlag = true;
            for (int i = 0; i < n; i++) {
                if (skip.contains(i) || combination[i].list.isEmpty()) {
                    continue;
                }

                final Integer opponent = combination[i].list.get(0);

                if (skip.contains(opponent)) {
                    continue;
                }

                if (combination[opponent].list.get(0) == i) {
                    combination[i].list.remove(0);
                    combination[opponent].list.remove(0);
                    skip.add(i);
                    skip.add(opponent);
                    doneOnceFlag = true;
                }

                if (!(combination[i].list.isEmpty() && combination[opponent].list.isEmpty())) {
                    doneAllFlag = false;
                }
            }

            if (doneAllFlag) {
                System.out.println(counter);
                return;
            }

            if (!doneOnceFlag) {
                System.out.println(-1);
                return;
            }
        }
    }

    static class List {
        final java.util.List<Integer> list = new ArrayList<>();
    }
}
