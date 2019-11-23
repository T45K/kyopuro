package DDCC2020.D;

import java.util.Arrays;
import java.util.Scanner;

// TODO fix
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int m = scanner.nextInt();
        long digit = 0;
        long all = 0;
        long tmp = 0;
        long pre = 0;
        for (int i = 0; i < m; i++) {
            final long d = scanner.nextLong();
            final long c = scanner.nextLong();

            digit += c;
            all += d * c;
            tmp += (pre + d * c) / 10;
            pre = (d * c) % 10;
        }

        long answer = digit - 1 + tmp;
        System.out.println(answer);
    }

    private static long sumDigit(final long value) {
        final String[] array = Long.toString(value).split("");
        return Arrays.stream(array)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
