package ABC.ABC155.B;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final boolean result = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .filter(i -> i % 2 == 0)
                .allMatch(i -> i % 3 == 0 || i % 5 == 0);
        if (result) {
            System.out.println("APPROVED");
        } else {
            System.out.println("DENIED");
        }
    }
}
