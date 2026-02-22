package ABC.ABC215.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Aiが約数に持たない素因数を列挙して，それから合成数を作る
Aiを素因数分解するのが速そう
 */
public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final int n = scanner.nextInt();
    final int m = scanner.nextInt();
    final Set<Integer> set = Stream.generate(scanner::nextInt)
        .limit(n)
        .filter(it -> it > 1) // gcd(1, k) == 1
        .collect(Collectors.toSet());

    final int max = set.stream().max(Integer::compareTo).orElse(1);

    final List<Integer> primes = sieveOfEratosthenes(m).stream()
        .filter(prime -> {
          for (int i = prime; i <= max; i += prime) {
            if (set.contains(i)) {
              return false;
            }
          }
          return true;
        })
        .collect(Collectors.toList());

    final List<Integer> answers = new ArrayList<>();
    compose(0, primes, answers, m, 1);
    final String answer = answers.stream()
        .sorted()
        .map(Objects::toString)
        .collect(Collectors.joining("\n"));
    System.out.println(answers.size());
    System.out.println(answer);
  }

  private static void compose(final int index, final List<Integer> primes, final List<Integer> answers, final int m, final long value) {
    answers.add((int) value);
    for (int i = index; i < primes.size(); i++) {
      final long prod = value * primes.get(i);
      if (prod > m) {
        break;
      }
      compose(i, primes, answers, m, prod);
    }
  }

  private static List<Integer> sieveOfEratosthenes(final int number) {
    final boolean[] isPrimeNumber = new boolean[number + 1];
    Arrays.fill(isPrimeNumber, true);
    final int sqrt = (int) Math.sqrt(number);
    for (int i = 2; i <= sqrt; i++) {
      if (!isPrimeNumber[i]) {
        continue;
      }
      for (int j = 2; i * j <= number; j++) {
        isPrimeNumber[i * j] = false;
      }
    }
    return IntStream.rangeClosed(2, number)
        .filter(i -> isPrimeNumber[i])
        .boxed()
        .collect(Collectors.toList());
  }

  private static class FastScanner {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    FastScanner(final InputStream in) {
      reader = new BufferedReader(new InputStreamReader(in));
    }

    String next() {
      if (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (final IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }
}
