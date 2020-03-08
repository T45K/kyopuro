package ABC060.C;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long t = scanner.nextInt();

        final List<Long> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextLong())
                .collect(Collectors.toList());

        long counter = 0;
        long previous = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            final Long current = list.get(i);
            counter += Math.min(current - previous, t);
            previous = current;
        }
        System.out.println(counter + t);
    }
}
