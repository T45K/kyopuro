package ABC.ABC194.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
TreeSetっで未出現の最小値を得られる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final TreeSet<Integer> set = IntStream.rangeClosed(0, n)
            .boxed()
            .collect(Collectors.toCollection(() -> new TreeSet<>(Integer::compare)));
        final int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        final int[] exist = new int[n];
        for (int i = 0; i < m; i++) {
            final int tmp = array[i];
            exist[tmp]++;
            set.remove(tmp);
        }
        int initial = set.first();
        final int min = IntStream.range(m, n)
            .map(i -> {
                final int remove = array[i - m];
                final int add = array[i];
                exist[remove]--;
                exist[add]++;
                set.remove(add);
                if (exist[remove] == 0) {
                    set.add(remove);
                }
                return set.first();
            }).reduce(initial, Math::min);
        System.out.println(min);
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
