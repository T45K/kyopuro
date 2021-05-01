package AtCoder.other.zone2021.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) throws IOException {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final LinkedList<Character> list = new LinkedList<>();
        boolean flag = true;
        for (final char c : s.toCharArray()) {
            if (c == 'R') {
                flag = !flag;
                continue;
            }
            if (flag) {
                if (!list.isEmpty() && c == list.peekLast()) {
                    list.removeLast();
                } else {
                    list.addLast(c);
                }
            } else {
                if (!list.isEmpty() && c == list.peekFirst()) {
                    list.removeFirst();
                } else {
                    list.addFirst(c);
                }
            }
        }
        if (list.isEmpty()) {
            System.out.println();
            return;
        }
        if (flag) {
            char pre = list.pollFirst();
            final StringBuilder builder = new StringBuilder();
            builder.append(pre);
            while (!list.isEmpty()) {
                final char tmp = list.pollFirst();
                if (tmp != pre) {
                    builder.append(tmp);
                    pre = tmp;
                }
            }
            System.out.println(builder);
        } else {
            char post = list.pollLast();
            final StringBuilder builder = new StringBuilder();
            builder.append(post);
            while (!list.isEmpty()) {
                final char tmp = list.pollLast();
                if (tmp != post) {
                    builder.append(tmp);
                    post = tmp;
                }
            }
            System.out.println(builder);
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) throws IOException {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = new StringTokenizer(reader.readLine());
        }

        String next() throws IOException {
            if (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        String nextLine() throws IOException {
            if (!tokenizer.hasMoreTokens()) {
                return reader.readLine();
            }
            return tokenizer.nextToken("\n");
        }
    }
}
    