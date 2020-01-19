package ABC152.E;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    private static final long MOD = (long) 1e9 + 7;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[] array = new int[n];
        final Map<Integer, Integer> primeCounter = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            array[i] = a;
            final Map<Integer, Integer> primeFactorization = primeFactorization(a);
            for (final int key : primeFactorization.keySet()) {
                primeCounter.put(key, Math.max(primeFactorization.get(key), primeCounter.getOrDefault(key, 0)));
            }
        }

        long lcm = 1;
        for (final Map.Entry<Integer, Integer> primeAndCount : primeCounter.entrySet()) {
            lcm *= bigPow(primeAndCount.getKey(), primeAndCount.getValue(), MOD);
            lcm %= MOD;
        }

        long answer = 0;
        for (final long i : array) {
            answer += lcm * invertWithMod(i, MOD);
            answer %= MOD;
        }

        System.out.println(answer);
    }

    private static long invertWithMod(final long number, final long mod) {
        return bigPow(number, number - 2, mod);
    }

    private static long bigPow(final long number, final long count, final long mod) {
        long returnValue = 1;
        for (long i = 0; i < count; i++) {
            returnValue *= number;
            returnValue %= mod;
        }
        return returnValue;
    }

    private static Map<Integer, Integer> primeFactorization(long n) {
        final double sqrt = Math.sqrt(n);
        final Map<Integer, AtomicInteger> countMap = new HashMap<>();
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                countMap.computeIfAbsent(i, v -> new AtomicInteger()).incrementAndGet();
                n /= i;
                i--;
            }
        }

        if (n != 1) {
            countMap.computeIfAbsent((int) n, v -> new AtomicInteger()).incrementAndGet();
        }

        return countMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entrySet -> entrySet.getValue().get()));
    }
}
