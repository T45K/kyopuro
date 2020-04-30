package AtCoder.ARC.ARC024.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
列 最初と最後を繋げる必要があるのが面倒
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Boolean> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt() == 0)
                .collect(Collectors.toList());

        if (list.stream().allMatch(b -> b) || list.stream().noneMatch(b -> b)) {
            System.out.println(-1);
            return;
        }

        final boolean initial = list.get(0);
        int boarder = 0;
        for (final boolean value : list) {
            if (initial == value) {
                boarder++;
            } else {
                break;
            }
        }

        final List<Boolean> former = list.subList(0, boarder);
        final List<Boolean> formatted = list.subList(boarder, list.size());
        formatted.addAll(former);

        boolean current = formatted.get(0);
        int max = 0;
        int size = 0;
        for (final boolean value : formatted) {
            if (current == value) {
                size++;
                max = Math.max(max, size);
            } else {
                current = value;
                size = 1;
            }
        }

        System.out.println((max + 1) / 2);
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
    