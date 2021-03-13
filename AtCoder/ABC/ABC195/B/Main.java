package AtCoder.ABC.ABC195.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.OptionalInt;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Main {
    private static final String UNSATISFIABLE = "UNSATISFIABLE";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int w = scanner.nextInt() * 1000;
        final Supplier<IntStream> divStreamSupplier = () ->
            IntStream.rangeClosed(1, w)
                .mapToObj(i -> (double) w / i)
                .filter(div -> a <= div && div <= b)
                .mapToInt(Double::intValue);
        final OptionalInt min = divStreamSupplier.get().min();
        final OptionalInt max = divStreamSupplier.get().max();
        if (min.isEmpty() || max.isEmpty()) {
            System.out.println(UNSATISFIABLE);
        } else {
            System.out.println(min.getAsInt() + " " + max.getAsInt());
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
