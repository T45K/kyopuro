package AtCoder.AGC.AGC016.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数列 コーナーケースが多い テストケースを確認してAC
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, Integer> map = new HashMap<>();
        IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .forEach(value -> map.compute(value, (k, v) -> v == null ? 1 : v + 1));

        final List<Integer> keys = map.keySet().stream().sorted().collect(Collectors.toList());
        if (keys.size() == 1) {
            System.out.println(keys.get(0) == n - 1 || keys.get(0) <= n / 2 ? "Yes" : "No");
            return;
        }

        final int smaller = keys.get(0);
        final int bigger = keys.get(1);
        if (keys.size() >= 3 || bigger - smaller != 1) {
            System.out.println("No");
            return;
        }

        final int min = map.get(keys.get(0));
        if (min >= bigger) {
            System.out.println("No");
            return;
        }
        final int rest = n - min;
        final int numOfBiggerGroups = bigger - min;
        System.out.println(rest / numOfBiggerGroups >= 2 || rest == numOfBiggerGroups ? "Yes" : "No");
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
