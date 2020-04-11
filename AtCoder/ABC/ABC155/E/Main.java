package AtCoder.ABC.ABC155.E;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        final int n = s.length();
        final Integer[] array = IntStream.range(0, n)
                .mapToObj(i -> s.charAt(i) - '0')
                .toArray(Integer[]::new);

        final int[] just = new int[n + 1];
        just[0] = 0;
        final int[] extra = new int[n + 1];
        extra[0] = 1;
        for (int i = 0; i < n; i++) {
            final int digit = array[i];
            just[i + 1] = Math.min(just[i] + digit, extra[i] + 10 - digit);
            extra[i + 1] = Math.min(just[i] + digit + 1, extra[i] + 9 - digit);
        }

        System.out.println(just[n]);
    }
}
