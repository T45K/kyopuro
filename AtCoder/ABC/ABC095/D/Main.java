package AtCoder.ABC.ABC095.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
頭ひねる系 知識不要 直線座標 初めにガバ計算量解を思いついて計算量を下げていく系
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final long c = scanner.nextLong();

        final List<Sushi> naturalList = IntStream.range(0, n)
                .mapToObj(i -> new Sushi(scanner.nextLong(), scanner.nextInt()))
                .collect(Collectors.toList());

        final List<Sushi> reverseList = naturalList.stream()
                .map(sushi -> new Sushi(c - sushi.distance, sushi.value))
                .collect(Collectors.toList());

        long max = Math.max(calc(naturalList, reverseList, n), 0);

        Collections.reverse(naturalList);
        Collections.reverse(reverseList);

        max = Math.max(max, calc(reverseList, naturalList, n));
        System.out.println(max);
    }

    private static long calc(final List<Sushi> naturalList, final List<Sushi> reverseList, final int n) {
        final long[] array = new long[n];
        long sumValue = naturalList.get(0).value;
        array[0] = sumValue - naturalList.get(0).distance;
        for (int i = 1; i < n; i++) {
            sumValue += naturalList.get(i).value;
            final long distance = naturalList.get(i).distance;
            array[i] = Math.max(sumValue - distance, array[i - 1]);
        }

        long max = array[n - 1];
        sumValue = 0;
        for (int i = n - 1; i > 0; i--) {
            sumValue += reverseList.get(i).value;
            max = Math.max(max, array[i - 1] + sumValue - 2 * reverseList.get(i).distance);
        }
        return Math.max(max, sumValue + reverseList.get(0).value - reverseList.get(0).distance);
    }

    private static class Sushi {
        final long distance;
        final long value;

        Sushi(final long distance, final long value) {
            this.distance = distance;
            this.value = value;
        }
    }

    static class FastScanner {
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

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
