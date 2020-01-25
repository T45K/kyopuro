package ABC085.C;

import java.util.Scanner;

public class Main {
    private static final int YUKICHI = (int) 1e4;
    private static final int HIGUCHI = (int) (5 * 1e3);
    private static final int NOGUCHI = (int) 1e3;

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int y = scanner.nextInt();

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                if (YUKICHI * i + HIGUCHI * j + NOGUCHI * (n - i - j) == y) {
                    System.out.println(i + " " + j + " " + (n - i - j));
                    return;
                }
            }
        }

        System.out.println("-1 -1 -1");
    }
}
