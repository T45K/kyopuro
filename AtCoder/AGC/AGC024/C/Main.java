package AtCoder.AGC.AGC024.C;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数列操作 簡単 後ろから貪欲
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());

        if (list.get(0) != 0) {
            System.out.println(-1);
            return;
        }

        long sum = list.get(list.size() - 1);
        long tmp = list.get(list.size() - 1);
        for (int i = list.size() - 2; i >= 0; i--) {
            final int value = list.get(i);
            if (value < tmp - 1) {
                System.out.println(-1);
                return;
            }
            if (value > i) {
                System.out.println(-1);
                return;
            }
            if (value == tmp - 1) {
                tmp = Math.max(tmp - 1, 0);
                continue;
            }
            tmp = value;
            sum += value;
        }
        System.out.println(sum);
    }
}
