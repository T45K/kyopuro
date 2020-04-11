package AtCoder.ABC.ABC149.E;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long m = scanner.nextLong();

        final long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        Arrays.sort(array);
        final long[] cumulativeSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            cumulativeSum[i + 1] += cumulativeSum[i] + array[i];
        }

        /*
        ある値xに対して，その値を越える和の組み合わせが何通り存在するかを計算し，Mと比較して二分探索
        各a_iに対して和の組み合わせはn通りある（a_i + a_0, a_i + a_1, ..., a_i + a_(n-1)）
        このとき配列は昇順にソートされているので，a_i + a_j >= Xとなるjは，
        a_j >= X - a_iを満たす最小のjの値をkとすると(n-k)個存在する（このkは二分探索で求められる）．
        これを各a_iに対して計算していき，#jの総和(=何通りあるか)をとる．
        この値がmより大きければxを大きくして，小さければxを小さくして再計算
        */

        long min = array[0] * 2;
        long max = array[n - 1] * 2;
        while (max - min > 1) {
            long mid = (min + max) / 2;
            long count = 0;
            for (int i = 0; i < n; i++) {
                final int index = upperBound(array, mid - array[i]) + 1;
                count += n - index;
            }
            if (count >= m) {
                min = mid;
            } else {
                max = mid;
            }
        }

        long answer = 0;
        long count = 0;
        for (int i = 0; i < n; i++) {
            final int index = upperBound(array, min - array[i]) + 1;
            if (index == n) {
                continue;
            }

            count += n - index;
            // (a + b_1) + (a + b_2) + ... +  (a + b_n) = (b_1 + b_2 + ... + b_n) + n * a = (S_n - S_0) + n * a
            answer += cumulativeSum[n] - cumulativeSum[index];
            answer += array[i] * (n - index);
        }

        answer += (m - count) * min;
        System.out.println(answer);
    }

    private static int upperBound(final long[] array, final long value) {
        int min = -1;
        int max = array.length;
        while (max - min > 1) {
            int mid = (min + max) / 2;
            if (array[mid] < value) {
                min = mid;
            } else {
                max = mid;
            }
        }
        return min;
    }
}
