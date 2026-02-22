package ABC.ABC194.B;

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
        final int n = scanner.nextInt();
        final List<Employee> list = IntStream.range(0, n)
            .mapToObj(i -> new Employee(scanner.nextInt(), scanner.nextInt()))
            .collect(Collectors.toList());
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            final Employee orig = list.get(i);
            min = Math.min(min, orig.a + orig.b);
            for (int j = i + 1; j < list.size(); j++) {
                final Employee tmp = list.get(j);
                min = Math.min(min, Math.max(orig.a, tmp.b));
                min = Math.min(min, Math.max(orig.b, tmp.a));
            }
        }
        System.out.println(min);
    }

    private static class Employee {
        final int a;
        final int b;

        Employee(final int a, final int b) {
            this.a = a;
            this.b = b;
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
