package ABC.ABC195.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
最終的には後ろから見ていく
T -> A -> T ... と順番に見ていくと
T: 出口になりうる入り口を多くとる
つまり、出口に辿り着きうる入り口の和集合をとる
A: 出口になりうる入り口を狭くとる
なぜなら、どの入り口に入っても同じ出口に出ることになれば、どれだけ最適に動いてもそこ以外にいけなくなるから
つまり、出口に辿り着きうる入り口の積集合をとる

これを繰り返す
 */
public class Main {
    private static final String TAKAHASHI = "Takahashi";
    private static final String AOKI = "Aoki";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final char[] s = scanner.next().toCharArray();
        final char[] x = scanner.next().toCharArray();
        if (n == 1) {
            if (x[0] == 'T') {
                System.out.println(TAKAHASHI);
                return;
            } else if (s[0] == '7' | s[0] == '0') {
                System.out.println(TAKAHASHI);
                return;
            }
            System.out.println(AOKI);
            return;
        }
        if (x[n - 1] == 'A' && (s[n - 1] != '0' && s[n - 1] != '7')) {
            System.out.println(AOKI);
            return;
        }

        final List<int[]> queue = new ArrayList<>();
        int[] mapper = new int[7];
        for (int i = 0; i < 7; i++) {
            mapper[i] = (int) Math.pow(2, i);
        }
        for (int i = 0; i < n; i++) {
            final int number = s[i] - '0';
            final int[] tmp = new int[7];
            for (int j = 0; j < 7; j++) {
                tmp[j * 10 % 7] |= mapper[j];
                tmp[(j * 10 + number) % 7] |= mapper[j];
            }
            mapper = tmp;
            if (i < n - 1 && x[i] != x[i + 1]) {
                queue.add(mapper);
                mapper = new int[7];
                for (int j = 0; j < 7; j++) {
                    mapper[j] = (int) Math.pow(2, j);
                }
            }
        }
        queue.add(mapper);
        char cur = x[n - 1];
        final boolean[] target = new boolean[7];
        target[0] = true;
        for (int i = queue.size() - 1; i >= 0; i--) {
            final int[] array = queue.get(i);
            if (cur == 'T') {
                int tmp = 0;
                for (int j = 0; j < target.length; j++) {
                    final boolean b = target[j];
                    if (b) {
                        tmp |= array[j];
                    }
                }
                for (int j = 0; j < target.length; j++) {
                    target[j] = (1 << j & tmp) > 0;
                }
            } else {
                final boolean[] tmp = new boolean[7];
                final int[] inv = inverse(array);
                for (int j = 0; j < 7; j++) {
                    final int mapping = inv[j];
                    final boolean allMatch = IntStream.range(0, 7)
                        .filter(k -> (1 << k & mapping) > 0)
                        .allMatch(k -> target[k]);
                    if (allMatch) {
                        tmp[j] = true;
                    }
                }
                if (IntStream.range(0, 7).mapToObj(j -> tmp[j]).noneMatch(Boolean::booleanValue)) {
                    System.out.println(AOKI);
                    return;
                }
                System.arraycopy(tmp, 0, target, 0, target.length);
            }

            cur = cur == 'A' ? 'T' : 'A';
        }
        if (target[0]) {
            System.out.println(TAKAHASHI);
        } else {
            System.out.println(AOKI);
        }
    }

    private static int[] inverse(final int[] array) {
        final int[] tmp = new int[7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if ((array[i] & 1 << j) > 0) {
                    tmp[j] |= 1 << i;
                }
            }
        }
        return tmp;
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
