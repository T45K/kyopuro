package AtCoder.ABC.ABC210.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
解説AC
最小全域木
ユーザ解説がめちゃくちゃ分かりやすい
https://blog.hamayanhamayan.com/entry/2021/07/17/233253
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<Operation> operations = Stream.generate(() -> new Operation(scanner.nextInt(), scanner.nextInt()))
            .limit(m)
            .sorted(Comparator.comparingLong(o -> o.cost))
            .collect(Collectors.toList());

        final long answer = recursive(operations, 0, n, 0);
        System.out.println(answer);
    }

    private static long recursive(final List<Operation> operations, final int index, final long connected, final long costSum) {
        if (connected == 1) {
            return costSum;
        }

        if (index == operations.size()) {
            return -1;
        }

        final Operation operation = operations.get(index);
        final long gcd = euclideanAlgorithm(connected, operation.length);
        return recursive(operations, index + 1, gcd, costSum + (connected - gcd) * operation.cost);
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

    private static class Operation {
        final int length;
        final long cost;

        Operation(final int length, final long cost) {
            this.length = length;
            this.cost = cost;
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
