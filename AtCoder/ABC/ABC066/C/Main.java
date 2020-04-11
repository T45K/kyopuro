package AtCoder.ABC.ABC066.C;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());

        final int[] b = new int[n];
        for (int i = 0; i < list.size(); i++) {
            final int k = n % 2 == 0 ? i : -i;
            final int a = list.get(i);
            if (i % 2 == 0) {
                b[(n + k) / 2] = a;
            } else {
                b[(n - k) / 2] = a;
            }
        }

        for (final int i : b) {
            System.out.print(i + " ");
        }
    }
}
