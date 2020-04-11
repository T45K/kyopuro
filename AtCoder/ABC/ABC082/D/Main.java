package AtCoder.ABC.ABC082.D;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        final List<Integer> xs = new ArrayList<>();
        final List<Integer> ys = new ArrayList<>();

        final String[] split = s.split("T");
        for (int i = 0; i < split.length; i++) {
            (i % 2 == 0 ? xs : ys).add(split[i].length());
        }

        final int x = scanner.nextInt();
        final int y = scanner.nextInt();

        if (isXMovable(x, xs) && isYMovable(y, ys)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    private static boolean isXMovable(final int point, final List<Integer> moves) {
        if (moves.size() == 0) {
            return point == 0;
        }
        boolean[] plusExists = new boolean[8001];
        boolean[] minusExists = new boolean[8001];
        plusExists[moves.get(0)] = true;
        for (int i = 1; i < moves.size(); i++) {
            final boolean[] plusTmp = new boolean[8001];
            final boolean[] minusTmp = new boolean[8001];
            final int move = moves.get(i);
            for (int j = 0; j < plusExists.length; j++) {
                if (plusExists[j]) {
                    plusTmp[j + move] = true;
                    if (j - move >= 0) {
                        plusTmp[j - move] = true;
                    } else {
                        minusTmp[move - j] = true;
                    }
                }

                if (minusExists[j]) {
                    minusTmp[j + move] = true;
                    if (j - move >= 0) {
                        minusTmp[j - move] = true;
                    } else {
                        plusTmp[move - j] = true;
                    }
                }
            }
            plusExists = plusTmp;
            minusExists = minusTmp;
        }

        if (point >= 0) {
            return plusExists[point];
        } else {
            return minusExists[-point];
        }
    }


    private static boolean isYMovable(final int point, final List<Integer> moves) {
        if (moves.size() == 0) {
            return point == 0;
        }
        boolean[] plusExists = new boolean[8001];
        boolean[] minusExists = new boolean[8001];
        plusExists[moves.get(0)] = true;
        minusExists[moves.get(0)] = true;
        for (int i = 1; i < moves.size(); i++) {
            final boolean[] plusTmp = new boolean[8001];
            final boolean[] minusTmp = new boolean[8001];
            final int move = moves.get(i);
            for (int j = 0; j < plusExists.length; j++) {
                if (plusExists[j]) {
                    plusTmp[j + move] = true;
                    if (j - move >= 0) {
                        plusTmp[j - move] = true;
                    } else {
                        minusTmp[move - j] = true;
                    }
                }

                if (minusExists[j]) {
                    minusTmp[j + move] = true;
                    if (j - move >= 0) {
                        minusTmp[j - move] = true;
                    } else {
                        plusTmp[move - j] = true;
                    }
                }
            }
            plusExists = plusTmp;
            minusExists = minusTmp;
        }

        if (point >= 0) {
            return plusExists[point];
        } else {
            return minusExists[-point];
        }
    }
}
