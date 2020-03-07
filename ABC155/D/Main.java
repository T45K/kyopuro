package ABC155.D;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final Comparator<Long> upperBoundComparator = (x, y) -> x > y ? 1 : -1;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextLong();

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

        final List<Long> minusList = list.subList(0, (int) numOfMinus).stream()
                .map(i -> -i)
                .collect(Collectors.toList());
        final List<Long> plusList = list.subList(list.size() - (int) numOfPlus, list.size());

        long minusPair = numOfMinus * numOfPlus;
        long zeroPair = numOfZero * (numOfMinus + numOfPlus) + numOfZero * (numOfZero - 1) / 2;

        final long answer;
        if (k <= minusPair) {
            answer = -binarySearchForMinus(minusList, plusList, 0, (long) 1e18, k);
        } else if (k <= minusPair + zeroPair) {
            answer = 0;
        } else {
            Collections.reverse(minusList);
            answer = binarySearchForPlus(minusList, plusList, 0, (long) 1e18, k - (minusPair + zeroPair));
        }

        System.out.println(answer);
    }

    private static long binarySearchForMinus(final List<Long> minusList, final List<Long> plusList, final long begin, final long end, final long k) {
        if (end - begin <= 1) {
            return end;
        }

        final long mid = (end + begin) / 2;
        long count = 0;
        for (final long value : minusList) {
            final int result = Collections.binarySearch(plusList, mid / value, upperBoundComparator);
            final int inverted = result >= 0 ? result : ~result;
            count += plusList.size() - inverted;
        }

        if (count < k) {
            return binarySearchForMinus(minusList, plusList, begin, mid, k);
        } else {
            return binarySearchForMinus(minusList, plusList, mid, end, k);
        }
    }

    private static long binarySearchForPlus(final List<Long> minusList, final List<Long> plusList, final long begin, final long end, final long k) {
        if (end - begin <= 1) {
            return end;
        }

        final long mid = (end + begin) / 2;
        long count = (count(minusList, mid) + count(plusList, mid)) / 2;

        if (count >= k) {
            return binarySearchForPlus(minusList, plusList, begin, mid, k);
        } else {
            return binarySearchForPlus(minusList, plusList, mid, end, k);
        }
    }

    private static long count(final List<Long> list, final long mid) {
        long count = 0;
        for (int i = 0; i < list.size(); i++) {
            final long value = list.get(i);
            final int result = Collections.binarySearch(list, mid / value, upperBoundComparator);
            final int invert = result >= 0 ? result : ~result;
            count += invert;
            if (invert > i) {
                count--;
            }
        }
        return count;
    }
}
