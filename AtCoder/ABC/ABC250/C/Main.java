package AtCoder.ABC.ABC250.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final int[] array = IntStream.rangeClosed(1, n).toArray();
        final Map<Integer, Integer> indices = IntStream.range(0, n)
            .boxed()
            .collect(Collectors.toMap(i -> array[i], Function.identity()));

        for (int i = 0; i < q; i++) {
            final int x = scanner.nextInt();
            final int index = indices.get(x);
            final int replaceTarget;
            if (index < n - 1) {
                replaceTarget = array[index + 1];
                array[index + 1] = x;
                indices.put(x, index + 1);
            } else {
                replaceTarget = array[index - 1];
                array[index - 1] = x;
                indices.put(x, index - 1);
            }
            array[index] = replaceTarget;
            indices.put(replaceTarget, index);
        }

        final String answer = Arrays.stream(array)
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
