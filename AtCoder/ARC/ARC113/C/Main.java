package AtCoder.ARC.ARC113.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
二つ連続したアルファベットs1,s2があるとき，そこ以降の全てのアルファベットをs1に変更できる
つまり，s2以降のs1ではないアルファベットの個数を数え上げればよい
後ろからやるのが最適
アルファベットの個数を数えて起き，連続したアルファベットがあれば，
それではないものの個数を足し合わせ，その後アルファベットの個数を更新する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final int[] count = new int[26];
        count[s.charAt(s.length() - 1) - 'a']++;
        long sum = 0;
        for (int i = s.length() - 2; i > 0; i--) {
            final int index = s.charAt(i) - 'a';
            count[index]++;
            if (s.charAt(i) != s.charAt(i - 1)) {
                continue;
            }
            final int all = s.length() - i;
            final int replaceable = all - count[index];
            sum += replaceable;
            Arrays.fill(count, 0);
            count[index] = all;
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
    }
}
