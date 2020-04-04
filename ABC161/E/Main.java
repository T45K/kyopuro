package ABC161.E;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO solve
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int c = scanner.nextInt();
        final String s = scanner.next();
        final List<Integer> workableDay = IntStream.range(0, n)
                .filter(i -> s.charAt(i) == 'o')
                .boxed()
                .collect(Collectors.toList());

        int hoge = workableDay.get(workableDay.size() - 1);
        int index = workableDay.size() - 1;
        final List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            final int tmp = Collections.binarySearch(workableDay, hoge - c - 1);
            if (tmp == index - 1) {
                answer.add(tmp);
            }
            index = tmp;
        }

        if (index == 0) {
            answer.add(1);
        }

        for (final Integer integer : answer) {
            System.out.println(index);
        }
    }
}
