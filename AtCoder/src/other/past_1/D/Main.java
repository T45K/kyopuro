package other.past_1.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[n + 1];
        for (int i = 0; i < n; i++) {
            array[scanner.nextInt()]++;
        }

        final List<Integer> added = new ArrayList<>();
        final List<Integer> deleted = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (array[i] == 0) {
                deleted.add(i);
            } else if (array[i] > 1) {
                for (int j = 1; j < array[i]; j++) {
                    added.add(i);
                }
            }
        }

        if (deleted.size() == 0) {
            System.out.println("Correct");
            return;
        }
        int index = 0;
        for (final int value : deleted) {
            System.out.println(added.get(index++) + " " + value);
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
    }
}
