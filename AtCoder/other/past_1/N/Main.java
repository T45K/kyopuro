package AtCoder.other.past_1.N;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO solve
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int w = scanner.nextInt();
        final int c = scanner.nextInt();

        /*final Map<Integer, Long> plusMap = new HashMap<>();
        final Map<Integer, Long> minusMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int l = scanner.nextInt();
            final int r = scanner.nextInt();
            final long p = scanner.nextInt();
            plusMap.compute(l, (k, v) -> v == null ? p : v + p);
            minusMap.compute(r + c, (k, v) -> v == null ? p : v + p);
        }

        long plusSum = 0;
        long minusSum = 0;
        long min = Long.MAX_VALUE;
        for (int i = 1; i <= w; i++) {
            plusSum += Optional.ofNullable(plusMap.get(i - 1)).orElse(0L);
            minusSum += Optional.ofNullable(minusMap.get(i)).orElse(0L);
            if (i >= c) {
                min = Math.min(min, plusSum - minusSum);
            }
        }*/
        final List<Rock> list = IntStream.range(0, n)
                .mapToObj(i -> new Rock(scanner.nextInt(), scanner.nextInt() + c, scanner.nextInt()))
                .collect(Collectors.toList());

        final List<Rock> lSortedList = list.stream()
                .sorted(Comparator.comparingInt(rock -> rock.l))
                .collect(Collectors.toList());
        final List<Rock> rSortedList = list.stream()
                .sorted(Comparator.comparingInt(rock -> rock.r))
                .collect(Collectors.toList());

        long plusSum = 0;
        long minusSum = 0;
        int index = 0;
        long min = Long.MAX_VALUE;
        for (final Rock rock : rSortedList) {
            if (rock.r > w) {
                break;
            }
            minusSum += rock.p;
            while (index < lSortedList.size() && lSortedList.get(index).l <= rock.r - 1) {
                plusSum += lSortedList.get(index).p;
                index++;
            }
            min = Math.min(min, plusSum - minusSum);
        }
        System.out.println(min);
    }

    private static class Rock {
        final int l;
        final int r;
        final long p;

        Rock(final int l, final int r, final long p) {
            this.l = l;
            this.r = r;
            this.p = p;
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
