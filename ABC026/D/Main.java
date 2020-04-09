package ABC026.D;

import java.util.Scanner;

/*
数値計算 数学 直線とサインカーブがどこで交わるかで場合分けするとしんどい 全パターンを試しても問題ない 力技
 */
public class Main {
    private static double a;
    private static double b;
    private static double c;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();

        final double zeroPoint = 100d / a;
        final double frequency = 2d / c;
        double i = 0;
        while (!(zeroPoint >= i && zeroPoint < i + frequency)) {
            i += frequency;
        }

        double answer;
        final double firstQuarter = i + frequency / 4d;
        final double thirdQuarter = i + frequency * 3d / 4d;

        answer = binarySearch(i, firstQuarter);
        if (answer >= 0) {
            System.out.println(answer);
            return;
        }

        answer = binarySearch(thirdQuarter, firstQuarter);
        if (answer >= 0) {
            System.out.println(answer);
            return;
        }

        System.out.println(binarySearch(thirdQuarter, i + frequency));
    }

    private static double binarySearch(double left, double right) {
        while (true) {
            final double mid = (left + right) / 2;
            if (mid == left) {
                return -1;
            }
            final double calc = a * mid + b * Math.sin(c * mid * Math.PI);
            if (Math.abs(calc - 100d) < 10e-7) {
                return mid;
            }
            if (calc < 100d) {
                left = mid;
            } else {
                right = mid;
            }
        }
    }
}
