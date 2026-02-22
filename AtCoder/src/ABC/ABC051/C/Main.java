package ABC.ABC051.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int sx = scanner.nextInt();
        final int sy = scanner.nextInt();
        final int tx = scanner.nextInt();
        final int ty = scanner.nextInt();

        final int xDistance = tx - sx;
        for (int i = 0; i < xDistance; i++) {
            System.out.print("R");
        }

        final int yDistance = ty - sy;
        for (int i = 0; i < yDistance; i++) {
            System.out.print("U");
        }

        for (int i = 0; i < xDistance; i++) {
            System.out.print("L");
        }

        for (int i = 0; i < yDistance; i++) {
            System.out.print("D");
        }

        System.out.print("D");

        for (int i = 0; i < xDistance + 1; i++) {
            System.out.print("R");
        }

        for (int i = 0; i < yDistance + 1; i++) {
            System.out.print("U");
        }

        System.out.print("L");

        System.out.print("U");

        for (int i = 0; i < xDistance + 1; i++) {
            System.out.print("L");
        }

        for (int i = 0; i < yDistance + 1; i++) {
            System.out.print("D");
        }

        System.out.println("R");
    }
}
