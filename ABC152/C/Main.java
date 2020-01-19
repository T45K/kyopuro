package ABC152.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        int min = 200001;
        int count = 0;
        for (int i = 0; i < n; i++) {
            final int p = scanner.nextInt();
            if (p <= min) {
                count++;
                min = p;
            }
        }

        System.out.println(count);
    }
}
