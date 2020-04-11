package AtCoder.ABC.ABC076.D;

import java.util.Arrays;
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

        final double[] maxVelocity = new double[n + 1];
        for (int i = 0; i < n; i++) {
            maxVelocity[i] = scanner.nextDouble();
        }

        final double[] velocity = new double[n];
        final double[] distance = new double[n];

        for (int i = 0; i < n; i++) {
            final VelocityAndDistance velocityAndDistance = calc(velocity[i], maxVelocity[i + 1], maxVelocity[i], time[0]);
            if (velocityAndDistance == null) {
                for (int j = i - 1; j >= 0; j--) {

                }
                continue;
            }

            velocity[i] = velocityAndDistance.velocity;
            distance[i] = velocityAndDistance.distance;
        }

        final double answer = Arrays.stream(distance)
                .sum();
        System.out.println(answer);
    }

    // endVelocity = maxVelocity[i + 1]
    private static VelocityAndDistance calc(final double startVelocity, final double endVelocity, final double maxVelocity, final double time) {
        if (startVelocity - endVelocity > time) {
            return null;
        }

        if (endVelocity - startVelocity > time) {
            return new VelocityAndDistance(startVelocity + time, (startVelocity * 2 + time) * time / 2);
        }

        final double max = startVelocity + (endVelocity - startVelocity + time) / 2;
        if (max <= maxVelocity) {
            final double leftTime = max - startVelocity;
            final double left = leftTime * (max + startVelocity) / 2;
            final double right = (time - leftTime) * (max + endVelocity) / 2;
            return new VelocityAndDistance(endVelocity, left + right);
        }

        final double leftTime = maxVelocity - startVelocity;
        final double leftDistance = leftTime * (startVelocity + maxVelocity) / 2;
        final double rightTime = maxVelocity - endVelocity >= 0 ? maxVelocity - endVelocity : 0;
        final double rightDistance = rightTime * (endVelocity + maxVelocity) / 2;
        return new VelocityAndDistance(endVelocity, leftDistance + rightDistance + maxVelocity * (time - rightTime - leftTime));
    }

    static class VelocityAndDistance {
        double velocity;
        double distance;

        VelocityAndDistance(final double velocity, final double distance) {
            this.velocity = velocity;
            this.distance = distance;
        }
    }
}
