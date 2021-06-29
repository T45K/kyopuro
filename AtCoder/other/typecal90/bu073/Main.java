package AtCoder.other.typecal90.bu073;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

/*
解説AC
木DP
dp[pos][3]として
0: posの部分木がaのみで構成されている
1: 部分木がbのみで構成されている
2: 部分木がa,b両方を含む
という風に定義する
ただし，切断後の末端はa,b両方を含んでいなければならない

次にdpの遷移を考える
ある節nに対して，そのnの値がaの時，
部分木nがaのみで構成される組み合わせは，
子をcとすると
- 辺(n, c)を切断する場合 -> dp[pos][2]（切断後の末端はa,b両方含むという制約）
- 切断しない場合 -> dp[pos][0]
通りとなるため
dp[n][0] = (dp[c1][0] + dp[c1][2]) * (dp[c2][0] + dp[c2][2]) * ...

次に部分木nがa,b両方を含む場合の組み合わせは，
全体の遷移からaのみで構成する場合を引くことで求める
全体の組み合わせは
- 辺(n, c)を切断する場合 -> dp[pos][2]（同上）
- 切断しない場合 -> dp[pos][0] + [1] + [2]
となる
 */
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final boolean[] values = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            values[i] = scanner.next().equals("a");
        }

        final ListMap<Integer, Integer> tree = new ListMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            tree.putSingle(a, b);
            tree.putSingle(b, a);
        }

        final long[][] dp = new long[n + 1][3];
        bfs(1, 0, tree, dp, values);
        System.out.println(dp[1][2]);
    }

    private static void bfs(final int current, final int parent, final ListMap<Integer, Integer> tree, final long[][] dp, final boolean[] values) {
        if (isLeaf(tree.getList(current), parent)) {
            dp[current][values[current] ? 0 : 1] = 1;
            return;
        }

        for (final int next : tree.getList(current)) {
            if (next == parent) {
                continue;
            }
            bfs(next, current, tree, dp, values);
        }

        final int value = values[current] ? 0 : 1;
        final long either = tree.getList(current).stream()
            .filter(v -> v != parent)
            .map(v -> (dp[v][value] + dp[v][2]) % MOD)
            .reduce(1L, (a, b) -> a * b % MOD);
        final long both = tree.getList(current).stream()
            .filter(v -> v != parent)
            .map(v -> (dp[v][0] + dp[v][1] + 2 * dp[v][2]) % MOD)
            .reduce(1L, (a, b) -> a * b % MOD);
        dp[current][value] = either;
        dp[current][2] = (MOD + both - either) % MOD;
    }

    private static boolean isLeaf(final List<Integer> children, final int parent) {
        return children.size() == 1 && children.contains(parent);
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
