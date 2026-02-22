package ARC.ARC049.B;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 座標 最長距離 考察できれば一瞬
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final List<Takahashi> list = IntStream.range(0, n)
                .mapToObj(i -> new Takahashi(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
                .collect(Collectors.toList());

        double max = -1;
        for (int i = 0; i < list.size() - 1; i++) {
            final Takahashi base1 = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                final Takahashi base2 = list.get(j);
                final double tmpX = base2.c * Math.abs(base1.x - base2.x) / (base1.c + base2.c) + base1.x;
                final double distanceX = Math.abs(base1.x - tmpX) * base1.c;
                final double tmpY = base2.c * Math.abs(base1.y - base2.y) / (base1.c + base2.c) + base1.y;
                final double distanceY = Math.abs(base1.y - tmpY) * base1.c;
                max = Math.max(max, Math.max(distanceX, distanceY));
            }
        }

        System.out.println(max);
    }

    private static class Takahashi {
        final double x;
        final double y;
        final double c;

        Takahashi(final int x, final int y, final int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}
