package ABC.ABC260.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
シミュレーションするだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(n)
            .collect(Collectors.toList());

        final TreeMap<Integer, List<Integer>> cardCounts = new TreeMap<>();
        final int[] answers = new int[n + 1];
        Arrays.fill(answers, -1);
        for (int i = 1; i <= list.size(); i++) {
            final int value = list.get(i - 1);
            final Map.Entry<Integer, List<Integer>> ceil = cardCounts.ceilingEntry(value);

            final List<Integer> values;
            if (ceil != null) {
                cardCounts.remove(ceil.getKey());
                values = ceil.getValue();
            } else {
                values = new ArrayList<>();
            }

            values.add(value);
            if (values.size() == k) {
                for (final int tmp : values) {
                    answers[tmp] = i;
                }
            } else {
                cardCounts.put(value, values);
            }
        }

        final String answer = Arrays.stream(answers, 1, n + 1)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining("\n"));
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
