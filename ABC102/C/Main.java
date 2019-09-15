package ABC102.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long[] arrayForSort = new long[n];
        final long[] nativeArray = new long[n];

        for (int i = 0; i < n; i++) {
            final long tmp = scanner.nextLong();
            arrayForSort[i] = tmp - (i + 1);
            nativeArray[i] = tmp;
        }

        Arrays.sort(arrayForSort);

        if (n % 2 == 0) {
            final long mid1 = arrayForSort[n / 2];
            final long mid2 = arrayForSort[n / 2 - 1];

            long answer1 = 0;
            long answer2 = 0;

            for (int i = 0; i < n; i++) {
                answer1 += Math.abs(nativeArray[i] - (mid1 + i + 1));
                answer2 += Math.abs(nativeArray[i] - (mid2 + i + 1));
            }

            System.out.println(Math.min(answer1,answer2));
        } else {
            final long mid = arrayForSort[(n - 1) / 2];

            long answer = 0;

            for (int i = 0; i < n; i++) {
                answer += Math.abs(nativeArray[i] - (mid + i+1));
            }

            System.out.println(answer);
        }
    }

    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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
    }
}
