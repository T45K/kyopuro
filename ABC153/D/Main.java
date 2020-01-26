package ABC153.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        long h = scanner.nextLong();

        long base = 1;
        long answer = 0;
        while (h > 0) {
            answer += base;
            base *= 2L;
            h /= 2;
        }
        System.out.println(answer);
    }
}
