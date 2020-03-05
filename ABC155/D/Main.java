package ABC155.D;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    // TODO solve
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();

        final List<Long> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextLong())
                .sorted()
                .collect(Collectors.toList());

        long numOfMinus = 0;
        long numOfZero = 0;
        long numOfPlus = 0;
        for (final long value : list) {
            if (value > 0) {
                numOfPlus++;
            } else if (value == 0) {
                numOfZero++;
            } else {
                numOfMinus++;
            }
        }

        final List<Long> minusList = IntStream.range(0, (int) numOfMinus)
                .mapToObj(list::get)
                .collect(Collectors.toList());
        final List<Long> plusList = IntStream.range(list.size() - (int) numOfPlus, list.size())
                .mapToObj(list::get)
                .collect(Collectors.toList());

        long minusPair = numOfMinus * numOfPlus;
        long zeroPair = numOfZero * (numOfMinus + numOfPlus) + numOfZero * (numOfZero - 1) / 2;

        final long answer;
        if (k <= minusPair) {
            answer = binarySearchForMinus(minusList, plusList, (long) -1e18, -1, k);
        } else if (k <= minusPair + zeroPair) {
            answer = 0;
        } else {
            final List<Long> minusPlusList = minusList.stream()
                    .map(i -> -i)
                    .sorted()
                    .collect(Collectors.toList());

            answer = binarySearchForPlus(minusPlusList, plusList, 1, (long) 1e18, k - (minusPair + zeroPair));
        }

        System.out.println(answer);
    }

    private static long binarySearchForPlus(final List<Long> minusList, final List<Long> plusList, final long ng, final long ok, final long k) {
        if (ok - ng <= 1) {
            return ok;
        }

        long mid = (ok + ng) / 2;
        final long sumOfMinusList = calcSum(minusList, mid);
        final long sumOfPlusList = calcSum(plusList, mid);
        final long sum = sumOfMinusList + sumOfPlusList;

        if (sum < k) {
            return binarySearchForPlus(minusList, plusList, mid, ok, k);
        } else {
            return binarySearchForPlus(minusList, plusList, ng, mid, k);
        }
    }

    private static long calcSum(final List<Long> list, final long mid) {
        if (list.size() <= 1) {
            return 0;
        }
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            final long div = mid / list.get(i);
            final int idx = duplicatedMaxBinarySearch(list, div, 0, list.size());
            if (idx == -1) {
                continue;
            }
            sum += idx <= i ? idx + 1 : idx;
        }
        return sum / 2;
    }

    private static long binarySearchForMinus(final List<Long> minusList, final List<Long> plusList, final long ng, final long ok, final long k) {
        if (ok - ng <= 1) {
            return ok;
        }

        long mid = (ok + ng) / 2;
        long sum = 0;
        for (final long minusValue : minusList) {
            final long tmp = (mid + minusValue + 1) / minusValue;
            sum += plusList.size() - duplicatedMinBinarySearch(plusList, tmp, 0, plusList.size());
        }

        if (sum <= k) {
            return binarySearchForMinus(minusList, plusList, mid, ok, k);
        } else {
            return binarySearchForMinus(minusList, plusList, ng, mid, k);
        }
    }

    private static int duplicatedMaxBinarySearch(final List<Long> list, final long value, final int min, final int max) {
        if (value < list.get(0)) {
            return -1;
        }

        if (value > list.get(list.size() - 1)) {
            return list.size() - 1;
        }

        if (Math.abs(max - min) <= 1) {
            return min;
        }

        final int mid = (min + max) / 2;
        if (list.get(mid) <= value) {
            return duplicatedMaxBinarySearch(list, value, mid, max);
        } else {
            return duplicatedMaxBinarySearch(list, value, min, mid);
        }
    }

    private static int duplicatedMinBinarySearch(final List<Long> list, final long value, final int min, final int max) {
        if (value > list.get(list.size() - 1)) {
            return list.size();
        }
        if (Math.abs(max - min) <= 1) {
            return min;
        }

        final int mid = (min + max) / 2;
        if (list.get(mid) < value) {
            return duplicatedMinBinarySearch(list, value, mid, max);
        } else {
            return duplicatedMinBinarySearch(list, value, min, mid);
        }
    }
}
