package ABC142.E;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// TODO fix
public class Main {
    private static int[] locks;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final List<Key> keys = new ArrayList<>();
        locks = new int[n + 1];
        locks[0] = 1;
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            final Key key = new Key();
            key.price = a;
            for (int j = 0; j < b; j++) {
                final int lock = scanner.nextInt();
                key.locks.add(lock);
                locks[lock]++;
            }
            keys.add(key);
        }

        for (final int lock : locks) {
            if (lock == 0) {
                System.out.println(-1);
                return;
            }
        }

        keys.sort(Comparator.comparingInt(key -> key.price));

        System.out.println(recursive(keys.size() - 1, keys));
    }

    private static int recursive(final int index, final List<Key> keys) {
        if (index == -1) {
            return getPrice(keys);
        }

        final int a = recursive(index - 1, keys);

        final Key key = keys.get(index);
        for (final int lock : key.locks) {
            if (locks[lock] == 1) {
                return a;
            }
        }

        for (final int lock : key.locks) {
            locks[lock]--;
        }

        keys.remove(key);

        final int b = recursive(index - 1, keys);

        for (final int lock : key.locks) {
            locks[lock]++;
        }

        keys.add(index, key);

        return Math.min(a, b);
    }

    private static int getPrice(final List<Key> keys) {
        return keys.stream()
                .map(key -> key.price)
                .mapToInt(Integer::intValue)
                .sum();
    }

    static class Key {
        int price;
        List<Integer> locks = new ArrayList<>();
    }
}
