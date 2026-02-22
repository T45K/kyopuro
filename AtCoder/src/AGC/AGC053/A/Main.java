package AGC.AGC053.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
にぶたんだと何も考えずに済んで楽
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final char[] s = scanner.next().toCharArray();
        final List<Integer> list = IntStream.rangeClosed(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());
        final Function<Integer, Boolean> judgeGood = k -> judge(k, list, s);
        final int answer = binarySearch(1, 10001, judgeGood);
        System.out.println(answer);
        final String answerStr = IntStream.range(0, answer)
            .mapToObj(i ->
                list.stream()
                    .map(value -> calc(value, i, answer))
                    .map(Objects::toString)
                    .collect(Collectors.joining(" "))
            ).collect(Collectors.joining("\n"));
        System.out.println(answerStr);
    }

    private static int binarySearch(final int begin, final int end, final Function<Integer, Boolean> judgeGood) {
        if (end - begin <= 1) {
            return begin;
        }
        final int mid = (begin + end) / 2;
        final boolean isGood = judgeGood.apply(mid);
        if (isGood) {
            return binarySearch(mid, end, judgeGood);
        } else {
            return binarySearch(begin, mid, judgeGood);
        }
    }

    private static boolean judge(final int k, final List<Integer> list, final char[] s) {
        for (int i = 0; i < k; i++) {
            int previous = calc(list.get(0), i, k);
            for (int j = 1; j < list.size(); j++) {
                final int current = calc(list.get(j), i, k);
                if (s[j - 1] == '>' && previous <= current
                    || s[j - 1] == '<' && previous >= current) {
                    return false;
                }
                previous = current;
            }
        }
        return true;
    }

    private static int calc(final int value, final int i, final int k) {
        return (value + k - 1 - i) / k;
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
    