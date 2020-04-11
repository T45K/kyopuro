package AtCoder.ABC.ABC130;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int x = scanner.nextInt();

        final int[] lengths = new int[n];

        for (int i = 0; i < lengths.length; i++) {
            lengths[i] = scanner.nextInt();
        }

        int i;
        int distance = 0;
        for (i = 0; i < n; i++) {
            distance += lengths[i];
            if (distance > x) {
                break;
            }
        }

        System.out.println(i + 1);
    }
}
