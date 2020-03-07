package ABC158.E;

import java.util.Collections;
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
        final int p = scanner.nextInt();
        final String s = scanner.next();
        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> s.charAt(i) - '0')
                .collect(Collectors.toList());

        long count = 0;
        if (p == 2 || p == 5) {
            for (int i = 0; i < list.size(); i++) {
                final int digit = list.get(i);
                if (digit % p == 0) {
                    count += i + 1;
                }
            }
        } else {
            final Map<Long, Long> counter = new HashMap<>();
            long base = 1;
            long current = 0;
            Collections.reverse(list);
            for (final int digit : list) {
                current = (current + digit * base) % p;
                base = base * 10 % p;
                if (current % p == 0) {
                    count++;
                }
                if (counter.get(current) != null) {
                    count += counter.get(current);
                }
                counter.compute(current, (k, v) -> v == null ? 1 : v + 1);
            }
        }

        System.out.println(count);
    }
}
