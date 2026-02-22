package ARC.ARC120.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
i+jが一致する座標だけ見ればよい
 */
public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final Map<Integer, Set<Character>> map = new LinkedHashMap<>();
        for (int i = 0; i < h; i++) {
            final String s = scanner.next();
            for (int j = 0; j < w; j++) {
                map.computeIfAbsent(i + j, k -> new HashSet<>()).add(s.charAt(j));
            }
        }
        final long answer = map.values().stream()
            .mapToLong(set -> {
                if (set.size() == 3) {
                    return 0;
                }
                if (set.size() == 2 && !set.contains('.')) {
                    return 0;
                }
                return set.size() == 1 && set.contains('.') ? 2 : 1;
            })
            .reduce(1, (a, b) -> a * b % MOD);
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
