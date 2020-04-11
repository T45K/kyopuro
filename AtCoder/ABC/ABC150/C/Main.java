package AtCoder.ABC.ABC150.C;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int pOrder = computeOrder(scanner, n);
        final int qOrder = computeOrder(scanner, n);

        System.out.println(Math.abs(pOrder - qOrder));
    }

    private static int computeOrder(final Scanner scanner, final int n) {
        final List<Integer> numbers = IntStream.rangeClosed(1, n)
                .boxed()
                .collect(Collectors.toList());

        int order = 1;
        for (int i = 0; i < n; i++) {
            final int number = scanner.nextInt();
            final int index = numbers.indexOf(number);
            if (index != i) {
                order += factorial(n - i) * (index - i);
            }
            numbers.remove(index);
        }

        return order;
    }

    private static int factorial(final int range) {
        int result = 1;
        for (int i = 2; i < range; i++) {
            result *= i;
        }

        return result;
    }
}
