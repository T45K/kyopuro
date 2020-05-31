package AtCoder.ABC.ABC169.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// TODO solve
public class Main {
    private static final long MOD = 998_244_353;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int s = scanner.nextInt();

        ArrayDeque<Integer>[] array = new ArrayDeque[s + 1];
        array[0] = new ArrayDeque<>();
        array[0].add(0);
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            final ArrayDeque<Integer>[] tmp = new ArrayDeque[s + 1];
            for (int j = 0; j <= s - a; j++) {
                if (array[j] != null && !array[j].isEmpty()) {
                    if (tmp[j + a] == null) {
                        tmp[j + a] = new ArrayDeque<>();
                    }
                    for (final int value : array[j]) {
                        tmp[j + a].add(value + 1);
                    }
                }
            }
            for (int j = 0; j <= s; j++) {
                if (tmp[j] == null) {
                    continue;
                }

                if (array[j] == null) {
                    array[j] = tmp[j];
                } else {
                    array[j].addAll(tmp[j]);
                }
            }
        }

        if (array[s] == null) {
            System.out.println(0);
            return;
        }

        final long[] pow = new long[3001];
        pow[0] = 1;
        for (int i = 1; i < 3001; i++) {
            pow[i] = pow[i - 1] * 2;
            pow[i] %= MOD;
        }

        long all = 0;
        for (final long value : array[s]) {
            final long rest = n - value;
            all += pow[(int) rest];
            all %= MOD;
        }

        System.out.println(all);
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
