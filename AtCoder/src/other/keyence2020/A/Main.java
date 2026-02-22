package keyence2020.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int n = scanner.nextInt();

        System.out.println((n + Math.max(h, w) - 1) / Math.max(h, w));
    }
}
