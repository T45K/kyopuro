package library;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

/**
 * 木構造系のライブラリ
 */
@SuppressWarnings("unused")
public class Tree {

    /**
     * Union-Find Tree
     * <p>
     * それなりに高速に集合の合体，同じ集合に属しているかの判定ができる．<br>
     * 辺の縮約を実装している．
     */
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
        private final T[] internalArray;
        private final int exponent;
        private final T initialValue;
        private final BinaryOperator<T> comparator;

        SegmentTree(final List<T> list, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = 1 << Integer.toBinaryString(list.size() - 1).length();
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalArray = initArray(list, initialValue);
        }

        SegmentTree(final int size, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = 1 << Integer.toBinaryString(size - 1).length();
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalArray = initArray(Collections.emptyList(), initialValue);
        }

        /**
         * 値の更新
         *
         * @param index "0-indexed"のインデックス
         * @param value 更新後の値
         */
        void update(final int index, final T value) {
            internalArray[index + exponent] = value;
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalArray[current] = comparator.apply(internalArray[current * 2], internalArray[current * 2 + 1]);
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
            internalArray[index + exponent] = operator.apply(internalArray[index + exponent]);
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalArray[current] = comparator.apply(internalArray[current * 2], internalArray[current * 2 + 1]);
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
                return internalArray[k];
            }

