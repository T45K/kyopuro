package ARC.ARC052.B;

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
数学 昔の問題にありがちな面倒臭いことをやるだけ問題
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();

        final List<Corn> list = IntStream.range(0, n)
                .mapToObj(i -> new Corn(scanner))
                .collect(Collectors.toList());

        final double[] answers = new double[q];
        for (int i = 0; i < q; i++) {
            final double a = scanner.nextInt();
            final double b = scanner.nextInt();

            double sum = 0;
            for (final Corn corn : list) {
                final double x = corn.x;
                final double h = corn.h;
                final double r = corn.r;

                if (x + h <= a || x >= b) {
                    continue;
                }

                if (x < a) {
                    final double miniH = x + h - a;
                    final double miniR = r * miniH / h;
                    sum += calc(miniR, miniH);
                } else {
                    sum += calc(r, h);
                }
                if (x + h > b) {
                    final double miniH = x + h - b;
                    final double miniR = r * miniH / h;
                    sum -= calc(miniR, miniH);
                }
            }
            answers[i] = sum;
        }

        Arrays.stream(answers)
                .forEach(System.out::println);
    }

    private static double calc(final double r, final double h) {
        return r * r * Math.PI * h / 3d;
    }

    private static class Corn {
        final double x;
        final double r;
        final double h;

        Corn(final FastScanner scanner) {
            this.x = scanner.nextDouble();
            this.r = scanner.nextDouble();
            this.h = scanner.nextDouble();
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
    