package MITSUI2019.E;

import java.util.Scanner;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[] colors = new int[3];
        long sum = 1;
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            long tmp = 0;
            int index = 0;
            for (int j = 0; j < colors.length; j++) {
                if (colors[j] == a) {
                    tmp++;
                    index = j;
                }
            }
            sum *= tmp;
            sum %= MOD;
            colors[index]++;
        }
        System.out.println(sum);
    }
}
