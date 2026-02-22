package ABC.ABC196.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final List<Function> functions = IntStream.range(0, n)
            .mapToObj(i -> new Function(scanner.nextLong(), scanner.nextInt()))
            .collect(Collectors.toList());
        final int q = scanner.nextInt();
        final List<IndexedNumber> numbers = IntStream.range(0, q)
            .mapToObj(i -> new IndexedNumber(i, scanner.nextLong()))
            .sorted(Comparator.comparingLong(indexedNumber -> indexedNumber.value))
            .collect(Collectors.toList());

        long sum = 0;
        int left = 0;
        long leftValue = numbers.get(0).value;
        int right = numbers.size() - 1;
        long rightValue = numbers.get(numbers.size() - 1).value;
        boolean allFlag = false;
        long allValue = 0;
        for (final Function function : functions) {
            final long value = function.a;
            switch (function.t) {
                case 1: {
                    sum += value;
                    break;
                }
                case 2: { // max
                    if (allFlag) {
                        allValue = Math.max(allValue, value - sum);
                        break;
                    }
                    if (rightValue + sum <= value) {
                        allFlag = true;
                        allValue = value - sum;
                        break;
                    }
                    if (leftValue + sum >= value) {
                        break;
                    }
                    left = maxBinarySearch(numbers, left, right, value - sum);
                    leftValue = value - sum;
                    break;
                }
                case 3: { // min
                    if (allFlag) {
                        allValue = Math.min(allValue, value - sum);
                        break;
                    }
                    if (leftValue + sum >= value) {
                        allFlag = true;
                        allValue = value - sum;
                        break;
                    }
                    if (rightValue + sum <= value) {
                        break;
                    }
                    right = minBinarySearch(numbers, left, right, value - sum);
                    rightValue = value - sum;
                }
            }
        }

        if (allFlag) {
            final long allSum = allValue + sum;
            final String answer = IntStream.range(0, q)
                .mapToObj(i -> Long.toString(allSum))
                .collect(Collectors.joining("\n"));
            System.out.println(answer);
            return;
        }

        for (int i = 0; i < q; i++) {
            if (i <= left) {
                numbers.get(i).value = leftValue + sum;
            } else if (i >= right) {
                numbers.get(i).value = rightValue + sum;
            } else {
                numbers.get(i).value += sum;
            }
        }

        final String answer = numbers.stream()
            .sorted(Comparator.comparingInt(number -> number.index))
            .map(number -> number.value)
            .map(Objects::toString)
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static int maxBinarySearch(final List<IndexedNumber> numbers, final int left, final int right, final long value) {
        if (right - left <= 1) {
            return left;
        }
        final int mid = (left + right) / 2;
        if (numbers.get(mid).value <= value) {
            return maxBinarySearch(numbers, mid, right, value);
        } else {
            return maxBinarySearch(numbers, left, mid, value);
        }
    }

    private static int minBinarySearch(final List<IndexedNumber> numbers, final int left, final int right, final long value) {
        if (right - left <= 1) {
            return right;
        }
        final int mid = (left + right) / 2;
        if (numbers.get(mid).value >= value) {
            return minBinarySearch(numbers, left, mid, value);
        } else {
            return minBinarySearch(numbers, mid, right, value);
        }
    }

    private static class Function {
        final long a;
        final int t;

        Function(final long a, final int t) {
            this.a = a;
            this.t = t;
        }
    }

    private static class IndexedNumber {
        final int index;
        long value;

        IndexedNumber(final int index, final long value) {
            this.index = index;
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
