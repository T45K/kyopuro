package AtCoder.ABC.ABC162.E;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO solve
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        long sum = 0;
        final List<Integer> primes = sieveOfEratosthenes(k);
        final long[] array = new long[k + 1];
        for (int i = k; i >= 1; i--) {
            final int nums = k / i;
            final long multi = iterativePow(nums, n);

            long tmp = 0;
            for (final int prime : primes) {
                if (i * prime > k) {
                    break;
                }
                tmp += array[i * prime];
                tmp %= MOD;
            }

            final long diff = (multi + MOD - tmp) % MOD;
            sum += diff * i % MOD;
            sum %= MOD;
            array[i] = multi;
        }
        System.out.println(sum);
    }

    private static long iterativePow(long a, long b) {
        if (a == 1) {
            return 1;
        }
        long tmp = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                tmp *= a;
            }
            a *= a;
            b >>= 1;
            tmp %= MOD;
        }

        return tmp;
    }


    private static List<Integer> sieveOfEratosthenes(final int number) {
        List<Integer> numbers = IntStream.rangeClosed(2, number)
                .boxed()
                .collect(Collectors.toList());

        final int sqrt = (int) Math.sqrt(number);
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
}
