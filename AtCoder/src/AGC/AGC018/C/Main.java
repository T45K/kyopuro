package AGC.AGC018.C;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final int[][] table = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        final Set<Integer> selectables = IntStream.rangeClosed(1, m)
                .boxed()
                .collect(Collectors.toSet());
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            final Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    final int sport = table[j][k];
                    if (selectables.contains(sport)) {
                        map.compute(sport, (key, v) -> v == null ? 1 : v + 1);
                        break;
                    }
                }
            }

            int max = -1;
            int maxSport = -1;
            for (final Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (max < entry.getValue()) {
                    max = entry.getValue();
                    maxSport = entry.getKey();
                }
            }
            min = Math.min(max, min);
            selectables.remove(maxSport);
        }

        System.out.println(min);
    }
}
