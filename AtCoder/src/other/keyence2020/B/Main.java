package keyence2020.B;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final List<Robot> robots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int point = scanner.nextInt();
            final int length = scanner.nextInt();
            robots.add(new Robot(point - length, point + length));
        }

        robots.sort(Comparator.comparing(robot -> robot.right));

        int count = 0;
        long rightest = -1000000000;
        for (int i = 0; i < n; i++) {
            if (rightest <= robots.get(i).left) {
                count++;
                rightest = robots.get(i).right;
            }
        }

        System.out.println(count);
    }

    static class Robot {
        long left;
        long right;

        Robot(final long left, final long right) {
            this.left = left;
            this.right = right;
        }
    }
}
