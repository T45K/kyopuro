package AtCoder.ABC.ABC144.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        for (int i = 9; i >= 1; i--) {
            if (n % i == 0 && n / i <= 9) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
    }
}
