package MITSUI2019.D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String[] array = scanner.next().split("");

        final Map<Integer, Set<String>> indexTwoDigitsMap = new HashMap<>();
        final Map<String, AtomicInteger> twoDigitsCountMap = new HashMap<>();
        final HashSet[] twoDigitsArray = new HashSet[n];
        final int[] countArray = new int[100];

        // n
        for (int i = 0; i < array.length - 1; i++) {
            /*
            // n
            for (int j = i + 1; j < array.length; j++) {
                indexTwoDigitsMap.computeIfAbsent(i, list -> new HashSet<>())
                        .add(array[i] + array[j]);
            }
             */
            final HashSet tmp = new HashSet();
            for (int j = i + 1; j < array.length; j++) {
                tmp.add(array[i] + array[j]);
            }
            twoDigitsArray[i] = tmp;

            // 100
            /*
            for (String twoDigits : indexTwoDigitsMap.get(i)) {
                twoDigitsCountMap.computeIfAbsent(twoDigits, count -> new AtomicInteger())
                        .incrementAndGet();
            }
             */
            for (Object o : tmp) {
                final int index = Integer.parseInt((String) o);
                countArray[index]++;

            }
        }

        final Set<String> answerSet = new HashSet<>();
        // n
        for (int i = 0; i < array.length - 2; i++) {
            final Set<String> twoDigits = indexTwoDigitsMap.get(i);
            // 100
            /*
            for (String twoDigit : twoDigits) {
                if (twoDigitsCountMap.get(twoDigit).decrementAndGet() == 0) {
                    twoDigitsCountMap.remove(twoDigit);
                }
            }
             */

            final HashSet set = twoDigitsArray[i];
            for (final Object o : set) {
                final int index = Integer.parseInt((String) o);
                countArray[index]--;
            }

            // 100
            /*
            for (String twoDigit : twoDigitsCountMap.keySet()) {
                answerSet.add(array[i] + twoDigit);
            }
             */

            for (int i1 = 0; i1 < countArray.length; i1++) {
                if (countArray[i] == 0) {
                    continue;
                }

                answerSet.add(array[i] + String.format("%02d", i));
            }
        }

        System.out.println(answerSet.size());
    }
}
