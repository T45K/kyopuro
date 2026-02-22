package AGC.AGC049.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();
        final int[] sArray = IntStream.range(0, n)
            .map(i -> s.charAt(i) - '0')
            .toArray();
        final String t = scanner.next();
        final int[] tArray = IntStream.range(0, n)
            .map(i -> t.charAt(i) - '0')
            .toArray();
        int left = 1;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (sArray[i] == tArray[i]) {
                continue;
            }
            left = Math.max(left, i + 1);
            final int index = indexOf(sArray, 1, left);
            if (index == -1) {
                System.out.println(-1);
                return;
            }
            sArray[index] = 0;
            left = index + 1;
            sum += index - i;
        }
        System.out.println(sum);
    }

    private static int indexOf(final int[] array, final int value, final int index) {
        for (int i = index; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
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
