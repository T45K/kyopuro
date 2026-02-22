package other.typecal90.K;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
後ろから見ていく
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, List<Work>> map = Stream.generate(() -> new Work(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
            .limit(n)
            .collect(Collectors.groupingBy(w -> w.d));
        final long[] salary = new long[5002];
        for (int i = 5000; i > 0; i--) { // i日目が締め切り
            for (final Work work : Optional.ofNullable(map.get(i)).orElse(Collections.emptyList())) {
                final int start = i - work.c + 1;
                for (int j = 0; j <= start; j++) {
                    salary[j] = Math.max(salary[j], salary[j + work.c] + work.s);
                }
            }
        }
        System.out.println(salary[1]);
    }

    private static class Work {
        final int d;
        final int c;
        final long s;

        public Work(final int d, final int c, final long s) {
            this.d = d;
            this.c = c;
            this.s = s;
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
