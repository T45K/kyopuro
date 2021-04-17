package AtCoder.other.jsc2021.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/*
基本的には、一番出現する文字に書き換えていく

まずはレベルを満たす最小の文字列に分解していく
この時、各折り返し点が異なっている場合は書き換える

次に、それぞれの文字列が同一にしていく
これは、各文字列の同じ位置に存在する文字のうち、マジョリティに書き換える

最後に各文字列が回文にならない様に書き換える
これは、一つ前のマジョリティに書き換える処理を行う際に2番目に多い文字を記録しておき、
（中間点以外の）どこか一箇所をそれに書き換えれば良い
 */
public class Main {
    private static final String IMPOSSIBLE = "impossible";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int k = scanner.nextInt();
        final String s = scanner.next();
        final Deque<String> queue = new ArrayDeque<>();
        final Supplier<String> safePeek = () -> Optional.ofNullable(queue.peek()).orElseThrow();
        queue.add(s);
        int sum = 0;
        for (int i = 0, pow = 1; i < k; i++, pow *= 2) { // 文字列を最小単位に分割していく
            final int length = safePeek.get().length();
            if (safePeek.get().length() == 0) { // これ以上分割できない
                System.out.println(IMPOSSIBLE);
                return;
            }
            if (length % 2 == 1) { // 文字列の長さが奇数 -> 折り返し地点での書き換えコストを考える
                sum += divideWithMidpoint(queue, pow);
            } else {
                divideWithoutMidpoint(queue, pow);
            }
        }
        final int length = safePeek.get().length();
        if (length == 1) { // 長さ1の時点で回文
            System.out.println(IMPOSSIBLE);
            return;
        }

        final int[][] counts = new int[length][26];
        for (final String str : queue) { // 各文字列の各位置における文字の出現回数をカウント
            for (int i = 0; i < str.length(); i++) {
                counts[i][str.charAt(i) - 'a']++;
            }
        }

        int max = -1;
        int secondMax = -1;
        final int[] created = new int[length];
        for (int i = 0; i < length; i++) {
            int tmpMax = 0;
            int tmpSecondMax = 0;
            for (int j = 0; j < 26; j++) {
                if (counts[i][j] > tmpMax) {
                    tmpSecondMax = tmpMax;
                    tmpMax = counts[i][j];
                    created[i] = j;
                    continue;
                }
                if (counts[i][j] > tmpSecondMax) {
                    tmpSecondMax = counts[i][j];
                }
            }
            sum += length - tmpMax;
            if (isMidpoint(i, length)) { // 折り返し地点は書き換えの対象外
                continue;
            }
            if (tmpSecondMax > secondMax) {
                max = tmpMax;
                secondMax = tmpSecondMax;
            }
        }
        if (!isPalindrome(created)) {
            System.out.println(sum);
        } else { // 回文の場合、最大のsecondMaxの方に書き換える
            System.out.println(sum + max - secondMax);
        }
    }

    private static boolean isMidpoint(final int index, final int length) {
        return length % 2 == 1 && index == length / 2;
    }

    private static int divideWithMidpoint(final Deque<String> queue, final int pow) {
        final Map<Integer, Integer> counts = new HashMap<>();
        for (long j = 0; j < pow; j++) {
            final String poll = Optional.ofNullable(queue.poll()).orElseThrow();
            counts.compute(poll.charAt(poll.length() / 2) - 'a', (key, v) -> v == null ? 1 : v + 1);
            queue.add(poll.substring(0, poll.length() / 2));
            queue.add(subStringReversed(poll, poll.length() / 2 + 1, poll.length()));
        }

        return counts.values().stream().max(Integer::compareTo).orElseThrow();
    }

    private static void divideWithoutMidpoint(final Deque<String> queue, final int pow) {
        for (long j = 0; j < pow; j++) {
            final String poll = Optional.ofNullable(queue.poll()).orElseThrow();
            queue.add(poll.substring(0, poll.length() / 2));
            queue.add(subStringReversed(poll, poll.length() / 2, poll.length()));
        }
    }

    private static String subStringReversed(final String str, final int begin, final int end) {
        return new StringBuilder(str.substring(begin, end)).reverse().toString();
    }

    private static boolean isPalindrome(final int[] array) {
        return IntStream.range(0, array.length / 2)
            .allMatch(i -> array[i] == array[array.length - i - 1]);
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
    