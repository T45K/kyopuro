package ABC097.C;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();
        final int k = scanner.nextInt() - 1;
        final Set<String> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j <= 5 && i + j <= s.length(); j++) {
                set.add(s.substring(i, i + j));
            }
        }

        final List<String> list = new ArrayList<>(set);
        list.sort(Comparator.naturalOrder());

        System.out.println(list.get(k));
    }
}
