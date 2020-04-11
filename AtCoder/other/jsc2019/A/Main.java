package AtCoder.other.jsc2019.A;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int[] rice = new int[n];
        final int[] fish = new int[m];

        for (int i = 0; i < n; i++) {
            rice[i] = scanner.nextInt();
        }

        for (int i = 0; i < m; i++) {
            fish[i] = scanner.nextInt();
        }

        final HashMap<Integer, Pair> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                final int weight = rice[i] + fish[j];
                final Pair pair = map.get(weight);
                if (pair != null) {
                    System.out.println(i + " " + j + " " + pair.a + " " + pair.b);
                    return;
                }

                map.put(weight, new Pair(i, j));
            }
        }

        System.out.println(-1);
    }

    static class Pair {
        int a;
        int b;

        Pair(final int a, final int b) {
            this.a = a;
            this.b = b;
        }
    }
}
