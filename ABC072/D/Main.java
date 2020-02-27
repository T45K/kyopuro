package ABC072.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = scanner.nextInt();
        }

        int count = 0;
        for (int i = 1; i < n; i++) {
            if (array[i] == i) {
                int tmp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = tmp;
                count++;
            }
        }
        if (array[n] == n) {
            count++;
        }

        System.out.println(count);
    }
}
