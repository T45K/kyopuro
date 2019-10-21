package ABC096.D;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final int BASE = 55555;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final Deque<Integer> primeNumbers = new ArrayDeque<>(sieveOfEratosthenes(BASE));
        final List<Integer> answers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int primeNumber;
            while ((primeNumber = primeNumbers.poll()) % 5 != 1) ;
            answers.add(primeNumber);
        }

        final String answer = answers.stream()
                .map(i -> Integer.toString(i))
                .collect(Collectors.joining(" "));

        System.out.println(answer);
    }

    private static List<Integer> sieveOfEratosthenes(final int number) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= number; i++) {
            list.add(i);
        }

        final int sqrt = (int) Math.sqrt(number);
        final List<Integer> primeNumbers = new ArrayList<>();
        int condition;

        do {
            final int prime = list.get(0);
            primeNumbers.add(prime);
            list = list.stream()
                    .filter(i -> i % prime != 0)
                    .collect(Collectors.toList());
            condition = prime;
        } while (condition < sqrt);

        primeNumbers.addAll(list);
        return primeNumbers;
    }
}
