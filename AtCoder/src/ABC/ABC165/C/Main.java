package ABC.ABC165.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
単純な全探索 10 ^ 10だと思い込んでたけど本当は N
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int q = scanner.nextInt();

        final int[] a = new int[q];
        final int[] b = new int[q];
        final int[] c = new int[q];
        final int[] d = new int[q];
        for (int i = 0; i < q; i++) {
            a[i] = scanner.nextInt();
            b[i] = scanner.nextInt();
            c[i] = scanner.nextInt();
            d[i] = scanner.nextInt();
        }

        final int[] array = new int[n + 1];
        final int answer = dfs(1, 1, array, n, m, a, b, c, d);
        System.out.println(answer);
    }

    private static int dfs(final int index, final int prev, final int[] array, final int n, final int m, final int[] a, final int[] b, final int[] c, final int[] d) {
        if (index > n) {
            return calc(a, b, c, d, array);
        }

        return IntStream.rangeClosed(prev, m)
                .map(i -> {
                    array[index] = i;
                    return dfs(index + 1, i, array, n, m, a, b, c, d);
                })
                .max()
                .orElseThrow();
    }

    private static int calc(final int[] a, final int[] b, final int[] c, final int[] d, final int[] array) {
        return IntStream.range(0, a.length)
                .map(i -> {
                    if (array[b[i]] - array[a[i]] == c[i]) {
                        return d[i];
                    } else {
                        return 0;
                    }
                })
                .sum();
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
