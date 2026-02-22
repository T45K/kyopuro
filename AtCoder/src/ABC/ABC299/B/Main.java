package ABC.ABC299.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int t = scanner.nextInt();
        final List<Integer> c = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Integer> r = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());
        final List<Player> players = IntStream.range(0, n)
            .mapToObj(i -> new Player(i + 1, c.get(i), r.get(i)))
            .collect(Collectors.toList());
        final int targetColor = c.contains(t) ? t : c.get(0);
        final Integer answer = players.stream()
            .filter(i -> i.color == targetColor)
            .max(Comparator.comparing(p -> p.value))
            .map(p -> p.index)
            .orElseThrow();
        System.out.println(answer);
    }

    private static class Player {
        final int index;
        final int color;
        final int value;

        Player(final int index, final int color, final int value) {
            this.index = index;
            this.color = color;
            this.value = value;
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
