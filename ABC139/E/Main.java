package ABC139.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final MyDeque[] combination = new MyDeque[n];
        final List<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            indexList.add(i);
            combination[i] = new MyDeque();
            for (int j = 0; j < n - 1; j++) {
                combination[i].queue.add(scanner.nextInt() - 1);
            }
        }

        int counter = 0;
        while (true) {
            counter++;

            final Set<Integer> skip = new HashSet<>();
            boolean doneOnceFlag = false;
            for (int index = 0; index < indexList.size(); index++) {
                final int i = indexList.get(index);
                if (combination[i].queue.isEmpty()) {
                    indexList.remove(index);
                    index--;
                    continue;
                }
                if (skip.contains(i)) {
                    continue;
                }

                final Integer opponent = combination[i].queue.getFirst();

                if (skip.contains(opponent)) {
                    continue;
                }

                if (combination[opponent].queue.getFirst() == i) {
                    combination[i].queue.removeFirst();
                    combination[opponent].queue.removeFirst();
                    skip.add(i);
                    skip.add(opponent);
                    doneOnceFlag = true;
                }
            }

            if (indexList.isEmpty()) {
                System.out.println(counter - 1);
                return;
            }

            if (!doneOnceFlag) {
                System.out.println(-1);
                return;
            }
        }
    }

    static class MyDeque {
        final Deque<Integer> queue = new ArrayDeque<>();
    }

    static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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
