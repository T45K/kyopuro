package AtCoder.other.deverta2.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .sorted()
                .collect(Collectors.toList());

        final List<String> answer = new ArrayList<>();
        final int first = list.get(0);
        final int last = list.get(list.size() - 1);
        int tmp;
        if (first >= 0) {
            tmp = first;
            for (int i = 1; i < list.size() - 1; i++) {
                answer.add(tmp + " " + list.get(i));
                tmp -= list.get(i);
            }
            answer.add(last + " " + tmp);
            tmp = last - tmp;
        } else if (last <= 0) {
            tmp = last;
            for (int i = 0; i < list.size() - 1; i++) {
                answer.add(tmp + " " + list.get(i));
                tmp -= list.get(i);
            }
        } else {
            tmp = first;
            int tmp2 = last;
            for (int i = 1; i < list.size() - 1; i++) {
                final int current = list.get(i);
                if (current < 0) {
                    answer.add(tmp2 + " " + current);
                    tmp2 -= current;
                } else {
                    answer.add(tmp + " " + current);
                    tmp -= current;
                }
            }
            answer.add(tmp2 + " " + tmp);
            tmp = tmp2 - tmp;
        }

        System.out.println(tmp);
        answer.forEach(System.out::println);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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
    