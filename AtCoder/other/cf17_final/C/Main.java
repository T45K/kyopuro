package AtCoder.other.cf17_final.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
座標 昇順に並べて交互に置く
適当にやったら通った系
正しい考察としては，2個以上あると反対側に置くのが最善なので，1個しかない場合を考えると全探索しても2^11で済む
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();

        final int[] count = new int[13];
        final int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            final int d = scanner.nextInt();
            array[i] = d;
            count[d]++;
        }

        if (count[0] >= 1 || count[12] >= 2) {
            System.out.println(0);
            return;
        }

        final boolean isOverlapping = Arrays.stream(count)
            .anyMatch(i -> i >= 3);
        if (isOverlapping) {
            System.out.println(0);
            return;
        }

        Arrays.sort(array);
        final boolean[] location = new boolean[25];
        location[0] = true;
        boolean flag = true;
        for (final int d : array) {
            if (flag) {
                location[d] = true;
            } else {
                location[24 - d] = true;
            }

            flag = !flag;
        }

        final List<Integer> list = IntStream.rangeClosed(0, 24)
            .filter(i -> location[i])
            .boxed()
            .collect(Collectors.toList());

        int min = 24 - list.get(list.size() - 1);
        for (int i = 0; i < list.size() - 1; i++) {
            min = Math.min(min, list.get(i + 1) - list.get(i));
        }

        System.out.println(min);
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
    