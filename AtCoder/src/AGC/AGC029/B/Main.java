package AGC.AGC029.B;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final List<Integer> list = new ArrayList<>();
        final Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            list.add(a);
            countMap.compute(a, (k, v) -> v == null ? 1 : v + 1);
        }

        list.sort(Comparator.naturalOrder());
        int pow = 1 << 30;
        int count = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            final int value = list.get(i);
            if (countMap.get(value) == 0) {
                continue;
            }
            countMap.compute(value, (k, v) -> --v);

            while (pow >> 1 > value) {
                pow = pow >> 1;
            }

            final int index = Collections.binarySearch(list, pow - value);
            if (index < 0) {
                continue;
            }
            final int sbj = list.get(index);
            if (countMap.get(sbj) > 0) {
                count++;
                countMap.compute(sbj, (k, v) -> --v);
            }
        }
        System.out.println(count);
    }
}
