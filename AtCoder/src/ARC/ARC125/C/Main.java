package ARC.ARC125.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
小さい値をなるべく前に入れる
つまり，Aiの後に残っている数字の中で最小の値（Aiより小さい）を挿入する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(k)
            .collect(Collectors.toList());
        final Set<Integer> set = new HashSet<>(list);
        final Deque<Integer> queue = IntStream.rangeClosed(1, n)
            .boxed()
            .filter(Predicate.not(set::contains))
            .collect(Collectors.toCollection(ArrayDeque::new));
        final StringJoiner joiner = new StringJoiner(" ");
        for (int i = 0; i < list.size() - 1; i++) {
            final Integer value = list.get(i);
            joiner.add(value.toString());
            if (queue.isEmpty()) {
                continue;
            }
            final Integer min = queue.peekFirst();
            if (min < value) {
                joiner.add(queue.pollFirst().toString());
            }
        }

        final Integer last = list.get(k - 1);
        joiner.add(joinPredicatedValues(queue, v -> v > last))
            .add(last.toString())
            .add(joinPredicatedValues(queue, v -> v < last));
        System.out.println(joiner);
    }

    private static String joinPredicatedValues(final Queue<Integer> queue, final Predicate<Integer> predicate) {
        return queue.stream()
            .filter(predicate)
            .sorted(Comparator.reverseOrder())
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
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
