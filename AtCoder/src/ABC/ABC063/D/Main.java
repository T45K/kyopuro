package ABC.ABC063.D;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();

        final List<Long> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextLong())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        final int answer = binarySearch(list, 0, 2_000_000_000, a, b);
        System.out.println(answer);
    }

    private static int binarySearch(final List<Long> list, final int begin, final int end, final long a, final long b) {
        if (end - begin <= 1) {
            return end;
        }

        final int mid = (end + begin) / 2;
        final List<Long> bAttackedList = list.stream()
                .filter(i -> i > (mid * b))
                .map(i -> i - mid * b)
                .collect(Collectors.toList());

        if (bAttackedList.isEmpty()) {
            return binarySearch(list, begin, mid, a, b);
        }

        long count = mid;
        final long attack = a - b;
        for (final long remainedHP : bAttackedList) {
            count -= (remainedHP + attack - 1) / attack;
        }

        if (count >= 0) {
            return binarySearch(list, begin, mid, a, b);
        } else {
            return binarySearch(list, mid, end, a, b);
        }
    }
}
