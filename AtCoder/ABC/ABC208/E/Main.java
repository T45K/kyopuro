package AtCoder.ABC.ABC208.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

/*
 桁DP
 総積は疎になる
*/
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        final long k = scanner.nextLong();

        Map<Long, Long> map = new LinkedHashMap<>();
        final String str = Long.toString(n);
        long upperBound = str.charAt(0) - '0';
        for (long i = 1; i < upperBound; i++) {
            map.put(i, 1L);
        }

        for (int i = 1; i < str.length(); i++) {
            final Map<Long, Long> tmpMap = new LinkedHashMap<>();

            // 上限に張り付いていない
            for (final Map.Entry<Long, Long> entry : map.entrySet()) {
                for (int j = 0; j < 10; j++) {
                    final long product = entry.getKey() * j;
                    tmpMap.compute(product, (key, value) -> entry.getValue() + Optional.ofNullable(value).orElse(0L));
                }
            }

            // 上限に張り付いている
            final int digit = str.charAt(i) - '0';
            for (int j = 0; j < digit; j++) {
                final long product = upperBound * j;
                tmpMap.compute(product, (key, value) -> 1 + Optional.ofNullable(value).orElse(0L));
            }
            upperBound *= digit;

            // この桁から始める
            for (int j = 1; j < 10; j++) {
                tmpMap.compute((long) j, (key, value) -> 1 + Optional.ofNullable(value).orElse(0L));
            }

            map = tmpMap;
        }

        final long answer = map.entrySet().stream()
            .filter(entry -> entry.getKey() <= k)
            .mapToLong(Map.Entry::getValue)
            .sum();

        if (upperBound <= k) {
            System.out.println(answer + 1);
        } else {
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
