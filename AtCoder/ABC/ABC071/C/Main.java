package AtCoder.ABC.ABC071.C;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final List<Integer> list = IntStream.range(0, scanner.nextInt())
                .mapToObj(i -> scanner.nextInt())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                final long longer = list.get(i);
                for (int j = i + 2; j < list.size() - 1; j++) {
                    if (list.get(j).equals(list.get(j + 1))) {
                        final long shorter = list.get(j);
                        System.out.println(longer * shorter);
                        return;
                    }
                }
            }
        }

        System.out.println(0);
    }
}
