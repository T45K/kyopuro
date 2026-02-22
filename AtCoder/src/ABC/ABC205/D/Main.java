package ABC.ABC205.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
にぶたんを二回する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final List<Long> list = Stream.generate(scanner::nextLong)
            .limit(n)
            .collect(Collectors.toList());
        final String answer = IntStream.range(0, q)
            .mapToLong(i -> {
                final long k = scanner.nextLong();
                return binarySearch(0, (long) 2e18, list, k);
            })
            .mapToObj(Long::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static long binarySearch(final long begin, final long end, final List<Long> list, final long target) {
        final long mid = (begin + end) / 2;
        final int index = Collections.binarySearch(list, mid);
        final long calibratedIndex = mid - (index >= 0 ? index + 1 : ~index);
        if (index < 0 && calibratedIndex == target) {
            return mid;
        } else if (calibratedIndex >= target) {
            return binarySearch(begin, mid, list, target);
        } else {
            return binarySearch(mid, end, list, target);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
