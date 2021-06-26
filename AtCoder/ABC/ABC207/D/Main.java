package AtCoder.ABC.ABC207.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO
public class Main {
    private static final Comparator<Pair> pairComparator = Comparator.comparingInt((Pair pair) -> pair.x)
        .thenComparing(pair -> pair.y);

    @SuppressWarnings("all")
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Pair> s = Stream.generate(() -> new Pair(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .sorted(pairComparator)
            .collect(Collectors.toList());
        final List<Pair> t = Stream.generate(() -> new Pair(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .sorted(pairComparator)
            .collect(Collectors.toList());

        if (n == 1) {
            System.out.println("Yes");
            return;
        }

        if (n == 2) {
            if (lengthPow(s.get(0), s.get(1)) == lengthPow(t.get(0), t.get(1))) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
            return;
        }

        final Pair sInit = s.get(0);
        final List<Cos> sCos = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            final Pair pair1 = s.get(i);
            for (int j = 1; j < n; j++) {
                final Pair pair2 = s.get(j);
                if (i == j) {
                    continue;
                }
                final int a = lengthPow(pair1, pair2);
                final int b = lengthPow(sInit, pair1);
                final int c = lengthPow(sInit, pair2);
                final double cos = (a - b - c) / (Math.sqrt(b) + Math.sqrt(c));
                sCos.add(new Cos(Math.max(b, c), Math.min(b, c), cos));
            }
        }

        Collections.sort(sCos, Comparator.comparing((Cos cos) -> cos.b)
            .thenComparing(cos -> cos.c)
            .thenComparing(cos -> cos.cos));

        for (int i = 0; i < n; i++) {
            final Pair tInit = t.get(i);
            final List<Cos> tCos = new ArrayList<>();
            for (int k = 0; k < n; k++) {
                if (i == k) {
                    continue;
                }
                final Pair pair1 = t.get(k);
                for (int j = 0; j < n; j++) {
                    final Pair pair2 = t.get(j);
                    if (i == j || k == j) {
                        continue;
                    }
                    final int a = lengthPow(pair1, pair2);
                    final int b = lengthPow(tInit, pair1);
                    final int c = lengthPow(tInit, pair2);
                    final double cos = (a - b - c) / (Math.sqrt(b) + Math.sqrt(c));
                    tCos.add(new Cos(Math.max(b, c), Math.min(b, c), cos));
                }
            }

            Collections.sort(tCos, Comparator.comparing((Cos cos) -> cos.b)
                .thenComparing(cos -> cos.c)
                .thenComparing(cos -> cos.cos));

            if (sCos.equals(tCos)) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
    }

    private static int lengthPow(final Pair a, final Pair b) {
        return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
    }

    private static boolean judge(final List<Pair> s, final List<Pair> t) {
        for (int i = -20; i <= 20; i++) {
            final int x = i;
            for (int j = -20; j <= 20; j++) {
                final int y = j;
                final List<Pair> tmp = t.stream()
                    .map(p -> new Pair(p.x + x, p.y + y))
                    .sorted(pairComparator)
                    .collect(Collectors.toList());
                if (s.equals(tmp)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class Cos {
        final int b;
        final int c; // b >= c
        final double cos;

        Cos(final int b, final int c, final double cos) {
            this.b = b;
            this.c = c;
            this.cos = cos;
        }

        @Override
        public int hashCode() {
            return b + c;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof Cos) {
                final Cos cos = (Cos) obj;
                return this.b == cos.b && this.c == cos.c && this.cos == cos.cos;
            }
            return false;
        }
    }

    private static class Pair {
        final int x;
        final int y;

        Pair(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return this.x * 11 + this.y;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof Pair) {
                final Pair pair = (Pair) obj;
                return this.x == pair.x && this.y == pair.y;
            }
            return false;
        }
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
}
