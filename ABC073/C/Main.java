package ABC073.C;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final Map<Integer, Boolean> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            countMap.compute(scanner.nextInt(), (k, v) -> v == null ? v = true : !v);
        }

        final long answer = countMap.values().stream()
                .filter(Boolean::booleanValue)
                .count();

        System.out.println(answer);
    }
}
