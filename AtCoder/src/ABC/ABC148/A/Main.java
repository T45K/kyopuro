package ABC.ABC148.A;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();

        final Set<Integer> set = IntStream.rangeClosed(1, 3)
                .boxed()
                .collect(Collectors.toSet());

        set.remove(a);
        set.remove(b);

        for (final int value : set) {
            System.out.println(value);
        }
    }
}
