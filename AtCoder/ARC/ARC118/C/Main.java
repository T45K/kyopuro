package AtCoder.ARC.ARC118.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final int UPPER = 10_000;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> primes = sieveOfEratosthenes();
        final List<Integer> list = new ArrayList<>(n);
        list.add(2 * 3);
        list.add(2 * 5);
        list.add(3 * 5);
        final Set<Integer> exists = new HashSet<>(list);
        for (int i = 0; i < n; i++) {
            final int value = list.get(i);
            for (final int prime : primes) {
                if (list.size() >= n) {
                    break;
                }
                if (value * prime <= UPPER && !exists.contains(value * prime)) {
                    list.add(value * prime);
                    exists.add(value * prime);
                }
            }
        }
        System.out.println(list.stream().map(Objects::toString).collect(Collectors.joining(" ")));
    }

    private static List<Integer> sieveOfEratosthenes() {
        final boolean[] isPrimeNumber = new boolean[UPPER + 1];
        Arrays.fill(isPrimeNumber, true);
        final int sqrt = (int) Math.sqrt(UPPER);
        for (int i = 2; i <= sqrt; i++) {
            if (!isPrimeNumber[i]) {
                continue;
            }
            for (int j = 2; i * j <= UPPER; j++) {
                isPrimeNumber[i * j] = false;
            }
        }
        return IntStream.rangeClosed(2, UPPER)
            .filter(i -> isPrimeNumber[i])
            .boxed()
            .collect(Collectors.toList());
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
    