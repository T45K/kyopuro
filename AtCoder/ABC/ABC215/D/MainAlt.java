package AtCoder.ABC.ABC215.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
解説
 */
public class MainAlt {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final int n = scanner.nextInt();
    final int m = scanner.nextInt();
    final List<Integer> list = Stream.generate(scanner::nextInt)
        .limit(n)
        .filter(it -> it > 1) // gcd(1, k) == 1
        .collect(Collectors.toList());
    final boolean[] isSelectable = new boolean[m + 1];
    Arrays.fill(isSelectable, true);

    for (final int value : list) {
      final Set<Integer> primes = primeFactorization(value).keySet();
      for (final int prime : primes) {
        if (prime > m || !isSelectable[prime]) {
          continue;
        }
        for (int i = prime; i <= m; i += prime) {
          isSelectable[i] = false;
        }
      }
    }

    final List<Integer> answers = IntStream.rangeClosed(1, m)
        .filter(i -> isSelectable[i])
        .boxed()
        .collect(Collectors.toList());
    System.out.println(answers.size());
    final String answer = answers.stream()
        .map(Objects::toString)
        .collect(Collectors.joining("\n"));
    System.out.println(answer);
  }

  private static Map<Integer, Long> primeFactorization(int n) {
    final double sqrt = Math.sqrt(n);
    final Map<Integer, Long> countMap = new LinkedHashMap<>();
    for (int i = 2; i <= sqrt; i++) {
      if (n % i == 0) {
        countMap.compute(i, (k, v) -> v = v == null ? 1 : v + 1);
        n /= i;
        i--;
      }
    }

    if (n != 1) {
      countMap.compute(n, (k, v) -> v = v == null ? 1 : v + 1);
    }

    return countMap;
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
