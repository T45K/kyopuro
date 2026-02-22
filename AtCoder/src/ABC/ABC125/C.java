package ABC.ABC125;

import java.util.Arrays;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int number = scanner.nextInt();
        final int[] ints = new int[number];

        for (int i = 0; i < number; i++) {
            ints[i] = scanner.nextInt();
        }

        final int[] leftGCD = new int[number];
        leftGCD[0] = ints[0];
        for (int i = 1; i < leftGCD.length; i++) {
            leftGCD[i] = getGCD(leftGCD[i - 1], ints[i]);
        }

        final int[] rightGCD = new int[number];
        rightGCD[number - 1] = ints[number - 1];
        for (int i = rightGCD.length - 2; i >= 0; i--) {
            rightGCD[i] = getGCD(rightGCD[i + 1], ints[i]);
        }

        final int[] maxGCDIfIndexDeleted = new int[number];
        maxGCDIfIndexDeleted[0] = rightGCD[1];
        maxGCDIfIndexDeleted[number - 1] = leftGCD[number - 2];
        for (int i = 1; i < maxGCDIfIndexDeleted.length - 1; i++) {
            maxGCDIfIndexDeleted[i] = getGCD(leftGCD[i - 1], rightGCD[i + 1]);
        }

        Arrays.sort(maxGCDIfIndexDeleted);
        System.out.println(maxGCDIfIndexDeleted[number - 1]);
    }

    private static int getGCD(final int a, final int b) {
        if (b > a) {
            return getGCD(b, a);
        }

        if (b == 0) {
            return a;
        }

        return getGCD(b, a % b);
    }

    /*
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int counter = scanner.nextInt();
        final int[] ints = new int[counter];

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int i = 0; i < counter; i++) {
            ints[i] = scanner.nextInt();
            if (ints[i] < min1) {
                min1 = ints[i];
            }
            if (min1 < ints[i] && ints[i] < min2) {
                min2 = ints[i];
            }
        }

        List<Integer> gs = new ArrayList<>();
        if (min1 % 2 == 0 || min2 % 2 == 0)
            gs.add(2);
        for (int i = 3; i <= min2 / 2; i += 2) {
            if (min1 % i == 0 || min2 % i == 0) {
                gs.add(i);
            }
        }

        gs.add(min1);
        if (min1 != min2)
            gs.add(min2);

        final boolean[][] table = new boolean[counter][gs.size()];

        for (int i = 0; i < counter; i++) {
            for (int j = 0; j < gs.size(); j++) {
                if (ints[i] % gs.get(j) == 0) {
                    table[i][j] = true;
                }
            }
        }

        for (int i = gs.size() - 1; i >= 0; i--) {
            int cont = 0;
            for (int j = 0; j < counter; j++) {
                if (table[j][i]) {
                    cont++;
                }
            }

            if (cont == counter || cont == counter - 1) {
                System.out.println(gs.get(i));
                return;
            }
        }

        System.out.println(1);
    }

     */
}
