package ABC.ABC085.D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int h = scanner.nextInt();

        int maxA = 0;
        final List<Integer> bList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            if (a > maxA) {
                maxA = a;
            }
            bList.add(scanner.nextInt());
        }

        int fixed = maxA;
        final List<Integer> list = bList.stream()
                .filter(b -> b >= fixed)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        int sum = 0;
        int counter = 0;
        for (final int b : list) {
            counter++;
            sum += b;
            if (sum >= h) {
                System.out.println(counter);
                return;
            }
        }

        System.out.println((h - sum + maxA - 1) / maxA + counter);
    }
}
