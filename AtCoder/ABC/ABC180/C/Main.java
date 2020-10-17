package AtCoder.ABC.ABC180.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        long n = scanner.nextLong();
        final Set<Long> set = new HashSet<>();
        set.add(1L);
        set.add(n);
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i != 0 || set.contains(i)) {
                continue;
            }

            long tmp = i;
            while (n % tmp == 0) {
                set.add(tmp);
                set.add(n / tmp);
                tmp *= i;
            }
        }

        final String answer = set.stream()
            .sorted()
            .map(Object::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
