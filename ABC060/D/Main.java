package ABC060.D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int w = scanner.nextInt();

        final Map<Integer, List<Long>> items = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int wi = scanner.nextInt();
            final long vi = scanner.nextInt();
            items.computeIfAbsent(wi, v -> new ArrayList<>()).add(vi);
        }

        items.values().forEach(list -> list.sort(Comparator.reverseOrder()));

        final List<Integer> keys = new ArrayList<>(items.keySet());
        final long answer = recursive(keys, items, 0, w, 0);
        System.out.println(answer);
    }

    private static long recursive(final List<Integer> keys, final Map<Integer, List<Long>> items, final long value, final int w, final int index) {
        long maxValue = value;
        if (index < keys.size() - 1) {
            maxValue = Math.max(maxValue, recursive(keys, items, value, w, index + 1));
        }

        final int key = keys.get(index);
        final List<Long> values = items.get(key);
        long tmpValue = value;
        for (int i = 0; i < values.size() && key * (i + 1) <= w; i++) {
            tmpValue += values.get(i);
            if (index < keys.size() - 1) {
                maxValue = Math.max(maxValue, recursive(keys, items, tmpValue, w - key * (i + 1), index + 1));
            }
        }
        return Math.max(maxValue, tmpValue);
    }
}
