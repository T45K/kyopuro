package AtCoder.ABC.ABC142.C;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final List<Pair> pairs = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            pairs.add(new Pair(i, scanner.nextInt()));
        }

        pairs.sort(Comparator.comparingInt(pair -> pair.timing));
        final String answer = pairs.stream()
                .map(pair -> Integer.toString(pair.number))
                .collect(Collectors.joining(" "));

        System.out.println(answer);
    }

    static class Pair {
        int number;
        int timing;

        Pair(final int number, final int timing) {
            this.number = number;
            this.timing = timing;
        }
    }
}
