package ABC161.B;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());

        final int sum = list.stream()
                .mapToInt(Integer::intValue)
                .sum();

        final long count = list.stream()
                .filter(i -> i * 4 * m >= sum)
                .count();

        System.out.println(count >= m ? "Yes" : "No");
    }
}
