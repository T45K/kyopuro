package AtCoder.ARC.ARC124.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
解説AC
約数列挙はそんなに重くない
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Pair<Integer, Integer>> list = Stream.generate(() -> new Pair<>(scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.toList());
        final long answer = enumerateDivisor(list.get(0).first).stream()
            .flatMapToLong(i -> enumerateDivisor(list.get(0).second).stream()
                .filter(j -> list.stream()
                    .allMatch(pair -> pair.first % i == 0 && pair.second % j == 0 || pair.first % j == 0 && pair.second % i == 0))
                .mapToLong(j -> (long) i * j / euclideanAlgorithm(i, j))
            )
            .max()
            .orElse(1);
        System.out.println(answer);
    }

    private static long euclideanAlgorithm(final long a, final long b) {
        if (b > a) {
            return euclideanAlgorithm(b, a);
        }

        if (b == 0) {
            return a;
        }

        return euclideanAlgorithm(b, a % b);
    }

    private static List<Integer> enumerateDivisor(final int value) {
        final List<Pair<Integer, Integer>> primeCounts = primeFactorization(value)
            .entrySet().stream()
            .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
        final List<Integer> list = new ArrayList<>();
        final BiConsumer<Integer, Integer> dfs = new BiConsumer<>() {
            @Override
            public void accept(final Integer index, final Integer value) {
                if (index == primeCounts.size()) {
                    list.add(value);
                    return;
                }

                final Pair<Integer, Integer> primCount = primeCounts.get(index);
                for (int i = 0, j = 1; i <= primCount.second; i++, j *= primCount.first) {
                    this.accept(index + 1, value * j);
                }
            }
        };
        dfs.accept(0, 1);
        return list;
    }

    private static Map<Integer, Integer> primeFactorization(int n) {
        final double sqrt = Math.sqrt(n);
        final Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                countMap.compute(i, (k, v) -> v = v == null ? 1 : v + 1);
                n /= i;
                i--;
            }
        }

        if (n != 1) {
            countMap.compute(n, (k, v) -> v = v == null ? 1 : v + 1);
        }

        return countMap;
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
