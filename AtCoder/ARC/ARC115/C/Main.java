package AtCoder.ARC.ARC115.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[n + 1];
        Arrays.fill(array, 1);
        final int[] answerArray = new int[n + 1];
        answerArray[1] = 1;
        for (int i = 2; i <= n; i++) {
            final int value = array[i] + 1;
            answerArray[i] = value;
            for (int j = i; j <= n; j += i) {
                array[j] = value;
            }
        }
        final String answer = Arrays.stream(answerArray, 1, n + 1)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining(" "));
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
