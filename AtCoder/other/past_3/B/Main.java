package AtCoder.other.past_3.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int[] array = new int[m + 1];
        Arrays.fill(array, n);
        final Map<Integer, List<Integer>> map = new HashMap<>();

        final int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            final int s = scanner.nextInt();
            final int ns = scanner.nextInt();
            if (s == 1) {
                if (map.get(ns) == null) {
                    System.out.println(0);
                    continue;
                }
                final int sum = map.get(ns).stream()
                    .mapToInt(index -> array[index])
                    .sum();
                System.out.println(sum);
            } else {
                final int nm = scanner.nextInt();
                map.computeIfAbsent(ns, v -> new ArrayList<>()).add(nm);
                array[nm]--;
            }
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }
    }
}
