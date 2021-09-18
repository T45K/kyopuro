package AtCoder.ABC.ABC219.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String x = scanner.next();
        final int[] base = new int[26];
        for (int i = 0; i < x.length(); i++) {
            base[x.charAt(i) - 'a'] = i;
        }
        final int n = scanner.nextInt();
        final String answer = Stream.generate(scanner::next)
            .limit(n)
            .sorted(Comparator.comparing((String s) -> IntStream.range(0, s.length())
                .map(i -> base[s.charAt(i) - 'a'])
                .mapToObj(v -> Character.toString(v + 'a'))
                .collect(Collectors.joining())))
            .collect(Collectors.joining("\n"));
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
