package ABC.ABC058.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[][] count = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (final char c : scanner.next().toCharArray()) {
                count[i][c - 'a']++;
            }
        }

        final int[] array = new int[26];
        for (int i = 0; i < 26; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                min = Math.min(min, count[j][i]);
            }
            array[i] = min;
        }

        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i]; j++) {
                builder.append((char) (i + 'a'));
            }
        }

        System.out.println(builder);
    }
}
