package AtCoder.ABC.ABC239.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();
        final int d = scanner.nextInt();
        final Set<Integer> primes = new HashSet<>(sieveOfEratosthenes(200));
        final boolean canMakePrimeEverytime = IntStream.rangeClosed(a, b)
            .allMatch(i -> IntStream.rangeClosed(c, d)
                .anyMatch(primes::contains));
        if (canMakePrimeEverytime) {
            System.out.println("Aoki");
        } else {
            System.out.println("Takahashi");
        }
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
