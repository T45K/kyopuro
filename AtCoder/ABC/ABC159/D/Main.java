package AtCoder.ABC.ABC159.D;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());

        final Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int a = list.get(i);
            countMap.compute(a, (k, v) -> v == null ? 1 : v + 1);
        }

        long all = 0;
        for (final long value : countMap.values()) {
            all += value * (value - 1) / 2;
        }

        for (final int a : list) {
            final int count = countMap.get(a);
            if (count == 1) {
                System.out.println(all);
            } else {
                System.out.println(all - count * (count - 1) / 2 + (count - 2) * (count - 1) / 2);
            }
        }
    }
}
