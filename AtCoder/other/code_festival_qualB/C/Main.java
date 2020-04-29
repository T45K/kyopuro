package AtCoder.other.code_festival_qualB.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
文字列操作 s3を構成するのに必要なs1の最小の文字数と最大の文字数をカウントして，条件に入っているか確認する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int[] s1 = countChar(scanner.next());
        final int[] s2 = countChar(scanner.next());
        final int[] s3 = countChar(scanner.next());
        final int length = Arrays.stream(s1).sum();

        int min = 0;
        int max = 0;
        for (int i = 0; i < 26; i++) {
            if (s3[i] > s1[i] + s2[i]) {
                System.out.println("NO");
                return;
            }

            min += Math.max(s3[i] - s2[i], 0);
            max += Math.min(s3[i], s1[i]);
        }

        if (min <= length / 2 && length / 2 <= max) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static int[] countChar(final String s) {
        final int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            array[s.charAt(i) - 'A']++;
        }
        return array;
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
    