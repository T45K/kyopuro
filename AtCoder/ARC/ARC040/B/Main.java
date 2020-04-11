package AtCoder.ARC.ARC040.B;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 解き方: 後ろから貪欲
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int r = scanner.nextInt();
        final String s = scanner.next();

        final List<Boolean> list = IntStream.range(0, n)
                .mapToObj(i -> s.charAt(i) == 'o')
                .collect(Collectors.toList());

        int index = list.lastIndexOf(false);
        if (index == -1) {
            System.out.println(0);
            return;
        }
        int time = 1;
        int current = Math.max(0, index - r + 1);
        while (current > 0) {
            for (int i = current; i < list.size(); i++) {
                list.set(i, true);
            }
            index = list.lastIndexOf(false);
            if (index == -1) {
                time += current;
                break;
            }
            time++;
            time += current - Math.max(0, index - r + 1);
            current = Math.max(0, index - r + 1);
        }
        System.out.println(time);
    }
}
