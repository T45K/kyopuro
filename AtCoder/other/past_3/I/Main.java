package AtCoder.other.past_3.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();

        final long[] colPointer = new long[n];
        final long[] rowPointer = new long[n];
        for (int i = 0; i < n; i++) {
            colPointer[i] = i;
            rowPointer[i] = i;
        }

        boolean isForward = true;
        for (int i = 0; i < q; i++) {
            final int operation = scanner.nextInt();
            switch (operation) {
                case 1: {
                    final int a = scanner.nextInt() - 1;
                    final int b = scanner.nextInt() - 1;

                    swap(isForward ? colPointer : rowPointer, a, b);
                    break;
                }

                case 2: {
                    final int a = scanner.nextInt() - 1;
                    final int b = scanner.nextInt() - 1;

                    swap(isForward ? rowPointer : colPointer, a, b);
                    break;
                }

                case 3: {
                    isForward = !isForward;
                    break;
                }

                case 4: {
                    final int a = scanner.nextInt() - 1;
                    final int b = scanner.nextInt() - 1;

                    final long answer;
                    if (isForward) {
                        answer = colPointer[a] * (long) n + rowPointer[b];
                    } else {
                        answer = colPointer[b] * (long) n + rowPointer[a];
                    }
                    System.out.println(answer);
                }
            }
        }
    }

    private static void swap(final long[] array, final int a, final int b) {
        final long tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
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
