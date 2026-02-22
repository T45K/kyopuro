package ABC.ABC201.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Mountain> list = Stream.generate(() -> new Mountain(scanner.next(), scanner.nextInt()))
            .limit(n)
            .sorted(Comparator.comparingInt(Mountain::getHeight).reversed())
            .limit(2)
            .collect(Collectors.toList());
        System.out.println(list.get(1).name);
    }

    private static class Mountain {
        final String name;
        final int height;

        Mountain(final String name, final int height) {
            this.name = name;
            this.height = height;
        }

        int getHeight() {
            return this.height;
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
