package AtCoder.other.typecal90.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

/*
1回目のbfs -> 1を根として最も遠い頂点を求める
2回目のbfs -> 先程求めた頂点から最も遠い頂点を求める
 */
public class Main {
    public static void main(final String[] args) throws IOException {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            tree.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            tree.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        final Node farthestFrom1 = bfs(tree, 1);
        final int answer = bfs(tree, farthestFrom1.number).distance;
        System.out.println(answer + 1);
    }

    private static Node bfs(final Map<Integer, List<Integer>> map, final int start) {
        final Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0, 0));
        while (true) {
            final Node poll = Optional.ofNullable(queue.poll()).orElseThrow();
            for (final int next : map.get(poll.number)) {
                if (next == poll.parent) {
                    continue;
                }
                queue.add(new Node(next, poll.distance + 1, poll.number));
            }
            if (queue.isEmpty()) {
                return poll;
            }
        }
    }

    private static class Node {
        final int number;
        final int distance;
        final int parent;

        Node(final int number, final int distance, final int parent) {
            this.number = number;
            this.distance = distance;
            this.parent = parent;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) throws IOException {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = new StringTokenizer(reader.readLine());
        }

        String next() throws IOException {
            if (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
    