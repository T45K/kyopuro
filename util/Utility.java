package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utility {
    static class Counter {
        private int value;

        public void increment() {
            value++;
        }

        public int getValue() {
            return value;
        }
    }

    private static int intPow(final int a, final int b) {
        return (int) Math.pow(a, b);
    }

    /**
     * 値が存在しない時も加味したした二分探索
     * 存在する時はその位置
     * 損しない時はそれが入るはずの位置を返す
     * 二分探索の性質上配列中に被りがあるとうまく働かない
     *
     * @param indexes int(Integer)の配列あるいはList
     * @param value   値
     * @return インデックス
     */
    @SuppressWarnings("unchecked")
    private static int extendedBinarySearch(final Object indexes, final int value) {
        final int rawIndex;
        if (indexes instanceof int[]) {
            rawIndex = Arrays.binarySearch((int[]) indexes, value);
        } else {
            rawIndex = Collections.binarySearch((List<Integer>) indexes, value);
        }

        if (rawIndex >= 0) {
            return rawIndex;
        }

        return -(rawIndex + 1);
    }
}
