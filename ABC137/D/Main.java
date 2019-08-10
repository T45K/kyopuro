package ABC137.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            final int days = scanner.nextInt();
            final int value = scanner.nextInt();
            pairs.add(new Pair(days, value));
        }

        pairs.sort((o1, o2) -> o2.getValue() - o1.getValue());

        final List<Integer> checked = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            checked.add(i);
        }

        int res = 0;
        for (final Pair pair : pairs) {
            int days = pair.getDays() - 1;

            final int index = extendedBinarySearch(checked, days);
            if (index != checked.size()) {
                checked.remove(index);
                res += pair.getValue();
            }
        }

        System.out.println(res);
    }

    static class Pair {
        private final int days;
        private final int value;

        Pair(final int days, final int value) {
            this.days = days;
            this.value = value;
        }

        int getDays() {
            return days;
        }

        int getValue() {
            return value;
        }
    }

    @SuppressWarnings("unchecked")
    private static int extendedBinarySearch(final Object indexes, final int value) {
        final int rawIndex;
        if (indexes instanceof int[]) {
            rawIndex = Arrays.binarySearch((int[]) indexes, value);
        } else {
            rawIndex = Collections.binarySearch((List<Integer>) indexes, value);
        }

        if (rawIndex >= 0) {
            return rawIndex;
        }

        return -(rawIndex + 1);
    }
}
