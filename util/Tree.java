package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

/**
 * 木構造系のライブラリ
 */
public class Tree {

    /**
     * Union-Find Tree
     * <p>
     * それなりに高速に集合の合体，同じ集合に属しているかの判定ができる．<br>
     * 辺の縮約を実装している．
     */
    private static class UnionFindTree {
        private final Integer[] nodes;
        private final List<Integer> indices = new ArrayList<>();

        UnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
        }

        private Integer[] init(final int numOfNodes) {
            return IntStream.rangeClosed(0, numOfNodes)
                .boxed()
                .toArray(Integer[]::new);
        }

        /**
         * 引数のノードが属している木の根を返す．
         *
         * @param nodeNumber ノードの番号
         * @return 根，つまり属している集合の中の一番小さい値
         */
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

        /**
         * 二つのノードが同じ集合に属しているかを判定する．
         *
         * @param nodeA ノード
         * @param nodeB ノード
         * @return 二つのノードが同じ集合に属しているかの判定結果
         */
        boolean isSame(final int nodeA, final int nodeB) {
            return getRoot(nodeA) == getRoot(nodeB);
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

            if (rootA == rootB) {
                return;
            }

            nodes[Math.max(rootA, rootB)] = Math.min(rootA, rootB);
        }
    }

    /**
     * Segment Tree
     * <p>
     * モノイドに対する値の更新，区間に対するクエリをそれぞれO(log n)で行える．<br>
     * 更新，クエリの引数は 0-indexed で渡すことに注意
     */
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

        /**
         * 値の更新
         *
         * @param index "0-indexed"のインデックス
         * @param value 更新後の値
         */
        void update(final int index, final T value) {
            internalTree[index + exponent] = value;
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalTree[current] = comparator.apply(internalTree[current * 2], internalTree[current * 2 + 1]);
                current /= 2;
            }
        }

        /**
         * 値の更新
         *
         * @param index    "0-indexed"のインデックス
         * @param operator 更新式
         */
        void update(final int index, final UnaryOperator<T> operator) {
            internalTree[index + exponent] = operator.apply(internalTree[index + exponent]);
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalTree[current] = comparator.apply(internalTree[current * 2], internalTree[current * 2 + 1]);
                current /= 2;
            }
        }

        /**
         * クエリ
         * クエリの区間を [left, right) の半開区間で渡すことに注意
         *
         * @param left  "0-indexed"のクエリの左端
         * @param right "0-indexed"のクエリの右端 + 1
         *              つまり"1-indexed"のクエリの右端
         * @return クエリ結果
         */
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

    /**
     * 最小共通祖先
     * <p>
     * 根付き木の二つの頂点の内，どちらの祖先でもありなおかつ最も近い（最も根から遠い）頂点を返す．
     * 内部でセグ木を使う．
     * 構築がO(n log n)，クエリがO(log n)
     * <p>
     * 木は辞書を用いた隣接リストで渡す．
     */
    private static class LowestCommonAncestor {
        private final SegmentTree<Node> segmentTree;
        private final int[] id;

        /**
         * @param tree       木．各頂点が子の頂点を持つ隣接リスト．
         * @param root       根の番号
         * @param numOfNodes 木に含まれる頂点の個数
         */
        LowestCommonAncestor(final Map<Integer, List<Integer>> tree, final int root, final int numOfNodes) {
            final List<Node> list = new ArrayList<>(numOfNodes * 2 - 1);
            this.id = new int[numOfNodes + 1];
            dfs(root, tree, list, id, 0);

            this.segmentTree = new SegmentTree<>(list, new Node(-1, Integer.MAX_VALUE), (a, b) -> a.getDepth() < b.getDepth() ? a : b);
        }

        /**
         * 二つの頂点の最小共通祖先の頂点番号を返す．
         *
         * @param nodeA 頂点A
         * @param nodeB 頂点B
         * @return 最小共通祖先の頂点番号
         */
        int getLCA(final int nodeA, final int nodeB) {
            final int idA = id[nodeA];
            final int idB = id[nodeB];

            return segmentTree.query(Math.min(idA, idB), Math.max(idA, idB) + 1).getNumber();
        }

        private void dfs(final int current, final Map<Integer, List<Integer>> tree, final List<Node> list, final int[] id, final int depth) {
            if (id[current] == 0) {
                id[current] = list.size();
            }
            list.add(new Node(current, depth));

            for (final int next : Optional.ofNullable(tree.get(current)).orElse(Collections.emptyList())) {
                dfs(next, tree, list, id, depth + 1);
                list.add(new Node(current, depth));
            }
        }

        private static class Node {
            private final int number;
            private final int depth;

            Node(final int number, final int depth) {
                this.number = number;
                this.depth = depth;
            }

            int getNumber() {
                return number;
            }

            int getDepth() {
                return depth;
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
    }
}
