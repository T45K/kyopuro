package AtCoder.ABC.ABC148.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();
        final String t = scanner.next();

        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            builder.append(s.charAt(i));
            builder.append(t.charAt(i));
        }

        System.out.println(builder.toString());
    }
}
