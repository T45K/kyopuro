package AtCoder.ABC.ABC174.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
解説とは別の方法
優先度付きキューにそれぞれの値を入れて，商が大きいものをより多い回数割る様にする
最初からKの値を使うと間に合わないので，全ての丸太の長さの合計値に対する
丸太iの長さの割合から，丸太iの割る回数の初期値を決める
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .sorted()
            .collect(Collectors.toList());
        if (k == 0) {
            System.out.println(list.get(n - 1));
            return;
        }

        final long sum = list.stream()
            .mapToLong(Integer::longValue)
            .sum();

        final PriorityQueue<Log> queue = new PriorityQueue<>(Comparator.comparingDouble(log -> -(double) log.length / log.divCount));
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            final long value = list.get(i);
            final long div = value * k / sum;
            tmp += div;
            final Log log = new Log(value, 1 + div);
            queue.add(log);
        }

        final int rest = k - tmp;
        for (int i = 0; i < rest; i++) {
            final Log log = Optional.ofNullable(queue.poll()).orElseThrow();
            log.divCount++;
            queue.add(log);
        }
        final Log max = Optional.ofNullable(queue.poll()).orElseThrow();
        System.out.println((max.length + max.divCount - 1) / max.divCount);
    }

    private static class Log {
        final long length;
        long divCount;

        public Log(final long length, final long div) {
            this.length = length;
            this.divCount = div;
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

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
    