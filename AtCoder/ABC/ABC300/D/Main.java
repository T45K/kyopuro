package AtCoder.ABC.ABC300.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final long n = scanner.nextLong();
        final List<Integer> aCandidates = sieveOfEratosthenes((int) Math.sqrt(Math.sqrt(n)) + 1);
        final List<Integer> bCandidates = sieveOfEratosthenes((int) Math.sqrt(n) + 1);
        int count = 0;
        for (int i = 0; i < aCandidates.size(); i++) {
            final int a = bCandidates.get(i);
            for (int j = i + 1; j < bCandidates.size() - 1; j++) {
                final int b = bCandidates.get(j);
                if ((long) a * a * b * bCandidates.get(j + 1) * bCandidates.get(j + 1) > n) {
                    break;
                }
                count += binarySearch(j + 1, bCandidates.size(), bCandidates, n, a, b) - j;
            }
        }
        System.out.println(count);
    }

    private static int binarySearch(final int begin, final int end, final List<Integer> list, final long n, final int a, final int b) {
        if (begin + 1 == end) {
            return begin;
        }

        final int mid = (begin + end) / 2;
        final long c = list.get(mid);
        if (a * a * b * c * c > n) {
            return binarySearch(begin, mid, list, n, a, b);
        } else {
            return binarySearch(mid, end, list, n, a, b);
        }
    }

    private static List<Integer> sieveOfEratosthenes(final int number) {
        final boolean[] isPrimeNumber = new boolean[number + 1];
        Arrays.fill(isPrimeNumber, true);
        final int sqrt = (int) Math.sqrt(number);
        for (int i = 2; i <= sqrt; i++) {
            if (!isPrimeNumber[i]) {
                continue;
            }
            for (int j = 2; i * j <= number; j++) {
                isPrimeNumber[i * j] = false;
            }
        }
        return IntStream.rangeClosed(2, number)
            .filter(i -> isPrimeNumber[i])
            .boxed()
            .collect(Collectors.toList());
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
