package ABC133;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class C {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int l = scanner.nextInt();
        final int r = scanner.nextInt();

        final Set<Integer> mods = new HashSet<>();

        for (int i = l; i <= r; i++) {
            if (i % 2019 == 0) {
                System.out.println(0);
                return;
            }

            mods.add(i % 2019);
        }

        final Integer[] modArray = new Integer[mods.size()];
        mods.toArray(modArray);

        int min = 2019;
        for (int i = 0; i < modArray.length - 1; i++) {
            for (int j = i + 1; j < modArray.length; j++) {
                if (modArray[i] * modArray[j] % 2019 < min) {
                    min = modArray[i] * modArray[j] % 2019;
                }
            }
        }

        System.out.println(min);
    }
}
