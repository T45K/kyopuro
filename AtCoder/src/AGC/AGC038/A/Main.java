package AGC.AGC038.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();

        for (int i = 0; i < b; i++) {
            print(0, a);
            print(1, w - a);
            System.out.println();
        }

        for (int i = 0; i < h - b; i++) {
            print(1, a);
            print(0, w - a);
            System.out.println();
        }
    }

    private static void print(final int a, final int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(a);
        }
    }
}
