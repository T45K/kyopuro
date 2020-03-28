package ABC157.E;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();

        int pow = 1;
        while (pow < n) {
            pow <<= 1;
        }

        final int[] tree = new int[2 * pow];
        for (int i = 0; i < pow; i++) {
            if (i >= n) {
                tree[i] = 0;
            } else {
                final int tmp = s.charAt(i) - 'a';
                tree[i + pow] = 1 << tmp;
            }
        }

        for (int i = pow - 1; i > 0; i--) {
            tree[i] = tree[2 * i] | tree[2 * i + 1];
        }

        final int q = scanner.nextInt();
        for (int _ = 0; _ < q; _++) {
            if (scanner.nextInt() == 1) {
                final int i = scanner.nextInt();
                final int c = scanner.next().toCharArray()[0] - 'a';

                tree[pow + i - 1] = 1 << c;
                int k = (pow + i - 1) / 2;
                while (k > 0) {
                    tree[k] = tree[2 * k] | tree[2 * k + 1];
                    k /= 2;
                }
            } else {
                final int l = scanner.nextInt();
                final int r = scanner.nextInt();

                final int value = recursive(tree, l - 1, r - 1, 0, pow - 1, pow);
                final String sValue = Integer.toBinaryString(value);
                final long count = IntStream.range(0, sValue.length())
                        .map(sValue::charAt)
                        .filter(c -> c == '1')
                        .count();
                System.out.println(count);
            }
        }

        System.out.println();
    }

    private static int recursive(final int[] tree, final int l, final int r, final int begin, final int end, final int pow) {
        if (l == begin && r == end) {
            return tree[(pow + l) / (r - l + 1)];
        }

        final int mid = (begin + end) / 2;
        if (l <= mid && r <= mid) {
            return recursive(tree, l, r, begin, mid, pow);
        } else if (l <= mid) {
            return recursive(tree, l, mid, begin, mid, pow) | recursive(tree, mid + 1, r, mid + 1, end, pow);
        } else {
            return recursive(tree, l, r, mid + 1, end, pow);
        }
    }
}
