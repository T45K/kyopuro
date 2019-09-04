package ABC112;

import java.util.Scanner;

public class D {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int divide = m / n;

        for (int i = divide; i >= 0; i--) {
            if (m % i == 0 && m/i >= n) {
                System.out.println(i);
                return;
            }
        }
    }
}
