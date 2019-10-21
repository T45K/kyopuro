package ABC098.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[][] array = new int[n][20];

        for (int i = 0; i < n; i++) {
            final int tmp = scanner.nextInt();
            final char[] binary = Integer.toBinaryString(tmp).toCharArray();
            for (int j = 0; j < binary.length; j++) {
                array[i][j] = binary[binary.length - j - 1] - '0';
            }
        }

        final int[] prePreOneIndex = new int[20];
        final int[] preOneIndex = new int[20];

        for (int i = 0; i < 20; i++) {
            preOneIndex[i] = -1;
            prePreOneIndex[i] = -1;
        }

        long answer = 0;
        for (int i = 0; i < n; i++) {
            final int[] binary = array[i];
            int length = Integer.MAX_VALUE;
            for (int j = 0; j < 20; j++) {
                if (binary[j] == 0) {
                    length = Math.min(length, i - prePreOneIndex[j]);
                } else {
                    length = Math.min(length, i - preOneIndex[j]);
                    prePreOneIndex[j] = preOneIndex[j];
                    preOneIndex[j] = i;
                }
            }
            answer += length;
        }

        System.out.println(answer);
    }
}
