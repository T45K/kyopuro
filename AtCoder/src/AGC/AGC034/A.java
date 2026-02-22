package AGC.AGC034;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();
        final int d = scanner.nextInt();

        scanner.nextLine();
        final char[] road = scanner.nextLine().toCharArray();

        if (d > c) {
            if (check(road, a, c) && check(road, b, d)) {
                System.out.println("Yes");
            } else
                System.out.println("No");
        } else {
            if (!(check(road, a, c) && doubleCheck(road, b, d))) {
                System.out.println("No");
                return;
            }

            if (check(road, b, d)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    private static boolean check(char[] roads, final int begin, final int end) {
        if (roads[begin - 1] == '#' || roads[end - 1] == '#') return false;
        for (int i = begin; i < end; i++) {
            if (roads[i] == '#' && roads[i - 1] == '#') {
                return false;
            }
        }

        return true;
    }

    private static boolean doubleCheck(final char[] road, final int begin, final int end) {
        int counter = 0;
        for (int i = begin - 2; i < end + 1; i++) {
            if (road[i] == '.') {
                counter++;
                if (counter == 3) {
                    return true;
                }
            } else {
                counter = 0;
            }
        }
        return false;
    }
}
