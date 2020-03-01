package ABC064.C;

import java.util.Scanner;

public class Main {
    private static final int GRAY = 0;
    private static final int BROWN = 1;
    private static final int GREEN = 2;
    private static final int WATER = 3;
    private static final int BLUE = 4;
    private static final int YELLOW = 5;
    private static final int ORANGE = 6;
    private static final int RED = 7;
    private static final int OTHERS = 8;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] colors = new int[9];
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            if (a < 400) {
                colors[GRAY]++;
            } else if (a < 800) {
                colors[BROWN]++;
            } else if (a < 1200) {
                colors[GREEN]++;
            } else if (a < 1600) {
                colors[WATER]++;
            } else if (a < 2000) {
                colors[BLUE]++;
            } else if (a < 2400) {
                colors[YELLOW]++;
            } else if (a < 2800) {
                colors[ORANGE]++;
            } else if (a < 3200) {
                colors[RED]++;
            } else {
                colors[OTHERS]++;
            }
        }

        int min = 0;
        int max;
        for (int i = 0; i <= RED; i++) {
            if (colors[i] > 0) {
                min++;
            }
        }

        max = min + colors[OTHERS];
        min = min == 0 && colors[OTHERS] > 0 ? 1 : min;

        System.out.println(min + " " + max);
    }
}
