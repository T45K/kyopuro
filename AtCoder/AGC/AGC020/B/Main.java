package AtCoder.AGC.AGC020.B;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数学 解説AC 床関数と天井関数の使い分けがしんどい 頭こんがらがる
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int k = scanner.nextInt();
        final List<Long> list = IntStream.range(0, k)
                .mapToObj(i -> scanner.nextLong())
                .collect(Collectors.toList());

        Collections.reverse(list);
        if (list.get(0) != 2) {
            System.out.println(-1);
            return;
        }

        long min = 2;
        long max = 2;
        for (final long value : list) {
            min = (min + value - 1) / value * value;
            max = max / value * value + value - 1;
        }

        if (min > max) {
            System.out.println(-1);
        } else {
            System.out.println(min + " " + max);
        }
    }
}
