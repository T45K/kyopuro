package AtCoder.ABC.ABC174.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO resolve
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());
        final int[] sei = new int[n + 1];
        final Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            final int value = list.get(i - 1);
            sei[i] = sei[i - 1];
            if (!set.contains(value)) sei[i] += 1;
            set.add(value);
        }

        final Set<Integer> set2 = new HashSet<>();
        final int[] gyaku = new int[n + 1];
        for (int i = n; i > 0; i--) {
            final int value = list.get(i - 1);
            if (!set2.contains(value)) gyaku[i] += 1;
            gyaku[i - 1] = gyaku[i];
            set2.add(value);
        }

        for (int i = 0; i < q; i++) {
            final int l = scanner.nextInt();
            final int r = scanner.nextInt();
            final int answer = sei[r] - (gyaku[0] - gyaku[l + 1]);
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

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
    