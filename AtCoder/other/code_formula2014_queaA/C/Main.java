package AtCoder.other.code_formula2014_queaA.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数列? 解説が詳しい https://www.slideshare.net/chokudai/code-formula2014-quala
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final int[][] array = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                array[i][j] = scanner.nextInt();
            }
        }

        final Set<Integer> isDetermined = new HashSet<>();
        for (int row = 0; row < n; row++) {
            int bound = k;
            final PriorityQueue<Integer> queue = new PriorityQueue<>();
            final Set<Integer> alreadyOccur = new HashSet<>();
            for (int j = 0; j < k && j * n < bound; j++) {
                for (int i = 0; i <= row && j * n + i < bound; i++) {
                    final int tmp = array[i][j];
                    if (alreadyOccur.contains(tmp)) {
                        bound++;
                        continue;
                    }
                    alreadyOccur.add(tmp);
                    if (!isDetermined.contains(tmp)) {
                        queue.add(tmp);
                        isDetermined.add(tmp);
                    }
                }
            }

            final String answer = IntStream.range(0, queue.size())
                .mapToObj(i -> Optional.ofNullable(queue.poll()).orElse(0).toString())
                .collect(Collectors.joining(" "));
            System.out.println(answer);
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
