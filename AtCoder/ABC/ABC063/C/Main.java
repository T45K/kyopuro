package AtCoder.ABC.ABC063.C;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        int sum = 0;
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int s = scanner.nextInt();
            sum += s;
            list.add(s);
        }

        list.sort(Comparator.comparingInt(Integer::intValue));
        if (sum % 10 != 0) {
            System.out.println(sum);
        } else {
            for (final int value : list) {
                if (value % 10 != 0) {
                    System.out.println(sum - value);
                    return;
                }
            }
            System.out.println(0);
        }
    }
}
