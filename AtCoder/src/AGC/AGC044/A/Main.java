package AGC.AGC044.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;

/*
数学 上から割っていく
移動の方法としては今見ている数字を2,3,5で割り切れるように調整した上で割っていくのが最適らしい
メモ化する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            final long n = scanner.nextLong();
            final Map<Long, BigInteger> cost = new HashMap<>();
            cost.put(2L, BigInteger.valueOf(scanner.nextLong()));
            cost.put(3L, BigInteger.valueOf(scanner.nextLong()));
            cost.put(5L, BigInteger.valueOf(scanner.nextLong()));
            final long d = scanner.nextInt();
            final BigInteger bigD = BigInteger.valueOf(d);

            final Map<Long, Long> memo = new HashMap<>();
            memo.put(n, 0L);
            final Deque<Long> queue = new ArrayDeque<>();
            queue.add(n);

            final BiConsumer<Long, Long> calc = (key, prime) -> {
                final long value = memo.get(key);
                if (key % prime == 0) {
                    final long min = cost.get(prime).min(BigInteger.valueOf(key - key / prime).multiply(bigD)).longValue();
                    if (!memo.containsKey(key / prime) || memo.get(key / prime) > value + min) {
                        memo.put(key / prime, value + min);
                        queue.add(key / prime);
                    }
                } else {
                    final long mod = key % prime;
                    final long dec = key - mod;
                    final long min1 = cost.get(prime).min(BigInteger.valueOf(dec - dec / prime).multiply(bigD)).longValue();
                    if (dec != 0 && (!memo.containsKey(dec / prime) || memo.get(dec / prime) > value + mod * d + min1)) {
                        memo.put(dec / prime, value + mod * d + min1);
                        queue.add(dec / prime);
                    }

                    final long reverseMod = prime - mod;
                    final long inc = key + reverseMod;
                    final long min2 = cost.get(prime).min(BigInteger.valueOf(inc - inc / prime).multiply(bigD)).longValue();
                    if (!memo.containsKey(inc / prime) || memo.get(inc / prime) > value + reverseMod * d + min2) {
                        memo.put(inc / prime, value + reverseMod * d + min2);
                        queue.add(inc / prime);
                    }
                }
            };

            while (!queue.isEmpty()) {
                final long key = queue.poll();
                if (key == 1) {
                    continue;
                }

                calc.accept(key, 2L);
                calc.accept(key, 3L);
                calc.accept(key, 5L);
            }

            System.out.println(memo.get(1L) + d);
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
    