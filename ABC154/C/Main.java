package ABC154.C;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        final Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            if (map.get(a) != null) {
                System.out.println("NO");
                return;
            } else {
                map.put(a, true);
            }
        }
        System.out.println("YES");
    }
}
