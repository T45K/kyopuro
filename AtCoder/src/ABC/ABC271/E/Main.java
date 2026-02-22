package ABC.ABC271.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
やるだけ
まだ到着していない街からは移動できないことに注意
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int k = scanner.nextInt();

        final Route[] routes = Stream.generate(() -> new Route(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
            .limit(m)
            .toArray(Route[]::new);
        final int[] e = Stream.generate(scanner::nextInt)
            .limit(k)
            .mapToInt(Integer::intValue)
            .toArray();

        final Long[] cities = new Long[n + 1];
        cities[1] = 0L;
        for (final int v : e) {
            final Route route = routes[v - 1];
            if (cities[route.a] == null) {
                continue;
            }
            if (cities[route.b] != null) {
                cities[route.b] = Math.min(cities[route.b], cities[route.a] + route.c);
            } else {
                cities[route.b] = cities[route.a] + route.c;
            }
        }

        if (cities[n] != null) {
            System.out.println(cities[n]);
        } else {
            System.out.println(-1);
        }
    }

    private static class Route {
        final int a;
        final int b;
        final long c;

        Route(final int a, final int b, final long c) {
            this.a = a;
            this.b = b;
            this.c = c;
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
