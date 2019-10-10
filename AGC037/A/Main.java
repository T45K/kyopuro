package AGC037.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char[] chars = scanner.nextLine().toCharArray();

        String prev = "";
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (final char aChar : chars) {
            builder.append(aChar);
            if (!builder.toString().equals(prev)) {
                count++;
                prev = builder.toString();
                builder = new StringBuilder();
            }
        }

        System.out.println(count);
    }
}
