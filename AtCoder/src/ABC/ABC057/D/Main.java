package ABC.ABC057.D;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
数列
1つの値だけで完結する場合: その値の個数をnとすると，選び方はnからa個選ぶ，a+1個選ぶ，...，min(n,b)個選ぶ，になる
複数の値の場合: 上からa個取った時に平均値が最大になる．a個目の値が複数個ある場合，その内必要な個数の組み合わせを取る
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();

        final Map<Long, Integer> counter = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final long v = scanner.nextLong();
            counter.compute(v, (key, value) -> value == null ? 1 : value + 1);
        }

        final List<Long> keys = counter.keySet().stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        if (counter.get(keys.get(0)) >= a) {
            System.out.println(keys.get(0));
            final int value = counter.get(keys.get(0));
            BigInteger sum = BigInteger.ZERO;
            BigInteger tmp1 = BigInteger.ONE;
            BigInteger tmp2 = BigInteger.ONE;
            for (int i = 1; i < a; i++) {
                tmp1 = tmp1.multiply(BigInteger.valueOf(value - i + 1));
                tmp2 = tmp2.multiply(BigInteger.valueOf(i));
            }
            for (int i = a; i <= Math.min(value, b); i++) {
                tmp1 = tmp1.multiply(BigInteger.valueOf(value - i + 1));
                tmp2 = tmp2.multiply(BigInteger.valueOf(i));
                sum = sum.add(tmp1.divide(tmp2));
            }
            System.out.println(sum);
            return;
        }

        int accumulation = 0;
        double sum = 0;
        for (final long key : keys) {
            final int value = counter.get(key);
            if (accumulation + value < a) {
                accumulation += value;
                sum += key * value;
                continue;
            }

            final int needed = a - accumulation;
            sum += needed * key;
            System.out.println(sum / (double) a);

            BigInteger tmp1 = BigInteger.ONE;
            BigInteger tmp2 = BigInteger.ONE;
            for (int i = 1; i <= needed; i++) {
                tmp1 = tmp1.multiply(BigInteger.valueOf(value - i + 1));
                tmp2 = tmp2.multiply(BigInteger.valueOf(i));
            }
            System.out.println(tmp1.divide(tmp2));
            return;
        }
    }
}
