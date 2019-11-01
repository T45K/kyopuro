package ABC089.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final Set<String> march = new HashSet<>(Arrays.asList("MARCH".split("")));
        final Map<String, AtomicLong> countMap = new HashMap<>();
        final int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            final String prefix = scanner.nextLine().split("")[0];
            if (march.contains(prefix)) {
                countMap.computeIfAbsent(prefix, count -> new AtomicLong())
                        .incrementAndGet();
            }
        }

        final List<AtomicLong> counts = new ArrayList<>(countMap.values());
        long answer = 0;

        for (int i = 0; i < counts.size() - 2; i++) {
            for (int j = i + 1; j < counts.size() - 1; j++) {
                for (int k = j + 1; k < counts.size(); k++) {
                    answer += counts.get(i).get() * counts.get(j).get() * counts.get(k).get();
                }
            }
        }

        System.out.println(answer);
    }
}
