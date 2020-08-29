package AtCoder.other.exawizards2019.C;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int q = scanner.nextInt();
        final String s = scanner.next();

        final List<Query> queries = IntStream.range(0, q)
            .mapToObj(i -> new Query(scanner.next().charAt(0), scanner.next().charAt(0) == 'L' ? -1 : 1))
            .collect(Collectors.toList());

        final Function<Integer, SimulationResult> simulate = current -> {
            for (final Query query : queries) {
                if (s.charAt(current) != query.symbol) {
                    continue;
                }

                if (current == 0 && query.direction == -1) {
                    return SimulationResult.LEFT_FALL;
                } else if (current == s.length() - 1 && query.direction == 1) {
                    return SimulationResult.RIGHT_FALL;
                }

                current += query.direction;
            }

            return SimulationResult.REMAIN;
        };

        if (simulate.apply(n - 1) == SimulationResult.LEFT_FALL || simulate.apply(0) == SimulationResult.RIGHT_FALL) {
            System.out.println(0);
            return;
        }

        final int mostLeft = simulate.apply(0) == SimulationResult.LEFT_FALL ? binarySearchLeft(0, n - 1, simulate) : 0;
        final int mostRight = simulate.apply(n - 1) == SimulationResult.RIGHT_FALL ? binarySearchRight(0, n - 1, simulate) : n;
        if (mostLeft >= mostRight) {
            System.out.println(0);
        } else {
            System.out.println(mostRight - mostLeft);
        }
    }

    private static int binarySearchLeft(final int begin, final int end, final Function<Integer, SimulationResult> simulate) {
        if (end - begin <= 1) {
            return end;
        }
        final int mid = (begin + end) / 2;
        final SimulationResult result = simulate.apply(mid);
        if (result == SimulationResult.LEFT_FALL) {
            return binarySearchLeft(mid, end, simulate);
        } else {
            return binarySearchLeft(begin, mid, simulate);
        }
    }

    private static int binarySearchRight(final int begin, final int end, final Function<Integer, SimulationResult> simulate) {
        if (end - begin <= 1) {
            return end;
        }
        final int mid = (begin + end) / 2;
        final SimulationResult result = simulate.apply(mid);
        if (result == SimulationResult.RIGHT_FALL) {
            return binarySearchRight(begin, mid, simulate);
        } else {
            return binarySearchRight(mid, end, simulate);
        }
    }

    private enum SimulationResult {
        LEFT_FALL, RIGHT_FALL, REMAIN
    }

    private static class Query {
        final char symbol;
        final int direction;

        Query(final char symbol, final int direction) {
            this.symbol = symbol;
            this.direction = direction;
        }
    }
}
