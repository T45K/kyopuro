package ABC161.F;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
数学 考察ができれば簡単
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long n = scanner.nextLong();

        if (n == 2) {
            System.out.println(1);
            return;
        }

        final long sqrt = (long) Math.sqrt(n);
        final Map<Long, Long> primes = primeFactorization(n - 1);
        long counter = 1; // none
        for (final long value : primes.values()) {
            counter *= value + 1;
        } // 約数の全ての組み合わせ(1含む)
        if (!primes.containsKey(2L)) {
            counter++; // 2 は必ず答えに含まれるので，約数になければ追加
        }
        if (isPrimeNumber(n - 1) && !primes.containsKey(n - 1)) {
            counter++;
        }

        for (long i = 3; i <= sqrt; i++) {
            if (n % i != 0) {
                continue;
            }

            long tmp = n / i;
            while (tmp % i == 0) {
                tmp /= i;
            }
            if (tmp % i == 1) {
                counter++;
            }
        }

        System.out.println(counter);
    }

    private static Map<Long, Long> primeFactorization(long n) {
        final double sqrt = Math.sqrt(n);
        final Map<Long, Long> countMap = new HashMap<>();
        for (long i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                countMap.compute(i, (k, v) -> v = v == null ? 1 : v + 1);
                n /= i;
                i--;
            }
        }

        if (n != 1) {
            countMap.compute(n, (k, v) -> v = v == null ? 1 : v + 1);
        }

        return countMap;
    }

    private static boolean isPrimeNumber(final long n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
