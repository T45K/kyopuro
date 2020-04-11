package AtCoder.ABC.ABC142.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (scanner.nextInt() >= k) {
                counter++;
            }
        }

        System.out.println(counter);
    }
}
