package AtCoder.ABC.ABC080.C;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final boolean[][] shops = new boolean[n][10];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                shops[i][j] = scanner.nextInt() == 1;
            }
        }

        final int[][] profits = new int[n][11];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 11; j++) {
                profits[i][j] = scanner.nextInt();
            }
        }

        System.out.println(recursive(new boolean[10], 0, shops, profits));
    }

    private static int recursive(final boolean[] isOpen, final int index, final boolean[][] shops, final int[][] profits) {
        if (index == 9) {
            return Math.max(calc(true, isOpen, shops, profits), calc(false, isOpen, shops, profits));
        } else {
            isOpen[index] = true;
            int trueValue = recursive(isOpen, index + 1, shops, profits);

            isOpen[index] = false;
            int falseValue = recursive(isOpen, index + 1, shops, profits);

            return Math.max(trueValue, falseValue);
        }
    }

    private static int calc(final boolean bool, final boolean[] isOpen, final boolean[][] shops, final int[][] profits) {
        if (isAllFalse(isOpen)) {
            return Integer.MIN_VALUE;
        }
        isOpen[9] = bool;
        return IntStream.range(0, shops.length)
                .map(i -> {
                    final boolean[] shop = shops[i];
                    int tmp = (int) IntStream.range(0, shop.length)
                            .filter(j -> isOpen[j] && shop[j])
                            .count();
                    return profits[i][tmp];
                }).sum();
    }

    private static boolean isAllFalse(final boolean[] isOpen) {
        return IntStream.range(0, isOpen.length)
                .mapToObj(i -> isOpen[i])
                .noneMatch(Boolean::booleanValue);
    }
}
