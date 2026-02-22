package ABC.ABC136.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
整数列の和はどんな操作をしても常に一定なので，
和を割り切れる数が答え候補になる
答え候補の内，k回の移動で達成できるものが答え
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final List<Integer> list = IntStream.range(0, n)
            .mapToObj(i -> scanner.nextInt())
            .collect(Collectors.toList());

        final int sum = list.stream()
            .mapToInt(Integer::intValue)
            .sum();

        final List<Integer> dividable = IntStream.range(0, sum)
            .map(i -> sum - i)
            .filter(i -> sum % i == 0)
            .boxed().collect(Collectors.toList());

        // stream 使うとTLEになった...
        for (final int value : dividable) {
            final List<Integer> mods = list.stream()
                .filter(i -> i % value != 0)
                .map(i -> i % value)
                .sorted()
                .collect(Collectors.toList());
            if (mods.size() == 0) {
                System.out.println(value);
                return;
            }

            final int[] fromLeft = new int[mods.size()];
            fromLeft[0] = mods.get(0);
            for (int i = 1; i < mods.size(); i++) {
                fromLeft[i] = fromLeft[i - 1] + mods.get(i);
            }

            final int[] fromRight = new int[mods.size()];
            fromRight[mods.size() - 1] = value - mods.get(mods.size() - 1);
            for (int i = mods.size() - 1; i > 0; i--) {
                fromRight[i - 1] = fromRight[i] + value - mods.get(i - 1);
            }

            for (int i = 0; i < mods.size() - 1; i++) {
                if (fromLeft[i] == fromRight[i + 1]) {
                    if (fromLeft[i] <= k) {
                        System.out.println(value);
                        return;
                    } else {
                        break;
                    }
                }
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
    