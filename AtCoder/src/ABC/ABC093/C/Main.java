package ABC.ABC093.C;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[] array = new int[3];
        for (int i = 0; i < 3; i++) {
            array[i] = scanner.nextInt();
        }

        Arrays.sort(array);
        final int a = array[0];
        final int b = array[1];
        final int c = array[2];

        int result = 0;
        result += c - b;
        if ((b - a) % 2 == 1) {
            result += 2;
        }
        result += (b - a) / 2;

        System.out.println(result);
    }
}
