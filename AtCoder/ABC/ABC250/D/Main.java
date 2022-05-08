package AtCoder.ABC.ABC250.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        final List<Long> primeNumbers = sieveOfEratosthenes((int) Math.min(n, 1_000_000)).stream()
            .map(i -> (long) i)
            .collect(Collectors.toList());
        long count = 0;
        for (final long primeNumber : primeNumbers) {
            final long pow = primeNumber * primeNumber * primeNumber;
            if (pow >= n) {
                break;
            }
            final long div = Math.min(n / pow, primeNumber - 1);
            final int index = Collections.binarySearch(primeNumbers, div);
            count += index >= 0 ? (index + 1) : ~index;
        }
        System.out.println(count);
    }

    private static List<Integer> sieveOfEratosthenes(final int number) {
        final boolean[] isPrimeNumber = new boolean[number + 1];
        Arrays.fill(isPrimeNumber, true);
        final int sqrt = (int) Math.sqrt(number);
        for (int i = 2; i <= sqrt; i++) {
            if (!isPrimeNumber[i]) {
                continue;
            }
            for (int j = 2; i * j <= number; j++) {
                isPrimeNumber[i * j] = false;
            }
        }
        return IntStream.rangeClosed(2, number)
            .filter(i -> isPrimeNumber[i])
            .boxed()
            .collect(Collectors.toList());
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
