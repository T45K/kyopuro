package AtCoder.ABC.ABC158.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        StringBuilder s = new StringBuilder(scanner.next());
        final int q = scanner.nextInt();
        final StringBuilder start = new StringBuilder();
        final StringBuilder end = new StringBuilder();

        boolean flag = true;
        for (int i = 0; i < q; i++) {
            final int t = scanner.nextInt();
            if (t == 1) {
                flag = !flag;
                continue;
            }

            final boolean f = scanner.nextInt() == 1;
            final String c = scanner.next();

            if (flag ^ f) {
                end.append(c);
            } else {
                start.append(c);
            }
        }

        if (flag) {
            System.out.println(start.reverse().toString() + s.toString() + end.toString());
        } else {
            System.out.println(end.reverse().toString() + s.reverse().toString() + start.toString());
        }
    }
}
