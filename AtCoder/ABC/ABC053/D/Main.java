package AtCoder.ABC.ABC053.D;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int kinds = IntStream.range(0, scanner.nextInt())
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.groupingBy(k -> k))
                .size();

        System.out.println(kinds % 2 == 0 ? kinds - 1 : kinds);
    }
}
