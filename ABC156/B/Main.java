package ABC156.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        final int k = scanner.nextInt();

        int count = 0;
        while (n > 0) {
            n /= k;
            count++;
        }
        System.out.println(count);
    }
}
