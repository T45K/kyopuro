package AtCoder.ABC.ABC222.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
まず初めに1を根として，1からの距離の総和Sを求める
次に，1に繋がっている頂点について考えると，
その頂点に移動したとき，その頂点の子に1ずつ近付き，それ以外の1の子に1だけ遠ざかるので，
その頂点からの距離の総和は
S - (その頂点の子の数) + (その頂点以外の1の子の数)
となる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final ListMap<Integer, Integer> tree = new ListMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            tree.putSingle(u, v);
            tree.putSingle(v, u);
        }
        final long[] numOfChildren = new long[n + 1];
        dfsForNumOfChildren(1, 0, numOfChildren, tree);
        final long[] answers = new long[n + 1];
        answers[1] = dfsForSumDistance(1, 0, numOfChildren, tree);
        dfsForAnswer(1, 0, numOfChildren, answers, tree, n);
        final String answer = Arrays.stream(answers, 1, n + 1)
            .mapToObj(Long::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static void dfsForAnswer(final int current, final int parent, final long[] numOfChildren, final long[] answers, final ListMap<Integer, Integer> tree, final long n) {
        for (final int next : tree.getList(current)) {
            if (next == parent) {
                continue;
            }
            answers[next] = answers[current] - numOfChildren[next] + (n - numOfChildren[next] - 2);
            dfsForAnswer(next, current, numOfChildren, answers, tree, n);
        }
    }

    private static long dfsForSumDistance(final int current, final int parent, final long[] numOfChildren, final ListMap<Integer, Integer> tree) {
        long sum = 0;
        for (final int next : tree.getList(current)) {
            if (next == parent) {
                continue;
            }
            final long tmp = dfsForSumDistance(next, current, numOfChildren, tree);
            sum += tmp;
        }
        return sum + numOfChildren[current];
    }

    private static void dfsForNumOfChildren(final int current, final int parent, final long[] numOfChildren, final ListMap<Integer, Integer> tree) {
        for (final int next : tree.getList(current)) {
            if (next == parent) {
                continue;
            }
            dfsForNumOfChildren(next, current, numOfChildren, tree);
            numOfChildren[current] += numOfChildren[next] + 1;
        }
    }

    private static class ListMap<K, V> extends HashMap<K, List<V>> {

        public void putSingle(final K key, final V value) {
            super.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }

        public List<V> getList(final K key) {
            return Optional.ofNullable(super.get(key)).orElse(Collections.emptyList());
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
