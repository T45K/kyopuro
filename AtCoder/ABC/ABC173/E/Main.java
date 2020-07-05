package AtCoder.ABC.ABC173.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

// TODO solve
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        int k = scanner.nextInt();

        final List<Long> plusList = new ArrayList<>();
        final List<Long> minusList = new ArrayList<>();
        int zeros = 0;
        for (int i = 0; i < n; i++) {
            final long a = scanner.nextInt();
            if (a > 0) {
                plusList.add(a);
            } else if (a == 0) {
                zeros++;
            } else {
                minusList.add(-a);
            }
        }

        if (k % 2 == 1 && plusList.isEmpty()) {
            if (zeros > 0) {
                System.out.println(0);
            } else {
                final long reduce = minusList.stream()
                    .sorted()
                    .limit(k)
                    .mapToLong(Long::longValue)
                    .reduce((a, b) -> a * b % MOD)
                    .orElseThrow();
                System.out.println(MOD - reduce);
            }
        } else if (k == plusList.size() + minusList.size() + zeros) {
            if (zeros > 0) {
                System.out.println(0);
            } else {
                final long production = plusList.stream()
                    .mapToLong(Long::longValue)
                    .reduce((a, b) -> a * b % MOD)
                    .orElse(1)
                    * minusList.stream()
                    .mapToLong(Long::longValue)
                    .reduce((a, b) -> a * b % MOD)
                    .orElse(1) % MOD;

                if (minusList.size() % 2 == 0) {
                    System.out.println(production);
                } else {
                    System.out.println(MOD - production);
                }
            }
        } else if (k > plusList.size() + minusList.size() / 2 * 2) {
            System.out.println(0);
        } else if (k < plusList.size() + minusList.size() / 2 * 2) {
            plusList.sort(Comparator.naturalOrder());
            minusList.sort(Comparator.naturalOrder());
            final Deque<Long> plusQueue = new ArrayDeque<>(plusList);
            final Deque<Long> minusQueue = new ArrayDeque<>(minusList);

            long production = 1;
            if (k % 2 == 1) {
                production *= plusQueue.pollFirst();
                k--;
            }

            for (int i = 0; i < k; i += 2) {
                if (plusQueue.size() >= 2 && minusQueue.size() >= 2) {
                    final long p1 = plusQueue.pollFirst();
                    final long p2 = plusQueue.pollFirst();
                    final long m1 = minusQueue.pollFirst();
                    final long m2 = minusQueue.pollFirst();

                    if (p1 * p2 > m1 * m2) {
                        production *= p1 * p2 % MOD;
                        production %= MOD;
                        minusQueue.addFirst(m2);
                        minusQueue.addFirst(m1);
                    } else {
                        production *= m1 * m2 % MOD;
                        production %= MOD;
                        plusQueue.addFirst(p2);
                        plusQueue.addFirst(p1);
                    }
                } else if (plusQueue.size() >= 2) {
                    final long p1 = plusQueue.pollFirst();
                    final long p2 = plusQueue.pollFirst();
                    production *= p1 * p2 % MOD;
                    production %= MOD;
                } else if (minusQueue.size() >= 2) {
                    final long m1 = minusQueue.pollFirst();
                    final long m2 = minusQueue.pollFirst();
                    production *= m1 * m2 % MOD;
                    production %= MOD;
                } else {
                    throw new RuntimeException();
                }
            }

            System.out.println(production);
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
    