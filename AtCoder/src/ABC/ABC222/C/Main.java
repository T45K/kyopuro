package ABC.ABC222.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final String[] array = Stream.generate(scanner::next)
            .limit(2L * n)
            .toArray(String[]::new);
        final List<Player> players = IntStream.range(0, 2 * n)
            .mapToObj(Player::new)
            .collect(Collectors.toList());
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                final Player a = players.get(2 * j);
                final Player b = players.get(2 * j + 1);
                final Hand aHand = Hand.from(array[a.number].charAt(i));
                final Hand bHand = Hand.from(array[b.number].charAt(i));
                if (aHand.win(bHand)) {
                    a.win();
                } else if (bHand.win(aHand)) {
                    b.win();
                }
            }
            players.sort(Comparator.comparing(Player::getCount, Comparator.reverseOrder()).thenComparing(Player::getNumber));
        }
        final String answer = players.stream()
            .map(player -> Integer.toString(player.number + 1))
            .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private enum Hand {
        G {
            @Override
            boolean win(final Hand hand) {
                return hand == C;
            }
        },
        C {
            @Override
            boolean win(final Hand hand) {
                return hand == P;
            }
        },
        P {
            @Override
            boolean win(final Hand hand) {
                return hand == G;
            }
        };

        static Hand from(final char c) {
            switch (c) {
                case 'G':
                    return G;
                case 'C':
                    return C;
                case 'P':
                    return P;
            }
            throw new RuntimeException();
        }

        abstract boolean win(final Hand hand);
    }

    private static class Player {
        private final int number;
        private int count = 0;

        public Player(final int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public int getCount() {
            return count;
        }

        void win() {
            count++;
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
