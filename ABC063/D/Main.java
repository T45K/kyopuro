package ABC063.D;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO solve
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();

        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        long count = 0;
        boolean isADamage = false;
        for (final int value : list) {
            final long hp;
            if (isADamage) {
                hp = value - (count - 1) * b - a;
                isADamage = false;
            } else {
                hp = value - count * b;
            }

            if (hp <= 0) {
                System.out.println(count);
                return;
            }

            final long tmp = hp / a;
            count += tmp;
            if (hp - a * tmp == 0) {
                continue;
            } else if (hp - a * tmp <= b) {
                count++;
                isADamage = true;
            } else {
                count++;
            }
        }

        System.out.println(count);
    }
}
