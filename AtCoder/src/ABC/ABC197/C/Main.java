package ABC.ABC197.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
全探索
bit全探索の方がきれいに書けそう
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());
        final boolean[] array = new boolean[n];
        final int answer = recursive(0, array, list);
        System.out.println(answer);
    }

    private static int recursive(final int index, final boolean[] array, final List<Integer> list) {
        if (index == list.size() - 1) {
            int initial = 0;
            int tmp = 0;
            for (int i = 0; i < array.length; i++) {
                tmp |= list.get(i);
                if (!array[i]) {
                    initial ^= tmp;
                    tmp = 0;
                }
            }
            return initial;
        }

        int min = recursive(index + 1, array, list);
        array[index] = true;
        min = Math.min(min, recursive(index + 1, array, list));
        array[index] = false;
        return min;
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
