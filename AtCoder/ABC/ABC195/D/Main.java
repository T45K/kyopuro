package AtCoder.ABC.ABC195.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int q = scanner.nextInt();
        final List<Baggage> baggages = IntStream.range(0, n)
            .mapToObj(i -> new Baggage(scanner.nextInt(), scanner.nextInt()))
            .sorted(Comparator.comparingInt(b -> -b.v))
            .collect(Collectors.toList());
        final List<Box> boxes = IntStream.rangeClosed(1, m)
            .mapToObj(i -> new Box(i, scanner.nextInt()))
            .sorted(Comparator.comparingInt(b -> b.x))
            .collect(Collectors.toList());
        for (int i = 0; i < q; i++) {
            final boolean[] isFilled = new boolean[m + 1];
            final int l = scanner.nextInt();
            final int r = scanner.nextInt();
            for (int j = l; j <= r; j++) {
                isFilled[j] = true;
            }
            final long answer = baggages.stream()
                .mapToLong(baggage -> {
                    final Optional<Box> availableBox = boxes.stream()
                        .filter(box -> box.x < baggage.w || isFilled[box.number])
                        .findFirst();
                    if (availableBox.isPresent()) {
                        isFilled[availableBox.get().number] = true;
                        return baggage.v;
                    } else {
                        return 0;
                    }
                }).sum();
            System.out.println(answer);
        }
    }

    private static class Baggage {
        final int w;
        final int v;

        Baggage(final int w, final int v) {
            this.w = w;
            this.v = v;
        }
    }

    private static class Box {
        final int number;
        final int x;

        Box(final int number, final int x) {
            this.number = number;
            this.x = x;
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
