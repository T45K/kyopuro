package other.zone2021.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) throws IOException {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final LinkedList<Character> list = new LinkedList<>();
        final String answer = operation(list, s, 0, true);
        System.out.println(answer);
    }

    private static String operation(final LinkedList<Character> list, final String s, final int index, final boolean isNatural) {
        if (index == s.length()) {
            if (!isNatural) {
                Collections.reverse(list);
            }
            return list.stream()
                .map(Objects::toString)
                .collect(Collectors.joining());
        }

        final char c = s.charAt(index);
        if (c == 'R') {
            return operation(list, s, index + 1, !isNatural);
        }

        if (isNatural) {
            if (!list.isEmpty() && list.peekLast() == c) {
                list.removeLast();
            } else {
                list.addLast(c);
            }
        } else {
            if (!list.isEmpty() && list.peekFirst() == c) {
                list.removeFirst();
            } else {
                list.addFirst(c);
            }
        }
        return operation(list, s, index + 1, isNatural);
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
    }
}
    