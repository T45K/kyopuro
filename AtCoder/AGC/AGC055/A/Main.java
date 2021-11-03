package AtCoder.AGC.AGC055.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();

        final int[] answers = new int[3 * n];
        count('A', 'B', 'C', s, n, answers, 1);
        count('A', 'C', 'B', s, n, answers, 2);
        count('B', 'A', 'C', s, n, answers, 3);
        count('B', 'C', 'A', s, n, answers, 4);
        count('C', 'A', 'B', s, n, answers, 5);
        count('C', 'B', 'A', s, n, answers, 6);
        final String answer = Arrays.stream(answers)
            .mapToObj(Integer::toString)
            .collect(Collectors.joining());
        System.out.println(answer);
    }

    private static void count(final char char1, final char char2, final char char3, final String s, final int n, final int[] answers, final int number) {
        final long count1 = IntStream.range(0, n)
            .filter(i -> s.charAt(i) == char1 && answers[i] == 0)
            .count();
        final long count2 = IntStream.range(n, 2 * n)
            .filter(i -> s.charAt(i) == char2 && answers[i] == 0)
            .count();
        final long count3 = IntStream.range(2 * n, 3 * n)
            .filter(i -> s.charAt(i) == char3 && answers[i] == 0)
            .count();
        final int min = Stream.of(count1, count2, count3)
            .mapToInt(Long::intValue)
            .min()
            .orElseThrow();
        fill(answers, min, 0, n, number, char1, s);
        fill(answers, min, n, n, number, char2, s);
        fill(answers, min, 2 * n, n, number, char3, s);
    }

    private static void fill(final int[] answers, final int count, final int begin, final int n, final int number, final char c, final String s) {
        int tmp = 0;
        for (int i = begin; i < begin + n; i++) {
            if (tmp == count) {
                break;
            }
            if (answers[i] == 0 && s.charAt(i) == c) {
                answers[i] = number;
                tmp++;
            }
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
