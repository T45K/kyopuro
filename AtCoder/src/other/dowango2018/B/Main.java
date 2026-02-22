package other.dowango2018.B;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextInt();

        final List<Long> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextLong())
                .collect(Collectors.toList());

        final int all = n * (n + 1) / 2;
        final List<Long> sums = new ArrayList<>(all / 2);
        for (int i = 0; i < n; i++) {
            long sum = list.get(i);
            sums.add(sum);
            for (int j = i + 1; j < n; j++) {
                sum += list.get(j);
                sums.add(sum);
            }
        }
        Collections.sort(sums);
        Collections.reverse(sums);

        final int tableLength = Long.toBinaryString(sums.get(0)).length();
        final boolean[][] table = new boolean[all][tableLength];
        for (int i = 0; i < all; i++) {
            final long tmp = sums.get(i);
            final String s = Long.toBinaryString(tmp);
            for (int j = s.length() - 1; j >= 0; j--) {
                final char c = s.charAt(j);
                table[i][s.length() - j - 1] = c == '1';
            }
        }

        final Set<Integer> exceptIndices = new HashSet<>();
        final List<Integer> digits = new ArrayList<>();
        for (int i = tableLength - 1; i >= 0; i--) {
            if (exceptIndices.size() > all - k) {
                break;
            }
            final Set<Integer> trues = new HashSet<>();
            final Set<Integer> falsies = new HashSet<>();
            for (int j = 0; j < all; j++) {
                if (exceptIndices.contains(j)) {
                    continue;
                }
                if (table[j][i]) {
                    trues.add(j);
                } else {
                    falsies.add(j);
                }
            }
            if (trues.size() >= k) {
                digits.add(i);
                exceptIndices.addAll(falsies);
            }
        }

        long sum = 0;
        for (final int value : digits) {
            sum += Math.pow(2, value);
        }
        System.out.println(sum);
    }
}
