package ABC.ABC135;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int count = scanner.nextInt();

        int res = 0;
        for (int i = 1; i < count + 1; i++) {
            final int a = scanner.nextInt();
            if (a != i) {
                res++;
            }
        }

        if (res <= 2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
