package ABC.ABC287.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final String t = scanner.next();
        final int diff = s.length() - t.length();

        final int[] ngCounts = new int[t.length() + 2];
        final BiFunction<Character, Character, Boolean> equalOrQuestion =
            (c1, c2) -> c1.equals('?') || c2.equals('?') || c1.equals(c2);
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (i >= diff) { // 出てる間 -> t[i-diff]とマッチするか
                final char target = t.charAt(i - diff);
                if (!equalOrQuestion.apply(c, target)) {
                    // 隠されるまでNG
                    ngCounts[0]++;
                    ngCounts[i - (diff) + 1]--;
                }
            }
            // 隠されている間
            // 何もしない
            if (i < t.length()) { // 出てる間 -> t[i]とマッチするか
                final char target = t.charAt(i);
                if (!equalOrQuestion.apply(c, target)) {
                    // 最後までNG
                    ngCounts[i + 1]++;
                    ngCounts[t.length() + 1]--;
                }
            }
        }

        for (int i = 1; i < t.length() + 2; i++) {
            ngCounts[i] += ngCounts[i - 1];
        }

        final String answer = IntStream.rangeClosed(0, t.length())
            .mapToObj(i -> ngCounts[i] == 0 ? "Yes" : "No")
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
    }
}
