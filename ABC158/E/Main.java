package ABC158.E;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    // TODO solve
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int p = scanner.nextInt();
        final String s = scanner.next();
        final List<Character> list = IntStream.range(0, n)
                .mapToObj(s::charAt)
                .collect(Collectors.toList());

        final long[][] table = new long[n + 1][10];
        for (int j = 0; j < 10; j++) {
            table[0][j] = j % p;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                table[i][j] = table[i - 1][j] * 10 % p;
            }
        }
    }
}
