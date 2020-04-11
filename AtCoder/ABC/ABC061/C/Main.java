package AtCoder.ABC.ABC061.C;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextLong();

        final List<Pair> list = IntStream.range(0, n)
                .mapToObj(i -> new Pair(scanner.nextInt(), scanner.nextInt()))
                .sorted(Comparator.comparingInt(Pair::getA))
                .collect(Collectors.toList());

        long sum = 0;
        for (final Pair pair : list) {
            if (k <= sum + pair.b) {
                System.out.println(pair.a);
                return;
            }

            sum += pair.b;
        }
    }

    static class Pair {
        int a;
        int b;

        Pair(final int a, final int b) {
            this.a = a;
            this.b = b;
        }

        int getA() {
            return a;
        }
    }
}
