package AtCoder.ABC.ABC198.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
条件からアルファベットは10種類以下
0~9をpermutationしてそれぞれのアルファベットに結びつけて計算する
 */
public class Main {
    private static final String UNSOLVABLE = "UNSOLVABLE";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final char[] s1 = scanner.next().toCharArray();
        final char[] s2 = scanner.next().toCharArray();
        final char[] s3 = scanner.next().toCharArray();
        if (s3.length >= Math.max(s1.length, s2.length) + 2) {
            System.out.println(UNSOLVABLE);
            return;
        }
        if (!isLessThanTenCharacters(new String(s1) + new String(s2) + new String(s3))) {
            System.out.println(UNSOLVABLE);
            return;
        }

        final int[] mapper = new int[26];
        Arrays.fill(mapper, -1);
        int count = 0;
        for (final char c : s1) {
            if (mapper[c - 'a'] == -1) {
                mapper[c - 'a'] = count;
                count++;
            }
        }
        for (final char c : s2) {
            if (mapper[c - 'a'] == -1) {
                mapper[c - 'a'] = count;
                count++;
            }
        }
        for (final char c : s3) {
            if (mapper[c - 'a'] == -1) {
                mapper[c - 'a'] = count;
                count++;
            }
        }

        final Function<int[], String> f = array -> {
            final Function<char[], Long> toLong = charArray -> {
                long value = 0;
                for (final char c : charArray) {
                    value *= 10;
                    value += array[mapper[c - 'a']];
                }
                return value;
            };

            final long s1Value = toLong.apply(s1);
            if (Long.toString(s1Value).length() != s1.length || s1Value == 0) {
                return null;
            }

            final long s2value = toLong.apply(s2);
            if (Long.toString(s2value).length() != s2.length || s2value == 0) {
                return null;
            }

            final long s3Value = toLong.apply(s3);
            if (Long.toString(s3Value).length() != s3.length) {
                return null;
            }

            if (s1Value + s2value == s3Value) {
                return s1Value + "\n" + s2value + "\n" + s3Value;
            } else {
                return null;
            }
        };

        final ArrayDeque<Integer> queue = IntStream.range(0, 10).boxed().collect(Collectors.toCollection(ArrayDeque::new));
        final String answer = permutation(new int[10], queue, 0, f);
        if (answer == null) {
            System.out.println(UNSOLVABLE);
        } else {
            System.out.println(answer);
        }
    }

    private static String permutation(final int[] array, final Queue<Integer> queue, final int index, final Function<int[], String> f) {
        if (index == 10) {
            return f.apply(array);
        }

        for (int i = 0; i < queue.size(); i++) {
            final int poll = queue.poll();
            array[index] = poll;
            final String result = permutation(array, queue, index + 1, f);
            if (result != null) {
                return result;
            }
            queue.add(poll);
        }
        return null;
    }

    private static boolean isLessThanTenCharacters(final String str) {
        return IntStream.range(0, str.length())
            .map(str::charAt)
            .distinct()
            .count() <= 10;
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
    