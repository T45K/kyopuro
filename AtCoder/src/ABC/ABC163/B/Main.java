package ABC.ABC163.B;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int sum = IntStream.range(0, m)
                .mapToObj(i -> scanner.nextInt())
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(n - sum >= 0 ? n - sum : -1);
    }
}
