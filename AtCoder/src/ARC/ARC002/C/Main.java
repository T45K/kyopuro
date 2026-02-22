package ARC.ARC002.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private static final char[] array = {'A', 'B', 'X', 'Y'};

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final char[] c = scanner.next().toCharArray();
        final int answer = charStream().flatMap(c1 ->
            charStream().flatMap(c2 ->
                charStream().flatMap(c3 ->
                    charStream().map(c4 -> {
                        int tmp = 0;
                        for (int i = 0; i < n; i++) {
                            tmp++;
                            if (i < n - 1 && (c[i] == c1 && c[i + 1] == c2 || c[i] == c3 && c[i + 1] == c4)) {
                                i++;
                            }
                        }
                        return tmp;
                    }))))
            .mapToInt(Integer::intValue)
            .min()
            .orElse(1);
        System.out.println(answer);
    }

    private static Stream<Character> charStream() {
        return IntStream.range(0, Main.array.length)
            .mapToObj(i -> Main.array[i]);
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
