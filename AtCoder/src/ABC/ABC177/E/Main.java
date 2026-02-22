package ABC.ABC177.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
not coprimeかどうかの判定は簡単
Aの最大値が10^6なので，10^3までの素数を用意しておく(200個弱)
各Aを各素数で割っていき，割れた素数を記録しておく
割れた素数が被ったら互いに素でない組が存在する
 */
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
        final Function<Integer, Boolean> isAddable = value -> {
            if (alreadyUsed.contains(value)) {
                System.out.println(SETWISE_COPRIME);
                return false;
            }
            alreadyUsed.add(value);
            return true;
        };
        for (int value : list) {
            for (final int prime : primes) {
                if (value % prime != 0) {
                    continue;
                }

                while (value % prime == 0) {
                    value /= prime;
                }
                if (!isAddable.apply(prime)) {
                    return;
                }
            }
            if (value != 1) {
                if (!isAddable.apply(value)) {
                    return;
                }
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
