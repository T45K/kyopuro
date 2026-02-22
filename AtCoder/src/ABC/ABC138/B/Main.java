package ABC.ABC138.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final double[] doubles = new double[n];
        double mult = 1;
        for (int i = 0; i < doubles.length; i++) {
            final double v = scanner.nextDouble();
            doubles[i] = v;
            mult *= v;
        }

        double sum = 0;
        for (final double aDouble : doubles) {
            sum += mult / aDouble;
        }

        System.out.println(mult/sum);
    }
}
