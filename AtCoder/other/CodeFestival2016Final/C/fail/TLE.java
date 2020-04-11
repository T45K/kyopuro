package AtCoder.other.CodeFestival2016Final.C.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TLE {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Map<Integer, List<Integer>> manToLang = new HashMap<>();
        final Map<Integer, List<Integer>> langToMan = new HashMap<>();
        for (int man = 1; man <= n; man++) {
            final int k = scanner.nextInt();
            for (int j = 0; j < k; j++) {
                final int lang = scanner.nextInt();
                manToLang.computeIfAbsent(man, v -> new ArrayList<>()).add(lang);
                langToMan.computeIfAbsent(lang, v -> new ArrayList<>()).add(man);
            }
        }

        final boolean[] manVisited = new boolean[n + 1];
        final boolean[] langVisited = new boolean[m + 1];
        dfs(manVisited, langVisited, 1, manToLang, langToMan);

        final boolean answer = IntStream.rangeClosed(1, n)
                .mapToObj(i -> manVisited[i])
                .allMatch(Boolean::booleanValue);

        System.out.println(answer ? "YES" : "NO");
    }

    private static void dfs(final boolean[] manVisited, final boolean[] langVisited, final int currentMan, final Map<Integer, List<Integer>> manToLang, final Map<Integer, List<Integer>> langToMan) {
        manVisited[currentMan] = true;

        for (final int lang : manToLang.get(currentMan)) {
            if (langVisited[lang]) {
                continue;
            }

            for (final int man : langToMan.get(lang)) {
                if (manVisited[man]) {
                    continue;
                }

                dfs(manVisited, langVisited, man, manToLang, langToMan);
            }
        }
    }
}
