package ABC.ABC082.C;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, AtomicInteger> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            countMap.computeIfAbsent(scanner.nextInt(), count -> new AtomicInteger())
                    .incrementAndGet();
        }

        int answer = 0;
        for (final Map.Entry<Integer, AtomicInteger> map : countMap.entrySet()) {
            if (map.getValue().get() >= map.getKey()) {
                answer += map.getValue().get() - map.getKey();
            } else {
                answer += map.getValue().get();
            }
        }

        System.out.println(answer);
    }
}
