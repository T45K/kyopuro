package AtCoder.ABC.ABC161.E;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
貪欲 前からと後ろから貪欲して結果を付き合わせる
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int c = scanner.nextInt();
        final String s = scanner.next();
        final List<Integer> workableDays = IntStream.range(0, n)
                .filter(i -> s.charAt(i) == 'o')
                .boxed()
                .collect(Collectors.toList());

        final int[] frontGreedy = new int[k];
        frontGreedy[0] = workableDays.get(0);
        for (int i = 1; i < k; i++) {
            final int next = Collections.binarySearch(workableDays, frontGreedy[i - 1] + c + 1);
            frontGreedy[i] = workableDays.get(next >= 0 ? next : ~next);
        }

        final int[] backGreedy = new int[k];
        backGreedy[k - 1] = workableDays.get(workableDays.size() - 1);
        for (int i = k - 2; i >= 0; i--) {
            final int next = Collections.binarySearch(workableDays, backGreedy[i + 1] - c - 1);
            backGreedy[i] = workableDays.get(next >= 0 ? next : ~next - 1);
        }

        for (int i = 0; i < k; i++) {
            if (frontGreedy[i] == backGreedy[i]) {
                System.out.println(frontGreedy[i] + 1);
            }
        }
    }
}
