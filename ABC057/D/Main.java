package ABC057.D;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    // TODO solve
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int a = scanner.nextInt();

        final List<Long> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextLong())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        final List<Long> limitList = list.stream()
                .limit(a)
                .collect(Collectors.toList());

        final double avg = list.stream()
                .mapToDouble(Long::doubleValue)
                .sum() / (double) n;

        final Long last = limitList.get(limitList.size() - 1);
        final long count = list.stream()
                .filter(i -> i.equals(last))
                .count();
    }
}
