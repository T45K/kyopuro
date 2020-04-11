package AtCoder.ABC.ABC002.D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        if (m == 0) {
            System.out.println(1);
            return;
        }

        final Map<Integer, Set<Integer>> relations = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int x = scanner.nextInt();
            final int y = scanner.nextInt();

            relations.computeIfAbsent(x, v -> new HashSet<>()).add(y);
            relations.computeIfAbsent(y, v -> new HashSet<>()).add(x);
        }

        final int answer = recursive(relations, 1, n + 1, new ArrayList<>());
        System.out.println(answer);
    }

    private static int recursive(final Map<Integer, Set<Integer>> relations, final int index, final int last, final List<Integer> members) {
        if (index == last) {
            for (final int from : members) {
                final Set<Integer> relation = relations.get(from);
                if (relation == null) {
                    return -1;
                }
                for (final int to : members) {
                    if (to == from) {
                        continue;
                    }
                    if (!relation.contains(to)) {
                        return -1;
                    }
                }
            }
            return members.size();
        }

        members.add(index);
        final int a = recursive(relations, index + 1, last, members);

        members.remove(members.size() - 1);
        final int b = recursive(relations, index + 1, last, members);

        return Math.max(a, b);
    }
}
