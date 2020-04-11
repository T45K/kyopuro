package AtCoder.ABC.ABC140.D;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        scanner.nextLine();
        final String s = scanner.nextLine();
        final char[] array = s.toCharArray();
        final List<Integer> lrs = new ArrayList<>();

        char lr = array[0];
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (array[i] == lr) {
                count++;
                continue;
            }

            lrs.add(count);
            count = 1;
            lr = array[i];
        }

        lrs.add(count);

        int answer = lrs.get(0) - 1;
        for (int i = 1; i < lrs.size(); i++) {
            if (i <= 2 * k) {
                answer += lrs.get(i);
            } else {
                answer += lrs.get(i) - 1;
            }
        }

        System.out.println(answer);
    }
}
