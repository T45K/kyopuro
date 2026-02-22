package ABC.ABC081.C;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final Map<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            countMap.compute(scanner.nextInt(), (key, value) -> value == null ? value = 1 : value + 1);
        }

        final int answer = countMap.values().stream()
                .sorted()
                .limit(Math.max(countMap.size() - k, 0))
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(answer);
    }
}
