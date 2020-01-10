package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;
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

    static class UnionFindTree {
        private int[] nodes;

        UnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
        }

        public int[] getNodes() {
            return nodes;
        }

        private int[] init(final int numOfNodes) {
            final int[] array = new int[numOfNodes];
            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }

            return array;
        }

        int getRoot(final int nodeNumber) {
            final int rootNode = nodes[nodeNumber];
            if (rootNode == nodeNumber) {
                return nodeNumber;
            }

            return getRoot(rootNode);
        }

        boolean isSame(final int nodeA, final int nodeB) {
            final int rootA = getRoot(nodeA);
            final int rootB = getRoot(nodeB);

            return rootA == rootB;
        }

        void unit(final int nodeA, final int nodeB) {
            final int rootA = getRoot(nodeA);
            final int rootB = getRoot(nodeB);

            if (rootA == rootB) {
                return;
            }

            nodes[rootA] = rootB;
        }
    }

    private static int intPow(final int a, final int b) {
        return (int) Math.pow(a, b);
    }

    /**
     * 値が存在しない時も加味した二分探索
     * 存在する時はその位置
     * 存在しない時はそれが入るはずの位置を返す
     * 二分探索の性質上配列中に被りがあるとうまく働かない
     *
     * @param indexes int(Integer)の配列あるいはList
     * @param value   値
     * @return インデックス
     */
    @SuppressWarnings("unchecked")
    private static int extendedBinarySearch(final Object indexes, final int value) {
        final int rawIndex;
        if (indexes instanceof int[]) {
            rawIndex = Arrays.binarySearch((int[]) indexes, value);
        } else {
            rawIndex = Collections.binarySearch((List<Integer>) indexes, value);
        }

        if (rawIndex >= 0) {
            return rawIndex;
        }

        return -(rawIndex + 1);
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

    private static int[] reverseIntArray(final int[] array) {
        final int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[i] = array[array.length - i - 1];
        }

        return newArray;
    }

    /**
     * 通常のUnionFindTreeの改造版
     * getRoot()がボトルネックになる場面で役に立つ
     * getRoot()が走るたびに該当のnodesを最新の値に保つ
     */
    static class FastUnionFindTree {
        private int[] nodes;
        private List<Integer> indices = new ArrayList<>();

        FastUnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
        }

        int[] getNodes() {
            return nodes;
        }

        private int[] init(final int numOfNodes) {
            final int[] array = new int[numOfNodes];
            for (int i = 0; i < array.length; i++) {
                array[i] = i;
            }

            return array;
        }

        int getRoot(final int nodeNumber) {
            final int rootNode = nodes[nodeNumber];
            if (rootNode == nodeNumber) {
                for (final Integer index : indices) {
                    nodes[index] = rootNode;
                }
                indices = new ArrayList<>();
                return nodeNumber;
            }

            indices.add(nodeNumber);
            return getRoot(rootNode);
        }

        boolean isSame(final int nodeA, final int nodeB) {
            return nodes[nodeA] == nodes[nodeB];
        }

        void unit(final int nodeA, final int nodeB) {
            final int rootA = getRoot(nodeA);
            final int rootB = getRoot(nodeB);

            if (rootA == rootB) {
                return;
            }

            nodes[rootA] = rootB;
        }
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
}
