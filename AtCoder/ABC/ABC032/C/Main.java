package AtCoder.ABC.ABC032.C;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
典型的なしゃくとり法 簡単 しゃくとり法の実装に慣れてなかったので時間がかかった
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());

        final boolean zeroIncluded = list.stream().anyMatch(i -> i == 0);
        if (zeroIncluded) {
            System.out.println(n);
            return;
        }

        final boolean impossible = list.stream().allMatch(i -> i > k);
        if (impossible) {
            System.out.println(0);
            return;
        }

        long product = 1;
        int left = -1;
        int right = -1;
        int max = 0;

        while (++right < list.size()) {
            product *= list.get(right);
            if (product > k) {
                while ((product /= list.get(++left)) > k) ;
            }
            max = Math.max(max, right - left);
        }

        System.out.println(max);
    }
}
