package AtCoder.ABC.ABC143.C;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();
        final List<Character> slimes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            slimes.add(s.charAt(i));
        }

        for (int i = 0; i < slimes.size() - 1; i++) {
            if (slimes.get(i) == slimes.get(i + 1)) {
                slimes.remove(i);
                i--;
            }
        }

        System.out.println(slimes.size());
    }
}
