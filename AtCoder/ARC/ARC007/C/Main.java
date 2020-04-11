package AtCoder.ARC.ARC007.C;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String[] s = scanner.next().split("");
        final Deque<String> deque = Arrays.stream(s)
                .collect(Collectors.toCollection(ArrayDeque::new));

        final List<String> pattern = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            pattern.add(String.join("", deque));
            deque.addLast(deque.pollFirst());
        }

        final char[] initial = new char[s.length];
        Arrays.fill(initial, 'x');
        final int answer = Math.min(dfs(pattern, 1, 1, pattern.get(0)), dfs(pattern, 1, 0, new String(initial)));
        System.out.println(answer);
    }

    private static int dfs(final List<String> pattern, final int index, final int count, final String created) {
        if (index == pattern.size()) {
            for (int i = 0; i < created.length(); i++) {
                if (created.charAt(i) == 'x') {
                    return Integer.MAX_VALUE;
                }
            }
            return count;
        }

        final String s = pattern.get(index);
        final char[] array = created.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'o') {
                array[i] = 'o';
            }
        }
        return Math.min(dfs(pattern, index + 1, count + 1, new String(array)), dfs(pattern, index + 1, count, created));
    }
}
