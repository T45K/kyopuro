package ABC076.D;

import java.util.Scanner;

public class Main {
    // TODO solve
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final double[] time = new double[n];
        for (int i = 0; i < n; i++) {
            time[i] = scanner.nextDouble();
        }

        final double[] velocity = new double[n];
        for (int i = 0; i < n; i++) {
            velocity[i] = scanner.nextDouble();
        }
    }
}
