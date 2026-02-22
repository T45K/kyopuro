package other.nikkei2019_quel.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
木 複数の入次数を持つ->祖先と繋がっている なのでdfsで子が複数の入次数を持っている時は，孫以下となる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final boolean[] isRoot = new boolean[n + 1];
        Arrays.fill(isRoot, 1, n + 1, true);
        final Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node();
        }
        for (int i = 0; i < n - 1 + m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            isRoot[b] = false;
            nodes[a].children.add(b);
            nodes[b].parentCandidates.add(a);
        }

        final int root = IntStream.rangeClosed(1, n).filter(i -> isRoot[i]).findFirst().orElseThrow(RuntimeException::new);
        dfs(root, nodes);
        Arrays.stream(nodes, 1, n + 1)
                .forEach(node -> System.out.println(node.parent));
    }

    private static void dfs(final int current, final Node[] nodes) {
        final Node node = nodes[current];
        final List<Integer> nextNodes = new ArrayList<>();
        for (final int child : node.children) {
            if (nodes[child].parentCandidates.size() != 1) {
                nodes[child].parentCandidates.remove(current);
                continue;
            }
            nodes[child].parent = current;
            nextNodes.add(child);
        }
        nextNodes.forEach(next -> dfs(next, nodes));
    }

    private static class Node {
        final Set<Integer> parentCandidates = new HashSet<>();
        final List<Integer> children = new ArrayList<>();
        int parent;
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
