package keyence2020.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int s = scanner.nextInt();

        int dummy = s == 1000000000 ? 999999999 : 1000000000;

        for (int i = 0; i < n; i++) {
            if (i < k) {
                System.out.print(s + " ");
            } else {
                System.out.println(dummy + " ");
            }
        }
    }
}
