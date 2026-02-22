package ABC.ABC160.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();

        final Function<Integer, List<Long>> listGenerator = length -> IntStream.range(0, length)
                .mapToObj(i -> scanner.nextLong())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        final List<Long> aApples = listGenerator.apply(a);
        final List<Long> bApples = listGenerator.apply(b);
        final List<Long> cApples = listGenerator.apply(c);

        final List<Long> aSub = aApples.subList(0, x);
        final List<Long> bSub = bApples.subList(0, y);

        final Function<List<Long>, Long> sumList = list -> list.stream()
                .mapToLong(Long::longValue)
                .sum();

        long sum = sumList.apply(aSub) + sumList.apply(bSub);

        int aIndex = x - 1;
        int bIndex = y - 1;
        for (final long cApple : cApples) {
            final long aApple = aIndex >= 0 ? aSub.get(aIndex) : Integer.MAX_VALUE;
            final long bApple = bIndex >= 0 ? bSub.get(bIndex) : Integer.MAX_VALUE;
            if (aApple > cApple && bApple > cApple) {
                break;
            }

            if (aApple < bApple) {
                sum = sum - aApple + cApple;
                aIndex--;
            } else {
                sum = sum - bApple + cApple;
                bIndex--;
            }
        }
        System.out.println(sum);
    }

    private static class FastScanner {
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

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
