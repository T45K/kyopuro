package ABC126.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        double result = 0;

        for (int i = 1; i <= n; i++) {
            final double a = Math.log(k) / Math.log(2);
            final double b = Math.log(i) / Math.log(2);
            double ceil = Math.ceil(a - b);
            if (ceil < 0) {
                ceil = 0;
            }
            final double pow = Math.pow(0.5, ceil);
            result += pow;
        }

        System.out.println(result / n);
    }
}
