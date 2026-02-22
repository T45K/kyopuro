package other.apc001.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
rightを変える時も基準の性別を変えてたせいで爆死
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Seat left = new Seat(0);
        if (left.status == Status.Vacant) {
            return;
        }

        final Seat right = new Seat(n - 1);
        if (right.status == Status.Vacant) {
            return;
        }

        binarySearch(left, right);
    }

    private static void binarySearch(final Seat left, final Seat right) {
        if (right.number - left.number == 1) {
            System.out.println(right.number);
            return;
        }

        final Seat mid = new Seat((left.number + right.number) / 2);
        if (mid.status == Status.Vacant) {
            return;
        }

        if ((mid.number - left.number) % 2 == 0 && left.status == mid.status
            || (mid.number - left.number) % 2 == 1 && left.status != mid.status) {
            binarySearch(mid, right);
        } else {
            binarySearch(left, new Seat(mid.number - 1, Status.Dummy));
        }
    }

    private enum Status {
        Vacant, Male, Female, Dummy
    }

    private static class Seat {
        private final int number;
        private final Status status;

        private static final FastScanner scanner = new FastScanner(System.in);

        Seat(final int number) {
            this.number = number;
            printAndFlush(number);
            this.status = Status.valueOf(scanner.next());
        }

        Seat(final int number, final Status status) {
            this.number = number;
            this.status = status;
        }

        private static void printAndFlush(final int number) {
            System.out.println(number);
            System.out.flush();
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
    }
}
