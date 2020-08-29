package AtCoder.ABC.ABC177.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final String PAIRWISE_COPRIME = "pairwise coprime";
    private static final String SETWISE_COPRIME = "setwise coprime";
    private static final String NOT_COPRIME = "not coprime";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());

        final int gcd = list.stream()
            .mapToInt(Integer::intValue)
            .reduce(Main::euclideanAlgorithm)
            .orElseThrow();

        if (gcd > 1) {
            System.out.println(NOT_COPRIME);
            return;
        }

        final List<Integer> primes = sieveOfEratosthenes();
        final Set<Integer> alreadyUsed = new HashSet<>();
        for (int value : list) {
            for (final int prime : primes) {
                if (value % prime != 0) {
                    continue;
                }

                while (value % prime == 0) {
                    value /= prime;
                }
                if (alreadyUsed.contains(prime)) {
                    System.out.println(SETWISE_COPRIME);
                    return;
                }
                alreadyUsed.add(prime);
            }
            if (value != 1) {
                if (alreadyUsed.contains(value)) {
                    System.out.println(SETWISE_COPRIME);
                    return;
                }
                alreadyUsed.add(value);
            }
        }
        System.out.println(PAIRWISE_COPRIME);
    }

    private static int euclideanAlgorithm(final int a, final int b) {
        if (b > a) {
            return euclideanAlgorithm(b, a);
        }

        if (b == 0) {
            return a;
        }

        return euclideanAlgorithm(b, a % b);
    }

    private static List<Integer> sieveOfEratosthenes() {
        List<Integer> numbers = IntStream.rangeClosed(2, 1000)
            .boxed()
            .collect(Collectors.toList());

        final int sqrt = (int) Math.sqrt(1000);
        final List<Integer> primeNumbers = new ArrayList<>();
        int condition;

        do {
            final int prime = numbers.get(0);
            primeNumbers.add(prime);
            numbers = numbers.stream()
                .filter(i -> i % prime != 0)
                .collect(Collectors.toList());
            condition = prime;
        } while (condition < sqrt);

        primeNumbers.addAll(numbers);
        return primeNumbers;
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
