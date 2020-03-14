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

public class Utility {
    static class Counter {
        private int value;

        public void increment() {
            value++;
        }

        public int getValue() {
            return value;
        }
    }

    static class Tuple<F, L> {
        private final F former;
        private final L latter;

        Tuple(final F former, final L latter) {
            this.former = former;
            this.latter = latter;
        }

        public F getFormer() {
            return former;
        }

        public L getLatter() {
            return latter;
        }
    }

    private static int intPow(final int a, final int b) {
        return (int) Math.pow(a, b);
    }

    /**
     * 組み合わせの計算をDPを用いて高速化する
     *
     * @param size 　二次元配列のサイズ
     *             　配列の大きさから size C x はできないことに注意
     * @return 二次元配列
     */
    private static long[][] createCombinationTable(final int size) {
        final int mod = 1000000007;
        final long[][] table = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    table[i][j] = 1;
                    continue;
                }

                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % mod;
            }
        }

        return table;
    }

    private static int computeGCD(final int a, final int b) {
        if (b > a) {
            return computeGCD(b, a);
        }

        if (b == 0) {
            return a;
        }

        return computeGCD(b, a % b);
    }

    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

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

    private static int[] reverseIntArray(final int[] array) {
        final int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[i] = array[array.length - i - 1];
        }

        return newArray;
    }

    /**
     * 素数modを法としたaの逆元を計算する
     * modPowと一緒に使う
     *
     * @param a   逆元を計算したい値
     * @param mod 法となる素数
     * @return modを法としたaの逆元
     */
    private static long modInv(final long a, final long mod) {
        return modPow(a, mod - 2, mod);
    }

    /**
     * 素数modを法としてaの逆元を計算する
     * modInvと一緒に使う
     *
     * @param a   逆元を計算したい値
     * @param n   a - 2
     * @param mod 法となる素数
     * @return modを法としたaの逆元
     */
    private static long modPow(long a, long n, final long mod) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = res * a % mod;
            }
            a = a * a % mod;
            n >>= 1;
        }
        return res;
    }

    /**
     * エラトステネスの篩
     * 与えられた整数以下の素数のリストを O(nloglogn) で返す
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
     * 高速にmod込みで組み合わせを計算できる
     * 使い方: new CombinationCalculator(size,mod).init().calc()
     * see http://drken1215.hatenablog.com/entry/2018/06/08/210000
     */
    private static class CombinationCalculator {
        private final int size;
        private final int mod;
        private final long[] factorials;
        private final long[] invertedElements;
        private final long[] invertedFactorials;

        CombinationCalculator(final int size, final int mod) {
            this.size = size;
            this.mod = mod;
            this.factorials = new long[size];
            this.invertedElements = new long[size];
            this.invertedFactorials = new long[size];
        }

        CombinationCalculator init() {
            factorials[0] = 1;
            factorials[1] = 1;
            invertedFactorials[0] = 1;
            invertedFactorials[1] = 1;
            invertedElements[1] = 1;
            for (int i = 2; i < size; i++) {
                factorials[i] = factorials[i - 1] * i % mod;
                invertedElements[i] = mod - invertedElements[mod % i] * (mod / i) % mod;
                invertedFactorials[i] = invertedFactorials[i - 1] * invertedElements[i] % mod;
            }
            return this;
        }

        long calc(final int n, final int k) {
            return factorials[n] * (invertedFactorials[k] * invertedFactorials[n - k] % mod) % mod;
        }
    }

    /**
     * 通常のUnionFindTreeの改造版
     * getRoot()がボトルネックになる場面で役に立つ
     * getRoot()が走るたびに該当のnodesを最新の値に保つ
     */
    static class FastUnionFindTree {
        private Integer[] nodes;
        private final List<Integer> indices = new ArrayList<>();

        FastUnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
        }

        private Integer[] init(final int numOfNodes) {
            return IntStream.rangeClosed(0, numOfNodes)
                    .boxed()
                    .toArray(Integer[]::new);
        }

        int getRoot(final int nodeNumber) {
            final int rootNode = nodes[nodeNumber];
            if (rootNode == nodeNumber) {
                for (final Integer index : indices) {
                    nodes[index] = rootNode;
                }
                indices.clear();
                return nodeNumber;
            }

            indices.add(nodeNumber);
            return getRoot(rootNode);
        }

        boolean isSame(final int nodeA, final int nodeB) {
            return getRoot(nodeA) == getRoot(nodeB);
        }

        void unit(final int nodeA, final int nodeB) {
            final int rootA = getRoot(nodeA);
            final int rootB = getRoot(nodeB);

            if (rootA == rootB) {
                return;
            }

            nodes[Math.max(rootA, rootB)] = Math.min(rootA, rootB);
        }
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
     * lowerBoundComparator: 指定した値以上の要素が初めて出現する場所を取得
     * upperBoundComparator: 指定した値より大きい要素が初めて出現する場所を取得
     */
    private static final Comparator<Long> lowerBoundComparator = (x, y) -> x >= y ? 1 : -1;
    private static final Comparator<Long> upperBoundComparator = (x, y) -> x > y ? 1 : -1;
}
