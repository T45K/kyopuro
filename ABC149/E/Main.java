package ABC149.E;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    // TODO
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final List<Long> list = new ArrayList<>();
        long all = 0;
        for (int i = 0; i < n; i++) {
            final long tmp = scanner.nextLong();
            all += tmp * 2 * n;
            list.add(tmp);
        }

        list.sort(Comparator.naturalOrder());

        long minus = 0;

        final int diff = n * n - m;
        final int sqrt = (int) Math.sqrt(diff);
        for (int i = 0; i < sqrt; i++) {
            minus += 2 * sqrt * list.get(i);
        }

        final Long base = list.get(sqrt);
        for (int i = 0; i < (diff - sqrt * sqrt) / 2; i++) {
            minus += 2 * (base + list.get(i));
        }

        if ((diff - sqrt * sqrt) % 2 == 1) {
            minus += base + list.get(diff - sqrt * sqrt);
        }

        System.out.println(all - minus);
    }
}
