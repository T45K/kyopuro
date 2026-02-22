package ABC.ABC138.C;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final double[] doubles = new double[n];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = scanner.nextDouble();
        }

        Arrays.sort(doubles);
        double res = doubles[0];
        for (int i = 1; i < n; i++) {
            res = (res + doubles[i]) / 2;
        }

        System.out.println(res);
    }
}
