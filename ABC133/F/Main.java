package ABC133.F;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final long k = scanner.nextInt();

        final List<Integer>[] tree = new List[n + 1];
        for (int i = 1; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            tree[a].add(b);
            tree[b].add(a);
        }

        final boolean[] isVisited = new boolean[n + 1];
        final long answer = searchWithNoParent(tree, k, isVisited);
        System.out.println(answer);
    }

    private static long searchWithNoParent(final List<Integer>[] tree, final long k, final boolean[] isVisited) {
        isVisited[1] = true;
        long product = k;
        final List<Integer> children = tree[1];
        for (int i = 0; i < children.size(); i++) {
            final int child = children.get(i);
            product *= searchWithParent(tree, child, i, k, isVisited);
            product %= MOD;
        }
        return product;
    }

    private static long searchWithParent(final List<Integer>[] tree, final int current, final int brotherNumber, final long k, final boolean[] isVisited) {
        isVisited[current] = true;
        long product = k - 1 - brotherNumber;
        int count = 0;
        for (final int child : tree[current]) {
            if (isVisited[child]) {
                continue;
            }
            product *= searchWithGrandParent(tree, child, count++, k, isVisited);
            product %= MOD;
        }
        return product;
    }

    private static long searchWithGrandParent(final List<Integer>[] tree, final int current, final int brotherNumber, final long k, final boolean[] isVisited) {
        isVisited[current] = true;
        long product = k - 2 - brotherNumber;
        int count = 0;
        for (final int child : tree[current]) {
            if (isVisited[child]) {
                continue;
            }
            product *= searchWithGrandParent(tree, child, count++, k, isVisited);
            product %= MOD;
        }
        return product;
    }
}
