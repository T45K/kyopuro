package MITSUI2019.D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    // TODO fix
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
        final String[] array = scanner.next().split("");

        final Map<Integer, Set<String>> indexTwoDigitsMap = new HashMap<>();
        final Map<String, AtomicInteger> twoDigitsCountMap = new HashMap<>();

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                indexTwoDigitsMap.computeIfAbsent(i, list -> new HashSet<>())
                        .add(array[i] + array[j]);
            }

            for (String twoDigits : indexTwoDigitsMap.get(i)) {
                twoDigitsCountMap.computeIfAbsent(twoDigits, count -> new AtomicInteger())
                        .incrementAndGet();
            }
        }

        final Set<String> answerSet = new HashSet<>();
        for (int i = 0; i < array.length - 2; i++) {
            final Set<String> twoDigits = indexTwoDigitsMap.get(i);
            for (String twoDigit : twoDigits) {
                if (twoDigitsCountMap.get(twoDigit).decrementAndGet() == 0) {
                    twoDigitsCountMap.remove(twoDigit);
                }
            }

            for (String twoDigit : twoDigitsCountMap.keySet()) {
                answerSet.add(array[i] + twoDigit);
            }
        }

        System.out.println(answerSet.size());
    }
}
