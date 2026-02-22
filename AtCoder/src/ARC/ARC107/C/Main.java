package ARC.ARC107.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/*
同じ行，列は存在しない
入れ替わる順番が変わっても結果は同じ
列を入れ替えても行の入れ替えには影響されない
行と列の入れ替えは独立，行ごと，列ごとの結果を掛ければ良い

入れ替え可能な行（列）の集合内で要素を自由に入れ替え可能（組み合わせ数はP!）なので，
Union-Findで集合を求めれば良い
 */
public class Main {
    private static final long MOD = 998_244_353;
    private static final LongBinaryOperator multipleMod = (a, b) -> a * b % MOD;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int[][] table = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = scanner.nextInt();
            }
        }
        final long calc1 = calc(n, tree -> IntStream.range(0, n)
            .forEach(i -> IntStream.range(0, n)
                .filter(l -> l != i)
                .filter(l -> IntStream.range(0, n)
                    .map(j -> table[i][j] + table[l][j])
                    .allMatch(sum -> sum <= k)
                ).forEach(l -> tree.unit(i, l))));
        final long calc2 = calc(n, tree -> IntStream.range(0, n)
            .forEach(j -> IntStream.range(0, n)
                .filter(l -> l != j)
                .filter(l -> IntStream.range(0, n)
                    .map(i -> table[i][j] + table[i][l])
                    .allMatch(sum -> sum <= k)
                ).forEach(l -> tree.unit(j, l))));
        System.out.println(calc1 * calc2 % MOD);
    }

    private static long calc(final int n, final Consumer<UnionFindTree> treeConstructor) {
        final UnionFindTree tree = new UnionFindTree(n);
        treeConstructor.accept(tree);
        return IntStream.range(0, n)
            .map(tree::getRoot)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity()))
            .values().stream()
            .mapToLong(Collection::size)
            .map(Main::factorial)
            .reduce(1, multipleMod);
    }

    private static long factorial(final long n) {
        return LongStream.rangeClosed(2, n).reduce(1, multipleMod);
    }

    private static class UnionFindTree {
        private final int[] nodes;
        private final Deque<Integer> indices = new ArrayDeque<>();

        UnionFindTree(final int numOfNodes) {
            this.nodes = IntStream.rangeClosed(0, numOfNodes).toArray();
        }

        /**
         * 引数のノードが属している木の根を返す．
         *
         * @param nodeNumber ノードの番号
         * @return 根，つまり属している集合の中の一番小さい値
         */
        int getRoot(final int nodeNumber) {
            final int rootNode = nodes[nodeNumber];
            if (rootNode != nodeNumber) {
                indices.add(nodeNumber);
                return getRoot(rootNode);
            }

            final Consumer<Integer> updateRoot = index -> nodes[index] = rootNode;
            indices.forEach(updateRoot);
            indices.clear();
            return nodeNumber;
        }

        /**
         * 引数のノードが属する集合を合体させる．
         *
         * @param nodeA ノード
         * @param nodeB ノード
         */
        void unit(final int nodeA, final int nodeB) {
            final int rootA = getRoot(nodeA);
            final int rootB = getRoot(nodeB);
            nodes[Math.max(rootA, rootB)] = Math.min(rootA, rootB);
        }
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
    