package ABC156.C;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .sorted()
                .collect(Collectors.toList());

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 101; i++) {
            final int tmp = i;
            final int sum = list.stream()
                    .map(v -> (v - tmp) * (v - tmp))
                    .mapToInt(Integer::intValue)
                    .sum();
            min = Math.min(sum, min);
        }

        System.out.println(min);
    }
}
