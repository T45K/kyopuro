package ABC146.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO fix
public class Main {
    private static final Map<Integer, Set<NodeIndex>> map = new HashMap<>();

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            map.computeIfAbsent(a, set -> new HashSet<>())
                    .add(new NodeIndex(b, i));
            map.computeIfAbsent(b, set -> new HashSet<>())
                    .add(new NodeIndex(a, i));
            array[a]++;
            array[b]++;
        }

        int maxValue = 0;
        int index = -1;
        for (int i = 0; i <= n; i++) {
            if (array[i] >= maxValue) {
                maxValue = array[i];
                index = i;
            }
        }

        final Set<Integer> set = IntStream.rangeClosed(1, maxValue)
                .boxed()
                .collect(Collectors.toSet());

        final int[] answer = new int[n - 1];
        recursive(set, index, -1, answer, -1);
        System.out.println(maxValue);
        for (final int i : answer) {
            System.out.println(i);
        }
    }

    private static void recursive(final Set<Integer> set, final int now, final int parent, final int[] answer, final int hen) {
        final Set<NodeIndex> nodeIndices = map.get(now);
        final List<Integer> list = set.stream()
                .filter(h -> h != hen)
                .collect(Collectors.toList());
        int index = 0;
        for (final NodeIndex nodeIndex : nodeIndices) {
            if (nodeIndex.node == parent) {
                continue;
            }

            final Integer value = list.get(index);
            answer[nodeIndex.index] = value;
            recursive(set, nodeIndex.node, now, answer, value);
            index++;
        }
    }

    static class NodeIndex {
        final int node;
        final int index;

        NodeIndex(final int node, final int index) {
            this.node = node;
            this.index = index;
        }
    }


    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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
