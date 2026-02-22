package ABC.ABC030.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.UnaryOperator;

/*
数列 循環参照っぽい感じなので，循環する長さでmodとるだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int a = scanner.nextInt();
        final String k = scanner.next();

        final int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = scanner.nextInt();
        }

        final int[] visited = new int[n + 1];
        Arrays.fill(visited, -1);
        visited[a] = 0;
        int tmpVisit = a;
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

        if (k.length() <= 19) {
            final long l = Long.parseLong(k);
            if (l <= loopStart) {
                final int answer = find.apply((int) l);
                System.out.println(answer);
                return;
            }

            final long diff = l - loopStart;
            final long mod = diff % loopCount;
            final long sum = loopStart + mod;
            System.out.println(find.apply((int) sum));
            return;
        }

        long sum = 0L;
        long base = 1;
        for (int i = k.length() - 1; i >= 0; i--) {
            int digit = k.charAt(i) - '0';
            long value = base * digit;
            sum += value;
            sum %= loopCount;
            base *= 10;
            base %= loopCount;
        }

        sum = (sum + loopCount * (loopStart / loopCount + 1) - loopStart) % loopCount;

        System.out.println(find.apply((int) (loopStart + sum)));
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
    