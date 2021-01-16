package AtCoder.other.keyence2021.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
広く薄く
0,1,2,...と連続で置いていくのが最良
置けなくなった時点でその箱は放棄
{1}とかも無意味
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final Map<Integer, List<Integer>> map = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.groupingBy(Function.identity()));
        if (!map.containsKey(0)) {
            System.out.println(0);
            return;
        }

        int prev = Math.min(map.get(0).size(), k);
        int sum = prev;
        for (int i = 1; i < n; i++) {
            if (!map.containsKey(i)) {
                break;
            }
            final int current = map.get(i).size();
            prev = Math.min(prev, current);
            sum += prev;
        }
        System.out.println(sum);
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
