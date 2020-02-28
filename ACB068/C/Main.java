package ACB068.C;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            map.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
        }

        final boolean answer = map.get(1).stream()
                .flatMap(i -> Optional.ofNullable(map.get(i)).orElse(Collections.emptyList()).stream())
                .anyMatch(i -> i == n);

        System.out.println(answer ? "POSSIBLE" : "IMPOSSIBLE");
    }
}
