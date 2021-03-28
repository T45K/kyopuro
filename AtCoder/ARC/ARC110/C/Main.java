package AtCoder.ARC.ARC110.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
選択バブルソートをして条件に合うかを確認するだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[n];
        final int[] invertedIndex = new int[n + 1];
        for (int i = 0; i < n; i++) {
            final int p = scanner.nextInt();
            array[i] = p;
            invertedIndex[p] = i;
        }
        final int[] isUsed = new int[n];
        int count = 1;
        for (int i = 1; i <= n; i++) {
            if (invertedIndex[i] == i - 1) {
                continue;
            }
            while (invertedIndex[i] > i - 1) {
                if (isUsed[invertedIndex[i]] != 0) {
                    System.out.println(-1);
                    return;
                }
                final int index = invertedIndex[i];
                final int tmp = array[index - 1];
                array[index - 1] = i;
                array[index] = tmp;
                invertedIndex[i]--;
                invertedIndex[tmp] = index;
                isUsed[index] = count++;
            }
        }

        if (count < n) {
            System.out.println(-1);
            return;
        }

        final String answer = IntStream.range(1, n)
            .boxed()
            .sorted(Comparator.comparingInt(i -> isUsed[i]))
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
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
