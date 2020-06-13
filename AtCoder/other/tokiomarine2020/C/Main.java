package AtCoder.other.tokiomarine2020.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
考察
実はいもす法をK回やるまでにカンストを迎えるからそこで答えを出すと間に合うというパターン
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = scanner.nextInt();
        }

        for (int count = 0; count < k; count++) {
            final int[] next = new int[n + 2];
            for (int i = 1; i <= n; i++) {
                final int left = Math.max(i - array[i], 1);
                final int right = Math.min(i + array[i] + 1, n + 1);
                next[left]++;
                next[right]--;
            }
            for (int i = 2; i <= n; i++) {
                next[i] += next[i - 1];
            }

            if (Arrays.stream(array, 1, n + 1).allMatch(i -> i == n)) {
                final String answer = Arrays.stream(array, 1, n + 1)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(" "));
                System.out.println(answer);
                return;
            }
            array = next;
        }

        final String answer = Arrays.stream(array, 1, n + 1)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining(" "));
        System.out.println(answer);
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
