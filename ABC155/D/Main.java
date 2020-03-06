package ABC155.D;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    // TODO solve
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
            answer = -binarySearchForMinus(minusList, plusList, 0, (long) 1e18, k - numOfMinus + 1);
        } else if (k <= minusPair + zeroPair) {
            answer = 0;
        } else {
            Collections.reverse(minusList);
            final int max = list.size() * (list.size() - 1) / 2;
            answer = binarySearchForPlus(minusList, plusList, 0, (long) 1e18, max - k + 1);
        }

        System.out.println(answer);
    }

    private static long binarySearchForMinus(final List<Long> minusList, final List<Long> plusList, final long begin, final long end, final long k) {
        if (end - begin <= 1) {
            return end;
        }

        final long mid = (end + begin) / 2;
        int tmpPlusIndex = 0;
        int count = 0;
        for (final long value : minusList) {
            if (tmpPlusIndex == plusList.size()) {
                count += tmpPlusIndex;
                continue;
            }

            while (tmpPlusIndex < plusList.size() && value * plusList.get(tmpPlusIndex) <= mid) {
                tmpPlusIndex++;
            }
            count += tmpPlusIndex;
        }

        if (count >= k) {
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
        int count = count(minusList, mid) + count(plusList, mid);

        if (count < k) {
            return binarySearchForPlus(minusList, plusList, begin, mid, k);
        } else {
            return binarySearchForPlus(minusList, plusList, mid, end, k);
        }
    }

    private static int count(final List<Long> list, final long mid) {
        int count = 0;
        int tmpIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            final long value = list.get(i);
            if (tmpIndex == list.size()) {
                count += tmpIndex;
                continue;
            }

            while (tmpIndex < list.size() && value * list.get(list.size() - tmpIndex - 1) > mid) {
                tmpIndex++;
            }
            count += tmpIndex;
            if (i >= list.size() - tmpIndex) {
                count--;
            }
        }
        return count / 2;
    }
}
