package AtCoder.AGC.AGC006.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数学 Xが1か2N-1以外だといける
同じ数字が2個隣り合うと，中央値の特性上それが継続される
上に上げたい数字が真ん中に来るように調整する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();

        if (x == 1 || x == 2 * n - 1) {
            System.out.println("No");
            return;
        }

        System.out.println("Yes");
        if (x == n) {
            final String answer = IntStream.rangeClosed(1, 2 * n - 1)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(" "));
            System.out.println(answer);
            return;
        }

        final IntPredicate predicate = x >= 3 ? i -> !(i >= x - 2 && i <= x + 1) : i -> !(i >= x - 1 && i <= x + 2);
        final int[] array = x >= 3 ? new int[]{x - 1, x + 1, x, x - 2} : new int[]{x + 1, x, x - 1, x + 2};
        final List<Integer> list = createList(n, predicate, array);
        final String answer = createAnswer(list);
        System.out.println(answer);
    }

    private static List<Integer> createList(final int n, final IntPredicate predicate, final int[] array) {
        final List<Integer> list = IntStream.rangeClosed(1, 2 * n - 1)
                .filter(predicate)
                .boxed()
                .collect(Collectors.toList());
        for (int i = 0; i < array.length; i++) {
            list.add(n + i - 3, array[i]);
        }
        return list;
    }

    private static String createAnswer(final List<Integer> list) {
        return list.stream()
                .map(i -> Integer.toString(i))
                .collect(Collectors.joining(" "));
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
