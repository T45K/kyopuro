package ABC.ABC062.D;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final List<Long> list = IntStream.range(0, 3 * n)
                .mapToObj(i -> scanner.nextLong())
                .collect(Collectors.toList());

        final long[] sumOfFormer = new long[n + 1];
        sumOfFormer[0] = list.stream().limit(n).mapToLong(Long::longValue).sum();
        final PriorityQueue<Long> formerQueue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            formerQueue.add(list.get(i));
        }
        for (int i = n, j = 1; i < 2 * n; i++, j++) {
            final long minOfQueue = Optional.ofNullable(formerQueue.poll()).orElseThrow(RuntimeException::new);
            final long tmp = list.get(i);
            if (tmp > minOfQueue) {
                formerQueue.add(tmp);
                sumOfFormer[j] = sumOfFormer[j - 1] + tmp - minOfQueue;
            } else {
                formerQueue.add(minOfQueue);
                sumOfFormer[j] = sumOfFormer[j - 1];
            }
        }

        final long[] sumOfLatter = new long[n + 1];
        sumOfLatter[n] = list.stream().skip(2 * n).mapToLong(Long::longValue).sum();
        final PriorityQueue<Long> latterQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < n; i++) {
            latterQueue.add(list.get(i + 2 * n));
        }
        for (int i = 2 * n - 1, j = n - 1; i >= n; i--, j--) {
            final long maxOfQueue = Optional.ofNullable(latterQueue.poll()).orElseThrow(RuntimeException::new);
            final long tmp = list.get(i);
            if (tmp < maxOfQueue) {
                latterQueue.add(tmp);
                sumOfLatter[j] = sumOfLatter[j + 1] + tmp - maxOfQueue;
            } else {
                latterQueue.add(maxOfQueue);
                sumOfLatter[j] = sumOfLatter[j + 1];
            }
        }

        long max = Long.MIN_VALUE;
        for (int i = 0; i < n + 1; i++) {
            max = Math.max(max, sumOfFormer[i] - sumOfLatter[i]);
        }

        System.out.println(max);
    }
}
