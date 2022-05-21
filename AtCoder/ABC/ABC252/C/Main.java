package AtCoder.ABC.ABC252.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[][] reels = Stream.generate(() -> {
                final String s = scanner.next();
                return IntStream.range(0, 10)
                    .map(i -> s.charAt(i) - '0')
                    .toArray();
            })
            .limit(n)
            .toArray(size -> new int[size][10]);
        final int answer = IntStream.rangeClosed(0, 9)
            .map(i -> calc(reels, i))
            .min()
            .orElseThrow();
        System.out.println(answer);
    }

    private static int calc(final int[][] reels, final int target) {
        return Arrays.stream(reels)
            .map(reel -> indexOf(reel, target))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .map(entry -> (entry.getValue() - 1) * 10 + entry.getKey())
            .mapToInt(Long::intValue)
            .max()
            .orElseThrow();
    }

    private static int indexOf(final int[] array, final int target) {
        return IntStream.range(0, array.length)
            .filter(i -> array[i] == target)
            .findFirst()
            .orElseThrow();
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
