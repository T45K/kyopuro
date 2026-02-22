package ABC.ABC015.D;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int w = scanner.nextInt();
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final Screenshot[] screenshots = new Screenshot[n];
        for (int i = 0; i < n; i++) {
            screenshots[i] = new Screenshot(scanner.nextInt(), scanner.nextInt());
        }

        final int[][][] dpCube = new int[n][w + 1][k + 1];
        for (final int[][] table : dpCube) {
            for (final int[] array : table) {
                Arrays.fill(array, -1);
            }
        }
        dpCube[0][0][0] = 0;
        if (screenshots[0].width <= w) {
            dpCube[0][screenshots[0].width][1] = screenshots[0].importance;
        }
        for (int x = 1; x < n; x++) {
            for (int y = 0; y <= w; y++) {
                System.arraycopy(dpCube[x - 1][y], 0, dpCube[x][y], 0, k + 1);
            }

            final Screenshot screenshot = screenshots[x];
            for (int y = 0; y <= w - screenshot.width; y++) {
                for (int z = 0; z < k; z++) {
                    if (dpCube[x - 1][y][z] >= 0) {
                        dpCube[x][y + screenshot.width][z + 1] = Math.max(dpCube[x][y + screenshot.width][z + 1], dpCube[x - 1][y][z] + screenshot.importance);
                    }
                }
            }
        }

        int max = 0;
        for (final int[] array : dpCube[n - 1]) {
            for (final int value : array) {
                max = Math.max(max, value);
            }
        }
        System.out.println(max);
    }

    static class Screenshot {
        final int width;
        final int importance;

        Screenshot(final int width, final int importance) {
            this.width = width;
            this.importance = importance;
        }
    }
}
