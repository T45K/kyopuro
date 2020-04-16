package AtCoder.ARC.ARC054.B;

import java.util.Scanner;

import static java.lang.Math.log;
import static java.lang.Math.pow;

/*
数学 微分するだけ
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final double p = scanner.nextDouble();

        final double tmp = 2d * p * log(2d) / 3d;
        if (tmp <= 1) {
            System.out.println(p);
            return;
        }
        final double min = 3d * log(tmp) / (2d * log(2d));
        System.out.println(min + p / pow(2, min / 1.5d));
    }
}
