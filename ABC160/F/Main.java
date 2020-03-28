package ABC160.F;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO solve
public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final List<Integer>[] tree = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int _ = 0; _ < n - 1; _++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            tree[a].add(b);
            tree[b].add(a);
        }


    }
}
