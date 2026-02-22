package AGC.AGC027.B;

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
数列 Nに比べてa,bの取れる値が大きく取られているのがヒント
一度全て和が同じになるような組み合わせを考えてほげる
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());

        final int[] aArray = new int[n];
        final int[] bArray = new int[n];
        for (int i = 0; i < n; i++) {
            aArray[i] = i * 50_000 + 1;
            bArray[n - i - 1] = i * 50_000 + 1;
        }

        for (int i = 0; i < list.size(); i++) {
            final int index = list.get(i) - 1;
            bArray[index] += i;
        }

        final String answer = Arrays.stream(aArray)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "))
                + "\n"
                + Arrays.stream(bArray)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
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
    