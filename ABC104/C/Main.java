package ABC104.C;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int d = scanner.nextInt();
        final int g = scanner.nextInt() / 100;

        final int[] numbers = new int[d];
        final int[] bonuses = new int[d];

        for (int i = 0; i < d; i++) {
            numbers[i] = scanner.nextInt();
            bonuses[i] = scanner.nextInt() / 100;
        }

        final Set<Integer> checked = new HashSet<>();
        System.out.println(recursive(0, g, 0, numbers, bonuses, checked));
    }

    private static int recursive(final int index, final int target, final int cost, final int[] numbers, final int[] bonuses, final Set<Integer> set) {
        if (index == numbers.length) {
            if (target <= 0) {
                return cost;
            }

            if (set.size() == index) {
                return Integer.MAX_VALUE;
            }

            int maxIndex = -1;
            for (int i = 0; i < index; i++) {
                if (set.contains(i)) {
                    continue;
                }

                maxIndex = Math.max(maxIndex, i);
            }

            if ((maxIndex + 1) * numbers[maxIndex] < target) {
                return Integer.MAX_VALUE;
            }

            return cost + (target + maxIndex) / (maxIndex + 1);
        }

        final Set<Integer> checked = new HashSet<>(set);
        checked.add(index);

        return Math.min(recursive(index + 1, target, cost, numbers, bonuses, set)
                , recursive(index + 1, target - (index + 1) * numbers[index] - bonuses[index], cost + numbers[index], numbers, bonuses, checked));
    }
}
