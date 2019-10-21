package AGC021.A;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final Integer[] n = Arrays.stream(scanner.nextLine().split(""))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        int tmp = 0;
        for (final Integer value : n) {
            tmp += value;
        }

        System.out.println(Math.max(tmp, (n.length - 1) * 9 + n[0] - 1));
    }
}
