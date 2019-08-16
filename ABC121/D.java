package ABC121;

import java.util.Scanner;

public class D {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long a = scanner.nextLong();
        final long b = scanner.nextLong();

        if (a == 0) {
            System.out.println(get(b));
            return;
        }

        final long fa = get(a - 1);
        final long fb = get(b);
        System.out.println(fa ^ fb);
    }

    private static long get(final long a) {
        final long mod = a % 4;
        switch ((int) mod) {
            case 0:
                return a;
            case 1:
                return 1;
            case 2:
                return a ^ 1;
            case 3:
                return 0;
        }

        throw new RuntimeException();
    }
}
