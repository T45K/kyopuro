package ABC.ABC124.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int number = scanner.nextInt();

        int max = 0;
        int result = 0;

        for (int i = 0; i < number; i++) {
            final int mountain = scanner.nextInt();
            if (mountain >= max) {
                result++;
                max = mountain;
            }
        }

        System.out.println(result);
    }
}
