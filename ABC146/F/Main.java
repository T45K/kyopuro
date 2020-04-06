package ABC146.F;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
貪欲 直感 証明なしの直感に頼った貪欲がたまたま上手くいくパターン
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        if (m >= n) {
            System.out.println(n);
            return;
        }

        final String s = scanner.next();
        final List<Boolean> list = IntStream.range(1, n)
                .mapToObj(i -> s.charAt(i) == '0')
                .collect(Collectors.toList());

        int falseConstraint = 0;
        for (final boolean bool : list) {
            if (bool) {
                falseConstraint = 0;
                continue;
            }

            falseConstraint++;
            if (falseConstraint == m) {
                System.out.println(-1);
                return;
            }
        }

        Collections.reverse(list);
        int constraint = 0;
        int last = 0;
        final List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final boolean bool = list.get(i);
            constraint++;
            if (bool) {
                last = i;
            }
            if (constraint == m) {
                if (bool) {
                    candidates.add(m);
                    constraint = 0;
                } else {
                    candidates.add(m - (i - last));
                    constraint = i - last;
                }
            }
        }
        candidates.add(constraint + 1);
        Collections.reverse(candidates);

        final String answer = candidates.stream()
                .map(i -> Integer.toString(i))
                .collect(Collectors.joining(" "));
        System.out.println(answer);
    }
}
