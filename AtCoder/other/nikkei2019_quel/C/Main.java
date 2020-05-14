package AtCoder.other.nikkei2019_quel.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
ゲーム理論 自分の利益と相手への妨害を考えると，ペアの和が大きい順に取っていくのがお互いに最適
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Pair> list = IntStream.range(0, n)
                .mapToObj(i -> new Pair(scanner.nextLong(), scanner.nextLong()))
                .sorted(Comparator.comparingLong(pair -> -(pair.t + pair.a)))
                .collect(Collectors.toList());

        final long answer = IntStream.range(0, n)
                .filter(i -> i % 2 == 0)
                .mapToLong(i -> list.get(i).t)
                .sum()
                - IntStream.range(0, n)
                .filter(i -> i % 2 == 1)
                .mapToLong(i -> list.get(i).a)
                .sum();

        System.out.println(answer);
    }

    private static class Pair {
        final long t;
        final long a;

        Pair(final long t, final long a) {
            this.t = t;
            this.a = a;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
    