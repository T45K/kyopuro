package ARC.ARC112.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long b = scanner.nextLong();
        final long c = scanner.nextLong();

        if (b == 0 && c == 1) {
            System.out.println(1);
            return;
        }
        if (c <= 3) {
            System.out.println(c + 1);
            return;
        }

        final long naturalMax = b + (c - 2) / 2; // 反転 -> 引く -> 反転
        final long naturalMin = b - c / 2; // 引くだけ
        final long reverseMax = -b + (c - 1) / 2; // 引く -> 反転
        final long reverseMin = -b - (c - 1) / 2; // 反転 -> 引く
        final long answer;
        if (b >= 0 && reverseMax >= naturalMin
            || b < 0 && naturalMax >= reverseMin) {
            answer = Math.max(naturalMax, reverseMax) - Math.min(naturalMin, reverseMin) + 1;
        } else {
            answer = naturalMax - naturalMin + reverseMax - reverseMin + 2;
        }
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
