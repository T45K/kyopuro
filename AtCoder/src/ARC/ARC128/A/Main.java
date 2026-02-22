package ARC.ARC128.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();

        final StringJoiner joiner = new StringJoiner(" ");
        boolean isGold = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (isGold && array[i] > array[i + 1]) {
                joiner.add("1");
                isGold = false;
            } else if (!isGold && array[i] < array[i + 1]) {
                joiner.add("1");
                isGold = true;
            } else {
                joiner.add("0");
            }
        }

        if (isGold) {
            joiner.add("0");
        } else {
            joiner.add("1");
        }

        System.out.println(joiner);
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
