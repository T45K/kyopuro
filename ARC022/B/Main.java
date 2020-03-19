package ARC022.B;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());

        int begin = 0;
        int end = 0;
        int max = -1;
        final Set<Integer> set = new HashSet<>();
        set.add(list.get(0));
        while (++end < list.size()) {
            final int taste = list.get(end);
            while (set.contains(taste)) {
                set.remove(list.get(begin++));
            }
            set.add(taste);

            max = Math.max(max, end - begin + 1);
        }

        System.out.println(Math.max(max, end - begin));
    }
}
