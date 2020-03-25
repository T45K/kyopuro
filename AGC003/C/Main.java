package AGC003.C;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final List<Integer> list = IntStream.range(0, scanner.nextInt())
                .mapToObj(i -> scanner.nextInt())
                .collect(Collectors.toList());

        final Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            index.put(list.get(i), i);
        }

        Collections.sort(list);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            final int tmp = list.get(i);
            if (i % 2 != index.get(tmp) % 2) {
                count++;
            }
        }

        System.out.println(count / 2);
    }
}
