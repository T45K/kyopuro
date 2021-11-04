package AtCoder.ABC.ABC146.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
累積和を取り，indexとその時点での累積和の差分を取る
これが0の時，初期位置からi個取った時に，その和の余りはiになることになる
あとは初期位置をどんどんずらしていき，それに合わせて差分を調節していく
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = (array[i - 1] + scanner.nextInt()) % k;
        }

        final BinaryOperator<Integer> modSub = (a, b) -> (a - b + k) % k;
        final UnaryOperator<Integer> calcDiff = i -> modSub.apply(i, array[i]);
        final Map<Integer, Long> counts = IntStream.range(0, Math.min(k - 1, n + 1))
            .mapToObj(calcDiff::apply)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long sum = 0;
        for (int i = 0; i < n; i++) {
            final int addedIndex = i + k - 1;
            if (addedIndex <= n) {
                counts.compute(calcDiff.apply(addedIndex), (key, value) -> value == null ? 1 : value + 1);
            }
            final int diff = calcDiff.apply(i);
            counts.compute(diff, (key, value) -> value == null ? 0 : value - 1);
            sum += Optional.ofNullable(counts.get(diff)).orElse(0L);
        }
        System.out.println(sum);
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
