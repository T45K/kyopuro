package ABC.ABC193.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int k = scanner.nextInt();
        final int[] array = new int[10];
        Arrays.fill(array, k);
        final String s = scanner.next();
        final String t = scanner.next();
        final int[] sArray = new int[10];
        final int[] tArray = new int[10];
        for (int i = 0; i < 4; i++) {
            array[s.charAt(i) - '0']--;
            array[t.charAt(i) - '0']--;
            sArray[s.charAt(i) - '0']++;
            tArray[t.charAt(i) - '0']++;
        }

        long numerator = 0;
        for (int i = 1; i < 10; i++) {
            if (array[i] == 0) {
                continue;
            }
            array[i]--;
            sArray[i]++;
            final long sPoint = calcPoint(sArray);
            for (int j = 1; j < 10; j++) {
                if (array[j] == 0) {
                    continue;
                }
                tArray[j]++;
                final long tPoint = calcPoint(tArray);
                if (sPoint > tPoint) {
                    numerator += (long) (array[i] + 1) * array[j];
                }
                tArray[j]--;
            }
            array[i]++;
            sArray[i]--;
        }
        final double fraction = (9 * (long) k - 8) * (9 * (long) k - 9);
        System.out.println((double) numerator / fraction);
    }

    private static long calcPoint(final int[] array) {
        long point = 0;
        for (int i = 1; i < 10; i++) {
            point += i * (long) Math.pow(10, array[i]);
        }
        return point;
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
