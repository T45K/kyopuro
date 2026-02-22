package ABC.ABC123.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int result = 0;
        int min = 10;
        for (int i = 0; i < 5; i++) {
            final int a = scanner.nextInt();
            if (a % 10 != 0 && a % 10 < min) {
                min = a % 10;
            }
            result += Math.ceil((float) a / 10) * 10;
        }

        if (min != 10) {
            result -= 10 - min;
        }
        System.out.println(result);
    }
}
