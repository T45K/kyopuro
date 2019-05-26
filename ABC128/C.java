package ABC128;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final List<boolean[]> list = new ArrayList<>();
        final boolean[] booleans = new boolean[n];
        createArray(booleans, list, 0, n - 1);
        final List<int[]> indexList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            final int[] ints = new int[scanner.nextInt()];
            for (int j = 0; j < ints.length; j++) {
                ints[j] = scanner.nextInt() - 1;
            }
            indexList.add(ints);
        }

        for(int i = 0;i<m;i++){
            int zeroOrOne = scanner.nextInt();
            final int[] ints = indexList.get(i);

            for (int i1 = 0; i1 < list.size(); i1++) {
                final boolean[] booleans1 = list.get(i1);
                int counter = 0;
                for (final int anInt : ints) {
                    if (booleans1[anInt]) {
                        counter++;
                    }
                }
                if (counter % 2 != zeroOrOne) {
                    list.remove(i1);
                    i1--;
                }
            }
        }


        System.out.println(list.size());
    }

    private static void createArray(final boolean[] bools, final List<boolean[]> list, final int index, final int last) {
        if (index == last) {
            bools[index] = true;
            list.add(getNewArray(bools));
            bools[index] = false;
            list.add(getNewArray(bools));
            return;
        }

        bools[index] = true;
        createArray(bools, list, index + 1, last);
        bools[index] = false;
        createArray(bools, list, index + 1, last);
    }

    private static boolean[] getNewArray(final boolean[] original) {
        final boolean[] booleans = new boolean[original.length];
        for (int i = 0; i < original.length; i++) {
            booleans[i] = original[i];
        }
        return booleans;
    }
}
