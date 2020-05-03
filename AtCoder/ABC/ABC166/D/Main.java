package AtCoder.ABC.ABC166.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
数学 全探索するだけ 問題文をよく読もう
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long x = scanner.nextInt();
        final List<Long> values = new ArrayList<>();
        values.add(0L);
        for (long i = 1; i <= 1_000; i++) {
            final long pow = i * i * i * i * i;
            values.add(pow);
            values.add(-pow);
        }

        Collections.sort(values);
        for (int i = 0; i < values.size(); i++) {
            final long value = values.get(i);
            final long diff = x + value;
            final int index = Collections.binarySearch(values, diff);
            if (index >= 0) {
                System.out.println(getIndex(index) + " " + getIndex(i));
                return;
            }
        }
    }

    private static int getIndex(final int index) {
        return index - 1000;
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