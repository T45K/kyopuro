package AtCoder.ABC.ABC162.B;

import java.util.Scanner;
import java.util.stream.LongStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long answer = LongStream.rangeClosed(1, scanner.nextInt())
                .filter(i -> i % 3 != 0 && i % 5 != 0)
                .sum();
        System.out.println(answer);
    }
}
