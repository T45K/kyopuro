package ABC045.C;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        final List<Integer> list = IntStream.range(0, s.length())
                .mapToObj(i -> s.charAt(i) - '0')
                .collect(Collectors.toList());

        System.out.println(calc(0, list, 0));
    }

    private static long calc(final int begin, final List<Integer> list, final long fixed) {
        if (begin >= list.size()) {
            return fixed;
        }

        long sum = 0;
        long tmp = 0;
        for (int i = begin; i < list.size(); i++) {
            tmp = tmp * 10 + list.get(i);
            sum += calc(i + 1, list, fixed + tmp);
        }

        return sum;
    }
}
