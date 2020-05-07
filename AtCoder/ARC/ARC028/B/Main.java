package AtCoder.ARC.ARC028.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数列 上からK番目の年齢だけ欲しいので，大きさKの優先度付きキューに打ち込んでいくだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final PriorityQueue<Candidate> queue = IntStream.rangeClosed(1, k)
                .mapToObj(i -> new Candidate(i, scanner.nextInt()))
                .collect(Collectors.toCollection(() -> new PriorityQueue<>(Comparator.comparingInt(candidate -> -candidate.age))));
        System.out.println(Optional.ofNullable(queue.peek()).orElseThrow(RuntimeException::new).rank);

        IntStream.rangeClosed(k + 1, n)
                .mapToObj(i -> {
                    final int x = scanner.nextInt();
                    final Candidate top = Optional.ofNullable(queue.peek()).orElseThrow(RuntimeException::new);
                    if (x > top.age) {
                        return top.rank;
                    }
                    queue.poll();
                    queue.add(new Candidate(i, x));
                    return Optional.ofNullable(queue.peek()).orElseThrow(RuntimeException::new).rank;
                })
                .forEach(System.out::println);
    }

    private static class Candidate {
        final int rank;
        final int age;

        Candidate(final int rank, final int age) {
            this.rank = rank;
            this.age = age;
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
