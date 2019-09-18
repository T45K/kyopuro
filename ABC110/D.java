package ABC110;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class D {
    private static final long MOD = 1000000007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Map<Integer, AtomicInteger> countMap = new HashMap<>();

        int num = m;
        for (int i = 2; i <= num; i++) {
            if (num % i == 0) {
                countMap.computeIfAbsent(i, counter -> new AtomicInteger())
                        .incrementAndGet();
                num /= i;
                i--;
            }
        }

        final List<Long> answerList = countMap.values().stream()
                .map(AtomicInteger::get)
                .map(value -> calculateCombination(value + n - 1, value))
                .collect(Collectors.toList());

        long answer = 1;
        for (final Long value : answerList) {
            answer = answer * value % MOD;
        }

        System.out.println(answer);

    }

    private static long calculateCombination(final long left, final long right) {
        BigInteger denominator = BigInteger.ONE;
        BigInteger numerator = BigInteger.ONE;

        for (long i = 0; i < right; i++) {
            denominator = denominator.multiply(BigInteger.valueOf(right - i));
            numerator = numerator.multiply(BigInteger.valueOf(left - i));
        }

        return (numerator.divide(denominator)).mod(BigInteger.valueOf(MOD)).longValue();
    }
}
