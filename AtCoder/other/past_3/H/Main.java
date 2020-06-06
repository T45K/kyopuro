package AtCoder.other.past_3.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int l = scanner.nextInt();

        final boolean[] isHurdle = new boolean[l + 1];
        for (int i = 0; i < n; i++) {
            isHurdle[scanner.nextInt()] = true;
        }

        final int t1 = scanner.nextInt();
        final int t2 = scanner.nextInt();
        final int t3 = scanner.nextInt();
        final int[] cost = new int[l + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = 0;
        for (int i = 0; i < l; i++) {
            if (isHurdle[i]) {
                cost[i] += t3;
            }

            cost[i + 1] = Math.min(cost[i + 1], cost[i] + t1);
            if (i <= l - 2) {
                cost[i + 2] = Math.min(cost[i + 2], cost[i] + t1 + t2);
            } else {
                cost[l] = Math.min(cost[l], cost[i] + (t1 + t2) / 2);
            }

            if (i <= l - 4) {
                cost[i + 4] = Math.min(cost[i + 4], cost[i] + t1 + 3 * t2);
            } else if (i <= l - 3) {
                cost[l] = Math.min(cost[l], cost[i] + (t1 + 5 * t2) / 2);
            } else if (i <= l - 2) {
                cost[l] = Math.min(cost[l], cost[i] + (t1 + 3 * t2) / 2);
            }
        }

        System.out.println(cost[l]);
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
