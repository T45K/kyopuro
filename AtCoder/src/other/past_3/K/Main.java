package other.past_3.K;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
LinkedListのようなイメージ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();

        final int[] lastContainerOnTable = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            lastContainerOnTable[i] = i;
        }

        final Container[] containerNumbers = new Container[n + 1];
        for (int i = 1; i <= n; i++) {
            containerNumbers[i] = new Container(i);
        }

        for (int i = 0; i < q; i++) {
            final int f = scanner.nextInt();
            final int t = scanner.nextInt();
            final int x = scanner.nextInt();

            final Container targetContainer = containerNumbers[x];
            int lastContainer = lastContainerOnTable[f];
            if (targetContainer.parent == null) {
                lastContainerOnTable[f] = -1;
            } else {
                lastContainerOnTable[f] = targetContainer.parent.number;
            }

            if (lastContainerOnTable[t] == -1) {
                targetContainer.parent = null;
            } else {
                targetContainer.parent = containerNumbers[lastContainerOnTable[t]];
            }
            lastContainerOnTable[t] = lastContainer;
        }

        final int[] onTable = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (lastContainerOnTable[i] == -1) {
                continue;
            }

            for (Container container = containerNumbers[lastContainerOnTable[i]];
                 container != null;
                 container = container.parent) {
                onTable[container.number] = i;
            }
        }

        Arrays.stream(onTable, 1, n + 1)
            .forEach(System.out::println);
    }

    private static class Container {
        final int number;
        Container parent;

        Container(final int number) {
            this.number = number;
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
