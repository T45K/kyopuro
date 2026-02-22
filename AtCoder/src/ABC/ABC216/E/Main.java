package ABC.ABC216.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
ある楽しさAをにぶたんで探して，A以上の楽しさのアトラクションを楽しさがAになるまで遊ぶ
 */
public class Main {
  public static void main(final String[] args) {
    final FastScanner scanner = new FastScanner(System.in);
    final int n = scanner.nextInt();
    final long k = scanner.nextLong();
    final List<Long> list = Stream.generate(scanner::nextLong)
        .limit(n)
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

    final long beginExclusive = binarySearch(0, 2_000_000_001, list, k) + 1;
    long rest = k;
    long sum = 0;
    for (final long value : list) {
      if (value <= beginExclusive) {
        break;
      }
      if (value - beginExclusive <= rest) {
        sum += rangeSum(beginExclusive, value);
        rest -= value - beginExclusive;
      }
    }
    final long count = list.stream()
        .filter(value -> value >= beginExclusive)
        .count();
    System.out.println(sum + Math.min(rest, count) * beginExclusive);
  }

  private static long rangeSum(final long beginExclusive, final long endInclusive) {
    return endInclusive * (endInclusive + 1) / 2 - beginExclusive * (beginExclusive + 1) / 2;
  }

  private static long binarySearch(final long begin, final long end, final List<Long> list, final long k) {
    if (end - begin <= 1) {
      return begin;
    }

    final long mid = (begin + end) / 2; // midは含まない
    final long sum = list.stream()
        .filter(value -> value >= mid)
        .mapToLong(Long::longValue)
        .sum();
    if (sum == k) {
      return mid;
    } else if (sum < k) {
      return binarySearch(begin, mid, list, k);
    } else {
      return binarySearch(mid, end, list, k);
    }
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

    long nextLong() {
      return Long.parseLong(next());
    }
  }
}
