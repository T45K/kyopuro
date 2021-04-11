package AtCoder.ABC.ABC198.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
木をdfsするだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] colors = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            colors[i] = scanner.nextInt();
        }
        final Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            tree.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            tree.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        final boolean[] isVisited = new boolean[100_001];
        final List<Integer> answer = new ArrayList<>();
        answer.add(1);
        isVisited[colors[1]] = true;
        dfs(1, 0, tree, isVisited, answer, colors);
        final String answerStr = answer.stream()
            .sorted()
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answerStr);
    }

    private static void dfs(final int current, final int parent, final Map<Integer, List<Integer>> graph, final boolean[] isVisited, final List<Integer> answer, final int[] colors) {
        for (final int child : graph.get(current)) {
            if (child == parent) {
                continue;
            }
            final int color = colors[child];
            boolean flag = false;
            if (!isVisited[color]) {
                answer.add(child);
                isVisited[color] = true;
                flag = true;
            }
            dfs(child, current, graph, isVisited, answer, colors);
            if (flag) {
                isVisited[color] = false;
            }
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
    