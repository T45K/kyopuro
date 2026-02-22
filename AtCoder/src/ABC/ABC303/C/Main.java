package ABC.ABC303.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        scanner.nextInt();
        final int m = scanner.nextInt();
        final int h = scanner.nextInt();
        final int k = scanner.nextInt();
        final String s = scanner.next();
        final Map<Pair<Integer, Integer>, Integer> items = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();
            items.compute(new Pair<>(x, y), (ignored, v) -> v == null ? 1 : v + 1);
        }

        int health = h;
        int curX = 0;
        int curY = 0;
        for (final char c : s.toCharArray()) {
            health--;
            if (health < 0) {
                System.out.println("No");
                return;
            }

            switch (c) {
                case 'R':
                    curX++;
                    break;
                case 'L':
                    curX--;
                    break;
                case 'U':
                    curY++;
                    break;
                case 'D':
                    curY--;
                    break;
            }

            final Pair<Integer, Integer> point = new Pair<>(curX, curY);
            if (items.getOrDefault(point, 0) > 0 && health < k) {
                health = k;
                items.compute(point, (ignored, v) -> v - 1);
            }
        }
        System.out.println("Yes");
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static class Pair<T1, T2> {
        final T1 first;
        final T2 second;

        Pair(final T1 first, final T2 second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
