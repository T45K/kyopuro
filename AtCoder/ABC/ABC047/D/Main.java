package AtCoder.ABC.ABC047.D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        scanner.nextInt();

        int min = Integer.MAX_VALUE;
        int maxDiff = -1;
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            min = Math.min(min, a);
            maxDiff = Math.max(maxDiff, a - min);
            list.add(a);
        }

        final Set<Integer> set = new HashSet<>(list);
        int counter = 0;
        for (final int value : list) {
            set.remove(value);
            if (set.contains(value + maxDiff)) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
