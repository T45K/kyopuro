package ABC.ABC127.D;

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Stream<Long> cards = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextLong());

        final Stream<Long> operations = IntStream.range(0, m)
                .mapToObj(i -> new Operation(scanner.nextInt(), scanner.nextInt()))
                .sorted(Comparator.comparingLong(operation -> -operation.value))
                .flatMap(operation -> IntStream.range(0, operation.number).mapToObj(i -> operation.value))
                .limit(n);

        final long answer = Stream.concat(cards, operations)
                .sorted(Comparator.reverseOrder())
                .limit(n)
                .mapToLong(Long::longValue)
                .sum();

        System.out.println(answer);
    }

    private static class Operation {
        final int number;
        final long value;

        Operation(final int number, final int value) {
            this.number = number;
            this.value = value;
        }
    }
}
