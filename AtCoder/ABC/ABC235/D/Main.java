package AtCoder.ABC.ABC235.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;

public class Main {
    private static final int UPPER_BOUND = 1_000_000;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int a = scanner.nextInt();
        final int n = scanner.nextInt();
        final int[] array = new int[UPPER_BOUND];
        Arrays.fill(array, -1);
        array[1] = 0;
        final Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        final BiConsumer<Integer, Integer> enqueue = (v, cur) -> {
            if (v < UPPER_BOUND && array[v] == -1) {
                array[v] = cur + 1;
                queue.add(v);
            }
        };
        while (!queue.isEmpty()) {
            final int poll = queue.pollFirst();
            final int cur = array[poll];
            if (poll == n) {
                System.out.println(cur);
                return;
            }
            if ((long) poll * a < UPPER_BOUND) {
                enqueue.accept(poll * a, cur);
            }
            if (poll >= 10 && poll % 10 != 0) {
                final int swap = swap(poll);
                enqueue.accept(swap, cur);
            }
        }
        System.out.println(-1);
    }

    private static int swap(final int value) {
        final String str = Integer.toString(value);
        return Integer.parseInt(str.charAt(str.length() - 1) + str.substring(0, str.length() - 1));
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
