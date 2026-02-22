package ABC.ABC289.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] a = Stream.generate(scanner::nextInt)
            .limit(n)
            .mapToInt(Integer::intValue)
            .toArray();
        final int m = scanner.nextInt();
        final Set<Integer> b = Stream.generate(scanner::nextInt)
            .limit(m)
            .collect(Collectors.toSet());
        final int x = scanner.nextInt();

        final boolean[] isVisited = new boolean[x + 100_000];
        isVisited[0] = true;
        for (int i = 0; i < x; i++) {
            if (!isVisited[i]) {
                continue;
            }

            if (b.contains(i)) {
                continue;
            }

            for (final int steps : a) {
                if (!b.contains(i + steps)) {
                    isVisited[i + steps] = true;
                }
            }
        }

        if (isVisited[x]) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
