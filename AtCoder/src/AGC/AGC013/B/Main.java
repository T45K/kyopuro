package AGC.AGC013.B;

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
import java.util.function.Consumer;
import java.util.stream.Collectors;

/*
適当なパスを作り，そのパスが条件を満たさなければ端点を追加する
証明は知らん
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            graph.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }

        int begin = 1;
        int end = graph.get(1).get(0);
        final boolean[] isIncluded = new boolean[n + 1];
        final Deque<Integer> path = new ArrayDeque<>();
        final Consumer<Integer> init = endPoint -> {
            isIncluded[endPoint] = true;
            path.add(endPoint);
        };
        init.accept(begin);
        init.accept(end);

        extendPath(begin, path::addFirst, graph, isIncluded);
        extendPath(end, path::addLast, graph, isIncluded);

        System.out.println(path.size());
        final String answer = path.stream()
            .map(i -> Integer.toString(i))
            .collect(Collectors.joining(" "));
        System.out.println(answer);
    }

    private static void extendPath(final int endPoint, final Consumer<Integer> addPath, final Map<Integer, List<Integer>> graph, final boolean[] isIncluded) {
        final Optional<Integer> beginCandidate = graph.get(endPoint).stream()
            .filter(i -> !isIncluded[i])
            .findFirst();
        if (beginCandidate.isPresent()) {
            final int next = beginCandidate.get();
            isIncluded[next] = true;
            addPath.accept(next);
            extendPath(next, addPath, graph, isIncluded);
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
