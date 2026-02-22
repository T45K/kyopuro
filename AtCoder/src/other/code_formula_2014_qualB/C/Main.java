package other.code_formula_2014_qualB.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

/*
文字列 ゴリ押し
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String a = scanner.next();
        final String b = scanner.next();

        final long count = IntStream.range(0, a.length())
            .filter(i -> a.charAt(i) != b.charAt(i))
            .count();

        if (count > 6) {
            System.out.println("NO");
            return;
        }

        final int[] aCounter = new int[26];
        final StringBuilder aDiff = new StringBuilder();
        final StringBuilder bDiff = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            final char aChar = a.charAt(i);
            final char bChar = b.charAt(i);
            aCounter[aChar - 'a']++;
            if (aChar != bChar) {
                aDiff.append(aChar);
                bDiff.append(bChar);
            }
        }

        final int max = Arrays.stream(aCounter).max().getAsInt();
        final int swapCount = randomSwap(aDiff.toString().toCharArray(), bDiff.toString().toCharArray());
        if (swapCount == 1) {
            System.out.println("YES");
        } else if (swapCount == 0 && max >= 2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static int randomSwap(final char[] a, final char[] b) {
        final int n = a.length;
        final BiConsumer<Integer, Integer> swap = (i, j) -> {
            final char tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        };
        boolean flag = false;
        for (int i1 = 0; i1 < n; i1++) {
            for (int j1 = i1 + 1; j1 < n; j1++) {
                swap.accept(i1, j1);
                for (int i2 = 0; i2 < n; i2++) {
                    for (int j2 = i2 + 1; j2 < n; j2++) {
                        swap.accept(i2, j2);
                        for (int i3 = 0; i3 < n; i3++) {
                            for (int j3 = i3 + 1; j3 < n; j3++) {
                                swap.accept(i3, j3);
                                if (Arrays.equals(a, b)) {
                                    return 1;
                                }
                                swap.accept(i3, j3);
                            }
                        }
                        if (!flag && Arrays.equals(a, b)) {
                            flag = true;
                        }
                        swap.accept(i2, j2);
                    }
                }
                if (!flag && Arrays.equals(a, b)) {
                    flag = true;
                }
                swap.accept(i1, j1);
            }
        }

        if (!flag && Arrays.equals(a, b)) {
            flag = true;
        }

        return flag ? 0 : -1;
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
    