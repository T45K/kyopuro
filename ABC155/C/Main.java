package ABC155.C;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final Map<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final String key = scanner.next();
            countMap.compute(key, (k, v) -> v == null ? 1 : v + 1);
        }

        final List<String> answers = new ArrayList<>();
        int max = -1;
        for (final Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > max) {
                answers.clear();
                answers.add(entry.getKey());
                max = entry.getValue();
            } else if (entry.getValue() == max) {
                answers.add(entry.getKey());
            }
        }

        Collections.sort(answers);
        for (final String answer : answers) {
            System.out.println(answer);
        }
    }
}
