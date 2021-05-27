package AtCoder.other.typecal90.AH034;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int[] array = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int num = 0;
        int begin = 0;
        for (int end = 0; end < n; end++) {
            if (map.containsKey(array[end]) && map.get(array[end]) > 0) {
                map.compute(array[end], (key, value) -> Optional.ofNullable(value).orElseThrow() + 1);
                max = Math.max(max, end - begin + 1);
                continue;
            }

            map.compute(array[end], (key, value) -> 1);
            num++;
            for (; num > k; begin++) {
                map.compute(array[begin], (key, value) -> Optional.ofNullable(value).orElseThrow() - 1);
                if (map.get(array[begin]) == 0) {
                    num--;
                }
            }
            max = Math.max(max, end - begin + 1);
        }
        System.out.println(max);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
    