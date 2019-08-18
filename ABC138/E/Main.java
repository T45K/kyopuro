package ABC138.E;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();
        final String t = scanner.nextLine();

        final Set<Character> sSet = new HashSet<>();
        final Set<Character> tSet = new HashSet<>();

        for (final char c : s.toCharArray()) {
            sSet.add(c);
        }

        for (final char c : t.toCharArray()) {
            tSet.add(c);
        }

        if (!sSet.containsAll(tSet)) {
            System.out.println(-1);
            return;
        }

        final Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(s.charAt(i), e -> new ArrayList<>()).add(i);
        }

        long counter = 0;
        int currentIndex = -1;
        for (int i = 0; i < t.length(); i++) {
            final List<Integer> indexList = map.get(t.charAt(i));
            final int indexInIndexes = extendedBinarySearch(indexList, currentIndex);
            if (indexInIndexes == indexList.size()) {
                counter++;
                currentIndex = indexList.get(0);
            } else {
                currentIndex = indexList.get(indexInIndexes);
            }

        }

        System.out.println(counter * s.length() + 1 + currentIndex);
    }

    @SuppressWarnings("unchecked")
    private static int extendedBinarySearch(final Object indexes, final int value) {
        final int rawIndex;
        if (indexes instanceof int[]) {
            rawIndex = Arrays.binarySearch((int[]) indexes, value);
        } else {
            rawIndex = Collections.binarySearch((List<Integer>) indexes, value);
        }

        if (rawIndex >= 0) {
            return rawIndex + 1;
        }

        return -(rawIndex + 1);
    }
}
