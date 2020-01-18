package keyence2020.B;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    // TODO solve
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final List<Robot> robots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            robots.add(new Robot(scanner.nextInt(), scanner.nextInt()));
        }

        robots.sort(Comparator.comparing(robot -> robot.length));
        int count = 0;
        for (int i = 0; i < robots.size() - 1; i++) {
            final Robot currentRobot = robots.get(i);
            final Robot nextRobot = robots.get(i + 1);

            if (currentRobot.point + currentRobot.length > nextRobot.point - nextRobot.length) {
                count++;
                if (currentRobot.point + currentRobot.length < nextRobot.point + nextRobot.length) {
                    i++;
                }
            }
        }

        System.out.println(n - count);
    }

    static class Robot {
        long point;
        long length;

        Robot(final long point, final long length) {
            this.point = point;
            this.length = length;
        }
    }
}
