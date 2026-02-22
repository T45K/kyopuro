package ABC.ABC148.E;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }

        long division = 10;
        long answer = 0;
        while (true) {
            final long divieded = n / division;
            if (divieded == 0) {
                break;
            }

            answer += divieded;
            division *= 5;
        }

        System.out.println(answer);
    }
}
