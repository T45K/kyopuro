package ABC.ABC084.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final Station[] stations = new Station[n - 1];
        for (int i = 0; i < n - 1; i++) {
            stations[i] = new Station(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }

        for (int i = 0; i < n - 1; i++) {
            int sum = stations[i].start + stations[i].time;
            for (int j = i + 1; j < n - 1; j++) {
                final Station station = stations[j];
                final int tmp = (sum + station.interval - 1) / station.interval * station.interval;
                sum = Math.max(tmp, station.start) + station.time;
            }
            System.out.println(sum);
        }
        System.out.println(0);
    }

    static class Station {
        int time;
        int start;
        int interval;

        Station(final int time, final int start, final int interval) {
            this.time = time;
            this.start = start;
            this.interval = interval;
        }
    }
}
