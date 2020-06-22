package AtCoder.ABC.ABC171.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
modをごちゃごちゃするだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());
        final int[] array = new int[n];
        if (n % 2 == 0) {
            final int all = list.stream().reduce((a, b) -> a ^ b).orElse(0);
            for (int i = 0; i < n; i++) {
                array[i] = all ^ list.get(i);
            }
        } else {
            final int all = list.stream().limit(n - 1).reduce((a, b) -> a ^ b).orElse(0);
            for (int i = 0; i < n - 1; i++) {
                array[i] = all ^ list.get(i);
            }
            array[n - 1] = all;
        }

        final String answer = Arrays.stream(array).mapToObj(Integer::toString).collect(Collectors.joining(" "));
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
