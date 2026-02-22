package ABC.ABC132.C;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();
        final int[] difficulties = new int[length];

        for (int i = 0; i < length; i++) {
            difficulties[i] = scanner.nextInt();
        }

        Arrays.sort(difficulties);

        final int half = length / 2;
        System.out.println(difficulties[half] - difficulties[half - 1]);
    }
}
