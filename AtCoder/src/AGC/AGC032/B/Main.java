package AGC.AGC032.B;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
グラフ 辺を張る問題 解説がよく分からん
全ての頂点を繋いだ時，頂点Vの隣接する頂点番号の和は N*(N+1)/2-V になる
そこで， これを全ての頂点に対して一致させるように辺を間引くことを考える
Nが偶数の時は N*(N+1)/2-(N+1)，奇数の時は N*(N+1)/2-N にできる
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final LinkedList<Pair> pairs = new LinkedList<>();
        if (n % 2 == 1) {
            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    if (i + j != n) {
                        pairs.add(new Pair(i, j));
                    }
                }
            }
        } else {
            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    if (i + j != n + 1) {
                        pairs.add(new Pair(i, j));
                    }
                }
            }
        }

        System.out.println(pairs.size());
        final String answer = pairs.stream()
                .map(Pair::toString)
                .collect(Collectors.joining("\n"));
        System.out.println(answer);
    }

    private static class Pair {
        final int x;
        final int y;

        Pair(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
}
