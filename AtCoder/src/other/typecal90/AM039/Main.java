package other.typecal90.AM039;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
解説AC
辺を見て，その辺を通る頂点の組の個数を数える
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            tree.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            tree.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        final long[] array = new long[n + 1];
        System.out.println(dfs(1, 0, tree, array));
    }

    private static long dfs(final int current, final int parent, final Map<Integer, List<Integer>> tree, final long[] array) {
        array[current] = 1;
        long sum = 0;
        for (final int next : tree.get(current)) {
            if (next == parent) {
                continue;
            }
            sum += dfs(next, current, tree, array);
            array[current] += array[next];
        }
        return sum + (array.length - 1 - array[current]) * array[current];
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
