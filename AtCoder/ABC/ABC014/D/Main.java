package AtCoder.ABC.ABC014.D;

import java.util.Scanner;

/*
いもす法をするだけ
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[] array = new int[1_000_001];
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            array[a]++;
            if (b < 1_000_000) {
                array[b + 1]--;
            }
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            array[i] += array[i - 1];
            max = Math.max(max, array[i]);
        }

        System.out.println(max);
    }
}
