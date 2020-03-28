package MITSUI2019.F;

import java.util.Scanner;

public class Main {
    // TODO solve
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long t1 = scanner.nextInt();
        final long t2 = scanner.nextInt();
        final long tmp1 = scanner.nextLong();
        final long tmp2 = scanner.nextLong();
        final long tmp3 = scanner.nextLong();
        final long tmp4 = scanner.nextLong();

        final long a1;
        final long a2;
        final long b1;
        final long b2;
        if (tmp1 > tmp3) {
            a1 = tmp1;
            a2 = tmp2;
            b1 = tmp3;
            b2 = tmp4;
        } else {
            b1 = tmp1;
            b2 = tmp2;
            a1 = tmp3;
            a2 = tmp4;
        }

        final long distanceInT1 = (a1 - b1) * t1;
        final long distanceInT2 = b2 * t2 + b1 * t1 - (a2 * t2 - b1 * t1);
        if (distanceInT2 == 0) {
            System.out.println("infinity");
            return;
        }
        if (distanceInT2 < 0) {
            System.out.println(0);
            return;
        }
        if (distanceInT1 < distanceInT2) {
            System.out.println(1);
            return;
        }
        final long diff = distanceInT1 - distanceInT2;
        System.out.println(distanceInT1 / distanceInT2);
    }
}
