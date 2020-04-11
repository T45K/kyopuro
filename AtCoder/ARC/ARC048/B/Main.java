package AtCoder.ARC.ARC048.B;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final int[][] table = new int[100_001][4];
        final List<Player> players = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int rate = scanner.nextInt();
            final int hand = scanner.nextInt();
            table[rate][hand]++;
            players.add(new Player(rate, hand));
        }

        final int[] accumulation = new int[100_001];
        int sum = 0;
        for (int i = 1; i < 100_001; i++) {
            accumulation[i] = sum;
            for (final int value : table[i]) {
                sum += value;
            }
        }

        for (final Player player : players) {
            final int win = accumulation[player.rate] + table[player.rate][player.hand % 3 + 1];
            final int draw = table[player.rate][player.hand] - 1;
            final int lose = n - (win + draw) - 1;
            System.out.println(win + " " + lose + " " + draw);
        }
    }

    static class Player {
        int rate;
        int hand;

        public Player(final int rate, final int hand) {
            this.rate = rate;
            this.hand = hand;
        }
    }
}
