package other.tenka1_2018.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
数列 数字を良い感じに並べていく
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();

        final List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        int bound = 1;
        for (int i = 1; i <= n; ) {
            list.get(0).add(i);
            final List<Integer> tmp = new ArrayList<>();
            tmp.add(i);
            list.add(tmp);
            final int last = list.size() - 1;
            for (int j = 1; j < bound; j++) {
                if (i + j > n) {
                    break;
                }
                list.get(j).add(i + j);
                list.get(last).add(i + j);
            }
            i += bound;
            bound++;
        }

        final long count = list.stream()
                .map(List::size)
                .distinct()
                .count();

        if (count == 1) {
            System.out.println("Yes");
            System.out.println(list.size());
            System.out.println(list.stream()
                    .map(l -> l.size() + " " + l.stream()
                            .map(i -> Integer.toString(i))
                            .collect(Collectors.joining(" ")))
                    .collect(Collectors.joining("\n"))
            );
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
