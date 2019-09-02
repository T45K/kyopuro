package ABC139.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        long answer = 0;

        for (int i = 1; i < n; i++) {
            answer += i;
        }

        System.out.println(answer);
    }
}
