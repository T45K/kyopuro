package AtCoder.ABC.ABC034.D;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数値計算 決め打ち二分探索 パーセントなので0~100をdouble型で探索 発想の時点で難しい
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final List<SaltWater> saltWaters = IntStream.range(0, n)
                .mapToObj(i -> new SaltWater(scanner.nextInt(), scanner.nextInt()))
                .collect(Collectors.toList());

        final double answer = binarySearch(0d, 100d, saltWaters, k);
        System.out.println(answer);
    }

    private static double binarySearch(final double left, final double right, final List<SaltWater> saltWaters, final int k) {
        final double mid = (left + right) / 2;
        final double result = calc(saltWaters, mid, k);
        if (result == mid) {
            return mid;
        } else if (result > mid) {
            return binarySearch(mid, right, saltWaters, k);
        } else {
            return binarySearch(left, mid, saltWaters, k);
        }
    }

    private static double calc(final List<SaltWater> saltWaters, final double comparison, final int k) {
        final List<SaltWater> list = saltWaters.stream()
                .sorted(((o1, o2) -> Double.compare(o2.water * (o2.percentage - comparison), o1.water * (o1.percentage - comparison))))
                .limit(k)
                .collect(Collectors.toList());

        double sumWater = 0;
        double sumExtraSalt = 0;
        for (final SaltWater saltWater : list) {
            sumWater += saltWater.water;
            sumExtraSalt += saltWater.water * saltWater.percentage;
        }

        return sumExtraSalt / sumWater;
    }

    private static class SaltWater {
        final double water;
        final double percentage;

        SaltWater(final int water, final int percentage) {
            this.water = water;
            this.percentage = percentage;
        }
    }
}
