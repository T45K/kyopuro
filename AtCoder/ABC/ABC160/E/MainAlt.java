package AtCoder.ABC.ABC160.E;

import java.util.Comparator;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainAlt {
    public static void main(final String[] args) {

        final Scanner scanner = new Scanner(System.in);
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();

        final BiFunction<Integer, Integer, Stream<Long>> streamGenerator = (length, limit) -> IntStream.range(0, length)
                .mapToObj(i -> scanner.nextLong())
                .sorted(Comparator.reverseOrder())
                .limit(limit);

        final long answer = Stream.of(
                streamGenerator.apply(a, x),
                streamGenerator.apply(b, y),
                streamGenerator.apply(c, c))
                .flatMap(Function.identity())
                .sorted(Comparator.reverseOrder())
                .limit(x + y)
                .mapToLong(Long::longValue)
                .sum();

        System.out.println(answer);
    }
}
