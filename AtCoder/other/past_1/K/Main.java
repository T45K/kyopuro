package AtCoder.other.past_1.K;

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
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;

/*
LCA
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();

        final Map<Integer, List<Integer>> tree = new HashMap<>();
        int root = -1;
        for (int i = 1; i <= n; i++) {
            final int p = scanner.nextInt();
            if (p == -1) {
                root = i;
                continue;
            }
            tree.computeIfAbsent(p, v -> new ArrayList<>()).add(i);
        }

        final List<Node> list = new ArrayList<>(2 * n - 1);
        final int[] id = new int[n + 1];
        Arrays.fill(id, -1);
        dfs(root, tree, list, 0, id);
        final SegmentTree<Node> rmq = new SegmentTree<>(list, new Node(-1, Integer.MAX_VALUE), (a, b) -> a.depth < b.depth ? a : b);

        final int q = scanner.nextInt();
        final List<String> answer = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            final int idA = id[a];
            final int idB = id[b];

            final Node lca = rmq.query(Math.min(idA, idB), Math.max(idA, idB) + 1);
            if (lca.number == b) {
                answer.add("Yes");
            } else {
                answer.add("No");
            }
        }

        answer.forEach(System.out::println);
    }

    private static void dfs(final int current, final Map<Integer, List<Integer>> tree, final List<Node> list, final int depth, final int[] id) {
        if (id[current] < 0) {
            id[current] = list.size();
        }
        list.add(new Node(current, depth));

        for (final int next : Optional.ofNullable(tree.get(current)).orElse(Collections.emptyList())) {
            dfs(next, tree, list, depth + 1, id);
            list.add(new Node(current, depth));
        }
    }

    private static class Node {
        final int number;
        final int depth;

        Node(final int number, final int depth) {
            this.number = number;
            this.depth = depth;
        }
    }

    private static class SegmentTree<T> {
        private final T[] internalTree;
        private final int exponent;
        private final T initialValue;
        private final BinaryOperator<T> comparator;

        SegmentTree(final List<T> list, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = calcExponent(list.size());
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalTree = initTree(list, initialValue);
        }

        void update(final int index, final T value) {
            internalTree[index + exponent] = value;
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalTree[current] = comparator.apply(internalTree[current * 2], internalTree[current * 2 + 1]);
                current /= 2;
            }
        }

        T query(final int left, final int right) {
            return query(left, right, 0, exponent, 1);
        }

        T query(final int left, final int right, final int begin, final int end, final int k) {
            if (left >= end || right <= begin) {
                return initialValue;
            }

            if (left <= begin && end <= right) {
                return internalTree[k];
            }

            final int mid = (begin + end) / 2;
            return comparator.apply(query(left, right, begin, mid, k * 2), query(left, right, mid, end, k * 2 + 1));
        }

        @SuppressWarnings("unchecked")
        private T[] initTree(final List<T> list, final T initialValue) {
            final Object[] array = new Object[exponent * 2];
            Arrays.fill(array, initialValue);
            for (int i = 0; i < list.size(); i++) {
                array[i + exponent] = list.get(i);
            }

            for (int i = exponent - 1; i > 0; i--) {
                array[i] = comparator.apply((T) array[i * 2], (T) array[i * 2 + 1]);
            }

            return (T[]) array;
        }

        private int calcExponent(final int n) {
            int exp = 1;
            while (exp < n) {
                exp <<= 1;
            }
            return exp;
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