            final int mid = (begin + end) / 2;
            return comparator.apply(query(left, right, begin, mid, k * 2), query(left, right, mid, end, k * 2 + 1));
        }

        @SuppressWarnings("unchecked")
        private T[] initArray(final List<T> list, final T initialValue) {
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
    }

    /**
     * インデックス付きSegment Tree
     * <p>
     * Segment Treeのクエリ結果に，その結果のインデックスを付与したデータ構造
     * クエリ結果の値を更新したい場合などに使える
     *
     * @param <T>
     */
    private static class IndexedSegmentTree<T> {
        private final IndexedValue<T>[] internalArray;
        private final int exponent;
        private final IndexedValue<T> initialValue;
        private final BinaryOperator<IndexedValue<T>> comparator;

        IndexedSegmentTree(final List<T> list, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = 1 << Integer.toBinaryString(list.size() - 1).length();
            this.comparator = (a, b) -> comparator.apply(a.getValue(), b.getValue()) == a.getValue() ? a : b;
            this.initialValue = new IndexedValue<>(-1, initialValue);
            internalArray = initArray(list, initialValue);
        }

        IndexedSegmentTree(final int size, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = 1 << Integer.toBinaryString(size - 1).length();
            this.comparator = (a, b) -> comparator.apply(a.getValue(), b.getValue()) == a.getValue() ? a : b;
            this.initialValue = new IndexedValue<>(-1, initialValue);
            internalArray = initArray(Collections.emptyList(), initialValue);
        }

        /**
         * 値の更新
         *
         * @param index "0-indexed"のインデックス
         * @param value 更新後の値
         */
        void update(final int index, final T value) {
            internalArray[index + exponent] = new IndexedValue<>(index, value);
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalArray[current] = comparator.apply(internalArray[current * 2], internalArray[current * 2 + 1]);
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
            internalArray[index + exponent] = new IndexedValue<>(index, operator.apply(internalArray[index + exponent].getValue()));
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalArray[current] = comparator.apply(internalArray[current * 2], internalArray[current * 2 + 1]);
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
        IndexedValue<T> query(final int left, final int right) {
            return query(left, right, 0, exponent, 1);
        }

        IndexedValue<T> query(final int left, final int right, final int begin, final int end, final int k) {
            if (left >= end || right <= begin) {
                return initialValue;
            }

            if (left <= begin && end <= right) {
                return internalArray[k];
            }

            final int mid = (begin + end) / 2;
            return comparator.apply(query(left, right, begin, mid, k * 2), query(left, right, mid, end, k * 2 + 1));
        }

        @SuppressWarnings("unchecked")
        private IndexedValue<T>[] initArray(final List<T> list, final T initialValue) {
            final IndexedValue<T>[] array = new IndexedValue[exponent * 2];
            Arrays.fill(array, new IndexedValue<>(-1, initialValue));
            for (int i = 0; i < list.size(); i++) {
                array[i + exponent] = new IndexedValue<>(i, list.get(i));
            }

            for (int i = exponent - 1; i > 0; i--) {
                array[i] = comparator.apply(array[i * 2], array[i * 2 + 1]);
            }

            return array;
        }

        private static class IndexedValue<T> {
            private final int index;
            private final T value;

            public IndexedValue(final int index, final T value) {
                this.index = index;
                this.value = value;
            }

            public int getIndex() {
                return index;
            }

            public T getValue() {
                return value;
            }
        }
    }

    /**
     * Lazy Segment Tree
     * <p>
     * セグ木の基本操作に加えて，区間更新が O(logN) で行える
     */
    private static class LazySegmentTree<T> {
        private final T[] internalArray;
        private final T[] lazyArray;
        private final int exponent;
        private final T initialValue;
        private final BinaryOperator<T> comparator;

        LazySegmentTree(final List<T> list, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = 1 << Integer.toBinaryString(list.size() - 1).length();
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalArray = initArray(list, initialValue);
            lazyArray = initLazyArray(initialValue);
        }

        LazySegmentTree(final int size, final T initialValue, final BinaryOperator<T> comparator) {
            this.exponent = 1 << Integer.toBinaryString(size - 1).length();
            this.comparator = comparator;
            this.initialValue = initialValue;
            internalArray = initArray(Collections.emptyList(), initialValue);
            lazyArray = initLazyArray(initialValue);
        }

        /**
         * 値の更新
         *
         * @param index "0-indexed"のインデックス
         * @param value 更新後の値
         */
        void update(final int index, final T value) {
            internalArray[index + exponent] = value;
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalArray[current] = comparator.apply(internalArray[current * 2], internalArray[current * 2 + 1]);
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
            internalArray[index + exponent] = operator.apply(internalArray[index + exponent]);
            int current = (index + exponent) / 2;
            while (current > 0) {
                internalArray[current] = comparator.apply(internalArray[current * 2], internalArray[current * 2 + 1]);
                current /= 2;
            }
        }

        /**
         * 区間更新
         * 区間を [left, right) の半開区間で渡すことに注意
         *
         * @param left  "0-indexed"のクエリの左端
         * @param right "0-indexed"のクエリの右端 + 1
         *              つまり"1-indexed"のクエリの右端
         * @param value 更新する値
         */
        void updateRange(final int left, final int right, final T value) {
            updateRange(left, right, value, 0, exponent, 1);
        }

        private void updateRange(final int left, final int right, final T value, final int begin, final int end, final int k) {
            evaluate(k);
            if (left <= begin && end <= right) {
                lazyArray[k] = value;
                evaluate(k);
            } else if (left < end && begin < right) {
                final int mid = (begin + end) / 2;
                updateRange(left, right, value, begin, mid, k * 2);
                updateRange(left, right, value, mid, end, k * 2 + 1);
                internalArray[k] = comparator.apply(internalArray[k * 2], internalArray[k * 2 + 1]);
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

        private T query(final int left, final int right, final int begin, final int end, final int k) {
            evaluate(k);

            if (left >= end || right <= begin) {
                return initialValue;
            }

            if (left <= begin && end <= right) {
                return internalArray[k];
            }

            final int mid = (begin + end) / 2;
            return comparator.apply(query(left, right, begin, mid, k * 2), query(left, right, mid, end, k * 2 + 1));
        }

        private void evaluate(final int index) {
            if (lazyArray[index] == initialValue) {
                return;
            }
            final T value = lazyArray[index];
            if (index * 2 + 1 < lazyArray.length) {
                lazyArray[index * 2] = value;
                lazyArray[index * 2 + 1] = value;
            }
            internalArray[index] = value;
            lazyArray[index] = initialValue;
        }

        @SuppressWarnings("unchecked")
        private T[] initArray(final List<T> list, final T initialValue) {
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

        @SuppressWarnings("unchecked")
        private T[] initLazyArray(final T initialValue) {
            final Object[] array = new Object[exponent * 2];
            Arrays.fill(array, initialValue);
            return (T[]) array;
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

    /**
     * Binary Indexed Tree
     * <p>
     * 区間和をセグ木より高速・省メモリ（定数倍）で求められる
     * クエリ，更新共にO(log n)
     */
    private static class BinaryIndexedTree {
        private final long[] internalArray;

        BinaryIndexedTree(final int size) {
            this.internalArray = new long[1 << Integer.toBinaryString(size).length()];
        }

        private long sum(final int index) {
            if (index == 0) {
                return 0;
            }
            return internalArray[index] + sum(index - (index & -index));
        }

        private void add(final int index, final long value) {
            for (int i = index; i < internalArray.length; i += i & -i) {
                internalArray[index] += value;
            }
        }
    }
}
