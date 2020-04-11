package AtCoder.AGC.AGC002.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int l = scanner.nextInt();

        final int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = scanner.nextInt();
        }

        int prev = array[1];
        for (int i = 2; i <= n; i++) {
            if (prev + array[i] < l) {
                prev = array[i];
                continue;
            }

            System.out.println("Possible");
            for (int j = 1; j < i - 1; j++) {
                System.out.println(j);
            }

            for (int j = n - 1; j >= i; j--) {
                System.out.println(j);
            }

            System.out.println(i - 1);
            return;
        }

        System.out.println("Impossible");
    }
}
