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

/*
数列 全ての岩の開始位置と終了位置を配列に持つとTLEになるので工夫する
解説AC
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int w = scanner.nextInt();
        final int c = scanner.nextInt();

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
        long min = lSortedList.stream()
                .takeWhile(rock -> rock.l < c)
                .mapToLong(rock -> rock.p)
                .sum();
        for (final Rock rock : rSortedList) {
            if (rock.r > w) {
                break;
            }
            minusSum += rock.p;
            while (index < lSortedList.size() && lSortedList.get(index).l < rock.r) {
                plusSum += lSortedList.get(index++).p;
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
