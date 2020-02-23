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
        long zeroPair = numOfZero * (numOfMinus + numOfPlus);

        if (k <= minusPair) {
            final long ans = binarySearchForMinus(minusList, plusList, minusList.get(0) * plusList.get(plusList.size() - 1), -1, minusPair - k);
            System.out.println(ans);
        } else if (k <= minusPair + zeroPair) {
            System.out.println(0);
        } else {
            final long ans = binarySearchForPlus(minusList, plusList, 1, (long) 1e18, n * (n - 1) / 2 - k);
            System.out.println(ans);
        }
    }

    private static long binarySearchForPlus(final List<Long> minusList, final List<Long> plusList, final long ng, final long ok, final long k) {
        if (ok - ng <= 1) {
            return ng;
        }

        long mid = (ok + ng) / 2;
        final long sumOfMinusList = calcSum(minusList, mid);
        final long sumOfPlusList = calcSum(plusList, mid);
        final long sum = sumOfMinusList + sumOfPlusList;

        if (sum > k) {
            return binarySearchForPlus(minusList, plusList, mid, ok, k);
        } else {
            return binarySearchForPlus(minusList, plusList, ng, mid, k);
        }
    }

    private static long calcSum(final List<Long> list, final long mid) {
        if (list.size() <= 1) {
            return 0;
        }
        int idx = list.size();
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            while (idx >= 1 && idx > i && list.get(i) * list.get(idx - 1) >= mid) {
                idx--;
            }
            sum += list.size() - Math.max(idx, i + 1);
        }
        return sum;
    }

    private static long binarySearchForMinus(final List<Long> minusList, final List<Long> plusList, final long ng, final long ok, final long k) {
        if (ok - ng <= 1) {
            return ng;
        }

        long mid = (ok + ng) / 2;
        int plusIdx = plusList.size();
        long sum = 0;
        // mid以上の値の数を探索
        for (final long minusValue : minusList) {
            while (plusIdx >= 1 && minusValue * plusList.get(plusIdx - 1) >= mid) {
                plusIdx--;
            }
            sum += plusList.size() - plusIdx;
        }

        if (sum > k) {
            return binarySearchForMinus(minusList, plusList, mid, ok, k);
        } else {
            return binarySearchForMinus(minusList, plusList, ng, mid, k);
        }
    }
}
