package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 基本のライブラリ
 */
public class Utility {
    /**
     * ユークリッドの互除法
     *
     * @param a 63bitまでの非負整数
     * @param b 63bitまでの非負整数
     * @return 最大公約数
     */
    private static long euclideanAlgorithm(final long a, final long b) {
        if (b > a) {
            return euclideanAlgorithm(b, a);
        }

        if (b == 0) {
            return a;
        }

        return euclideanAlgorithm(b, a % b);
    }

    /**
     * 素因数分解
     *
     * @param n 素因数分解したい31bitまでの非負整数
     * @return 引数を構成している素数をキー，掛けられる回数をバリューとしたマップ
     */
    private static Map<Integer, Long> primeFactorization(int n) {
        final double sqrt = Math.sqrt(n);
        final Map<Integer, Long> countMap = new HashMap<>();
        for (int i = 2; i <= sqrt; i++) {
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

    /**
     * エラトステネスの篩
     * <p>
     * 与えられた整数以下の素数のリストを O(nloglogn) で返す．
     *
     * @param number 上限
     * @return 条件を満たす素数のリスト
     */
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

    /**
     * 繰り返し二乗法
     *
     * @param a 基数
     * @param b べき数
     * @return 基数の冪乗
     */
    private static long iterativePow(long a, long b) {
        long tmp = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                tmp *= a;
            }
            a *= a;
            b >>= 1;
        }

        return tmp;
    }

    /**
     * 指定した値以上の要素が初めて出現する場所を取得するComparator
     */
    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private static final Comparator<Long> lowerBoundComparator = (x, y) -> x >= y ? 1 : -1;

    /**
     * 指定した値より大きい要素が初めて出現する場所を取得するComparator
     */
    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private static final Comparator<Long> upperBoundComparator = (x, y) -> x > y ? 1 : -1;

    /**
     * java.util.Scanner の高速版
     * <p>
     * インターフェースは Scanner と同じ
     */
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

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
