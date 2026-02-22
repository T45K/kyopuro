package ABC.ABC292.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// TODO: solve
// radianを三分探索すると精度が足りんくなる
public class Main {
    private static final double ced30 = Math.toRadians(30);
    private static double cache1 = 0;
    private static double cache2 = 0;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();

        final int longer = Math.max(a, b);
        final int shorter = Math.min(a, b);
        if (longer == shorter) {
            System.out.println((Math.sqrt(6) - Math.sqrt(2)) * shorter);
            return;
        }
        if (longer > 2 * Math.sqrt(3) / 3 * shorter) {
            System.out.println(2 * Math.sqrt(3) / 3 * shorter);
            return;
        }
        // ここからは三分探索
        final double answer = searchLongerAngle(0, Math.toRadians(15), longer, shorter);
        System.out.println(Math.acos(answer) * longer);
    }

    private static double searchLongerAngle(final double begin, final double end, final int longer, final int shorter) { // radian
        if (begin == cache1 && end == cache2) {
            return begin;
        }
        cache1 = begin;
        cache2 = end;

        final double former = (begin * 2 + end) / 3;
        final double a1 = Math.acos(former) * longer;
        final double b1 = Math.acos(ced30 - former) * shorter;

        final double latter = (begin + end * 2) / 3;
        final double a2 = Math.acos(latter) * longer;
        final double b2 = Math.acos(ced30 - latter) * shorter;
        if (Math.abs(a1 - b1) < Math.abs(a2 - b2)) {
            return searchLongerAngle(begin, latter, longer, shorter);
        } else {
            return searchLongerAngle(former, end, longer, shorter);
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
