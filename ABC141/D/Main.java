package ABC141.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final PriorityQueue<Integer> goods = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < n; i++) {
            goods.add(scanner.nextInt());
        }

        for (int i = 0; i < m; i++) {
            final int poll = Optional.ofNullable(goods.poll()).orElse(-1);
            goods.add(poll / 2);
        }

        long answer = 0;
        for (final Integer good : goods) {
            answer += good;
        }

        System.out.println(answer);
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

