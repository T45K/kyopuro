package AtCoder.ABC.ABC297.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final int[] array = IntStream.range(0, s.length())
            .filter(i -> s.charAt(i) == 'B')
            .toArray();
        if ((array[0] + array[1]) % 2 == 0) {
            System.out.println("No");
            return;
        }

        final List<Character> list = IntStream.range(0, s.length())
            .filter(i -> s.charAt(i) == 'K' || s.charAt(i) == 'R')
            .mapToObj(s::charAt)
            .collect(Collectors.toList());
        if (list.get(0) == 'R' && list.get(1) == 'K' && list.get(2) == 'R') {
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
    }
}
