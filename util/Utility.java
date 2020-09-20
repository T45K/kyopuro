package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * IntStream.range(0, n)
     * .map(i -> (a * i + b) / m)
     * .sum()
     * を O(log a + log m) で計算できる
     *
     * @see <a href="https://qiita.com/HNJ/items/564f15316719209df73c">Floor Sum (ACL Practice Contest C)</a>
     */
    private static long floorSum(final long n, final long m, long a, long b) {
        long answer = 0;
        if (a >= m) {
            answer += (n - 1) * n * (a / m) / 2;
            a %= m;
        }
        if (b >= m) {
            answer += n * (b / m);
            b %= m;
        }

        final long yMax = (a * n + b) / m;
        final long xMax = yMax * m - b;
        if (yMax == 0) {
            return answer;
        }
        answer += (n - (xMax + a - 1) / a) * yMax;
        answer += floorSum(yMax, a, m, (a - xMax % a) % a);
        return answer;
    }

    /**
     * Hunt–Szymanski algorithm
     * O(n log n) で最長共通文字列の長さを計算する
     *
     * @param a 文字列
     * @param b 文字列
     * @return 最長共通部分文字列の長さ
     * @see <a href="http://www.prefield.com/algorithm/dp/lcs_hs.html">最長共通部分列（ O( (n+r) log n) ）</a>
     */
    private static int calcLCSLength(final String a, final String b) {
        final int n = a.length();
        final int m = b.length();

        final Map<Character, List<Integer>> invertedIndicesOfB = new HashMap<>();
        for (int i = m - 1; i >= 0; i--) {
            invertedIndicesOfB.computeIfAbsent(b.charAt(i), v -> new ArrayList<>()).add(i);
        }

        final Integer[] lcs = new Integer[n + 1];
        Arrays.fill(lcs, Integer.MAX_VALUE);
        lcs[0] = -1;

        @SuppressWarnings("ComparatorMethodParameterNotUsed") final Comparator<Integer> lowerBoundComparator = (x, y) -> x >= y ? 1 : -1;
        for (final char c : a.toCharArray()) {
            if (!invertedIndicesOfB.containsKey(c)) {
                continue;
            }

            for (final int indexOfB : invertedIndicesOfB.get(c)) {
                final int index = ~Arrays.binarySearch(lcs, indexOfB, lowerBoundComparator);
                lcs[index] = indexOfB;
            }
        }

        return ~Arrays.binarySearch(lcs, Integer.MAX_VALUE - 1) - 1;
    }
}
