package AtCoder.ABC.ABC167.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.UnaryOperator;

/*
ABM030#Dと同じ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextLong();
        final int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = scanner.nextInt();
        }

        final int[] visited = new int[n + 1];
        Arrays.fill(visited, -1);
        visited[1] = 0;
        int tmpVisit = 1;
        final int loopStart;
        int count = 1;
        while (true) {
            int next = array[tmpVisit];
            if (visited[next] >= 0) {
                loopStart = visited[next];
                break;
            }
            visited[next] = count;
            tmpVisit = next;
            count++;
        }

        final UnaryOperator<Integer> find = value -> Arrays.stream(array)
                .filter(i -> visited[i] == value)
                .findFirst()
                .orElseThrow(RuntimeException::new);

        final long loopCount = Arrays.stream(array)
                .filter(i -> visited[i] >= loopStart)
                .distinct()
                .count();

        if (k <= loopStart) {
            final int answer = find.apply((int) k);
            System.out.println(answer);
            return;
        }

        final long diff = k - loopStart;
        final long mod = diff % loopCount;
        final long sum = loopStart + mod;
        System.out.println(find.apply((int) sum));
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
    }
}
