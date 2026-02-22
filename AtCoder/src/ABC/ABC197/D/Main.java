package ABC.ABC197.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
座標の移動
x' = x*cos - y*sin
y' = x*sin + y*cos
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final double x0 = scanner.nextDouble();
        final double y0 = scanner.nextDouble();
        final double xn2 = scanner.nextDouble();
        final double yn2 = scanner.nextDouble();

        final double xc = (x0 + xn2) / 2;
        final double yc = (y0 + yn2) / 2;

        final double xRelative = x0 - xc;
        final double yRelative = y0 - yc;
        final double radian = 2 * Math.PI / n;
        final double xAnswer = xRelative * Math.cos(radian) - yRelative * Math.sin(radian) + xc;
        final double yAnswer = xRelative * Math.sin(radian) + yRelative * Math.cos(radian) + yc;
        System.out.println(xAnswer + " " + yAnswer);
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
