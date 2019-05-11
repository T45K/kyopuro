package kyopuro.ABC125;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C {
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


}
