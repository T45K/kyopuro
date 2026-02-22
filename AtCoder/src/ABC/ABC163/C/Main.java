package ABC.ABC163.C;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[n + 1];
        IntStream.rangeClosed(2, n)
                .mapToObj(i -> scanner.nextInt())
                .forEach(i -> array[i]++);

        for (int i = 1; i <= n; i++) {
            System.out.println(array[i]);
        }
    }
}
