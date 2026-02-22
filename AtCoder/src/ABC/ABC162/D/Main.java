package ABC.ABC162.D;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();

        final Map<Character, Integer> map = new HashMap<>();
        map.put('R', 0);
        map.put('G', 1);
        map.put('B', 2);

        final long[][] colors = new long[3][n];
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1) {
                for (int j = 0; j < 3; j++) {
                    colors[j][i] = colors[j][i + 1];
                }
            }

            colors[map.get(s.charAt(i))][i]++;
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                final int a = map.get(s.charAt(i));
                final int b = map.get(s.charAt(j));
                if (a == b) {
                    continue;
                }

                final int k = 2 * j - i;
                final int c = 3 - (a + b);
                if (k >= n) {
                    sum += colors[c][j + 1];
                } else if (k == n - 1) {
                    sum += colors[c][j + 1] - colors[c][n - 1];
                } else {
                    sum += colors[c][j + 1] - colors[c][k] + colors[c][k + 1];
                }
            }
        }
        System.out.println(sum);
    }
}
