package AGC041.B;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long m = scanner.nextInt();
        final long v = scanner.nextInt();
        final int p = scanner.nextInt();

        final List<Long> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextLong())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        final int answer = binarySearch(p - 1, list.size(), list, m, v, p) + 1;
        System.out.println(answer);
    }

    private static int binarySearch(final int begin, final int end, final List<Long> list, final long m, final long v, final int p) {
        if (end - begin <= 1) {
            return begin;
        }

        final int mid = (begin + end) / 2;
        if (isOk(mid, p, list, m, v)) {
            return binarySearch(mid, end, list, m, v, p);
        } else {
            return binarySearch(begin, mid, list, m, v, p);
        }
    }

    private static boolean isOk(final int index, final int p, final List<Long> list, final long m, final long v) {
        if (list.get(index) + m < list.get(p - 1)) {
            return false;
        }

        final long max = list.get(index) + m;
        final long formerSum = IntStream.range(p - 1, index)
                .mapToObj(i -> max - list.get(i))
                .mapToLong(Long::longValue)
                .sum();

        final long latterSum = (list.size() - index - 1) * m;

        return m + (p - 1) * m + formerSum + latterSum >= m * v;
    }
}
