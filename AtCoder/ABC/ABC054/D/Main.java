package AtCoder.ABC.ABC054.D;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int ma = scanner.nextInt();
        final int mb = scanner.nextInt();

        final int[] aArray = new int[n];
        final int[] bArray = new int[n];
        final int[] cArray = new int[n];
        for (int i = 0; i < n; i++) {
            aArray[i] = scanner.nextInt();
            bArray[i] = scanner.nextInt();
            cArray[i] = scanner.nextInt();
        }

        final int[][][] cube = new int[n][401][401];
        init(cube);
        cube[0][0][0] = 0;
        cube[0][aArray[0]][bArray[0]] = cArray[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < cube[i].length; j++) {
                System.arraycopy(cube[i - 1][j], 0, cube[i][j], 0, cube[i][j].length);
            }

            for (int j = 0; j < 401; j++) {
                for (int k = 0; k < 401; k++) {
                    if (cube[i - 1][j][k] == Integer.MAX_VALUE) {
                        continue;
                    }
                    cube[i][j + aArray[i]][k + bArray[i]] = Math.min(cube[i - 1][j][k] + cArray[i], cube[i - 1][j + aArray[i]][k + bArray[i]]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; ma * i < 401 && mb * i < 401; i++) {
            answer = Math.min(answer, cube[n - 1][ma * i][mb * i]);
        }

        System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
    }

    private static void init(final int[][][] cube) {
        for (final int[][] table : cube) {
            for (final int[] array : table) {
                Arrays.fill(array, Integer.MAX_VALUE);
            }
        }
    }
}
