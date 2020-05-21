package AtCoder.ABC.ABC006.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
二分探索 典型
解説によると，老人を0か1に固定して考えても良いらしい
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        if (2 * n == m) {
            System.out.println(n + " 0 0");
            return;
        }

        if (3 * n == m) {
            System.out.println("0 " + n + " 0");
            return;
        }

        if (4 * n == m) {
            System.out.println("0 0 " + n);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (4 * i >= m) {
                break;
            }

            final int restMembers = n - i;
            final int restFoots = m - 4 * i;
            if (3 * restMembers < restFoots || 2 * restMembers > restFoots) {
                continue;
            }

            if (2 * restMembers == restFoots) {
                System.out.println(restMembers + " 0 " + i);
                return;
            }

            if (3 * restMembers == restFoots) {
                System.out.println("0 " + restMembers + " " + i);
                return;
            }

            int left = 0;
            int right = restMembers;
            while (right - left > 1) {
                final int mid = (left + right) / 2;
                final int tmpFoots = 3 * mid + 2 * (restMembers - mid);
                if (tmpFoots == restFoots) {
                    System.out.println((restMembers - mid) + " " + mid + " " + i);
                    return;
                } else if (tmpFoots < restFoots) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }

        System.out.println("-1 -1 -1");
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
