package other.caddi2018.D;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final boolean answer = IntStream.range(0, scanner.nextInt())
                .mapToObj(i -> scanner.nextLong())
                .mapToLong(Long::longValue)
                .allMatch(i -> i % 2 == 0);

        if (answer) {
            System.out.println("second");
        } else {
            System.out.println("first");
        }
    }
}
