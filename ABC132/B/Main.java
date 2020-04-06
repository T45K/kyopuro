package ABC132.B;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();
        final int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        int res = 0;
        for (int i = 1; i < length - 1; i++) {
            if (array[i] > array[i - 1] && array[i + 1] > array[i] || array[i] < array[i - 1] && array[i + 1] < array[i]) {
                res++;
            }
        }

        System.out.println(res);
    }
}
