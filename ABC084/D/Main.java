package ABC084.D;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final int MAX = (int) 1e5 + 1;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int q = scanner.nextInt();
        final int[] like2017 = new int[MAX];
        final Set<Integer> primeNumbers = sieveOfEratosthenes(MAX);

        for (int i = 1; i < MAX; i++) {
            like2017[i] = isLike2017(i, primeNumbers) ? like2017[i - 1] + 1 : like2017[i - 1];
        }

        for (int i = 0; i < q; i++) {
            final int begin = scanner.nextInt();
            final int end = scanner.nextInt();
            System.out.println(like2017[end] - like2017[begin - 1]);
        }
    }

    private static boolean isLike2017(final int number, final Set<Integer> primeNumbers) {
        return number % 2 == 1 && primeNumbers.contains(number) && primeNumbers.contains((number + 1) / 2);
    }

    private static Set<Integer> sieveOfEratosthenes(final int number) {
        List<Integer> numbers = IntStream.rangeClosed(2, number)
                .boxed()
                .collect(Collectors.toList());

        final int sqrt = (int) Math.sqrt(number);
        final Set<Integer> primeNumbers = new HashSet<>();
        int condition;

        do {
            final int prime = numbers.get(0);
            primeNumbers.add(prime);
            numbers = numbers.stream()
                    .filter(i -> i % prime != 0)
                    .collect(Collectors.toList());
            condition = prime;
        } while (condition < sqrt);

        primeNumbers.addAll(numbers);
        return primeNumbers;
    }
}
