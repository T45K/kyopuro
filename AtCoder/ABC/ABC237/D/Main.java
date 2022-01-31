package AtCoder.ABC.ABC237.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    private static final Node HEAD = new Node(Integer.MIN_VALUE);
    private static final Node TAIL = new Node(Integer.MAX_VALUE);

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();

        Node current = new Node(0);
        HEAD.next = current;
        current.previous = HEAD;
        current.next = TAIL;
        TAIL.previous = current;

        for (int i = 0; i < n; i++) {
            final char c = s.charAt(i);
            final Node node = new Node(i + 1);
            if (c == 'R') {
                addRight(current, node);
            } else {
                addRight(current.previous, node);
            }
            current = node;
        }

        final List<Integer> list = new ArrayList<>();
        for (Node tmp = HEAD.next; tmp != TAIL; tmp = tmp.next) {
            list.add(tmp.value);
        }
        System.out.println(list.stream().map(Objects::toString).collect(Collectors.joining(" ")));
    }

    private static void addRight(final Node from, final Node addedTarget) {
        final Node next = from.next;
        addedTarget.next = next;
        next.previous = addedTarget;
        from.next = addedTarget;
        addedTarget.previous = from;
    }

    private static class Node {
        final int value;
        Node previous;
        Node next;

        Node(int value) {
            this.value = value;
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
    }
}
