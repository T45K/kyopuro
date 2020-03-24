package Tenka1_2012.B;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String SNAKE_CASE_REGEX = "[a-z][a-z0-9]*(?:_[a-z][a-z0-9]*)*";
    private static final String CAMEL_CASE_REGEX = "[a-z][A-Za-z0-9]*";

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();

        if (s.matches("_*" + SNAKE_CASE_REGEX + "_*")) {
            final Pattern pattern = Pattern.compile("(_*)(" + SNAKE_CASE_REGEX + ")(_*)");
            final Matcher matcher = pattern.matcher(s);
            //noinspection ResultOfMethodCallIgnored
            matcher.find();
            final String snakeCase = matcher.group(2);
            final String[] array = snakeCase.split("_");
            System.out.print(matcher.group(1) + array[0]);
            for (int i = 1; i < array.length; i++) {
                final char[] chars = array[i].toCharArray();
                chars[0] += 'A' - 'a';
                System.out.print(new String(chars));
            }
            System.out.println(matcher.group(3));
        } else if (s.matches("_*" + CAMEL_CASE_REGEX + "_*")) {
            for (final char c : s.toCharArray()) {
                if (c >= 'A' && c <= 'Z') {
                    System.out.print("_" + (char) (c - 'A' + 'a'));
                } else {
                    System.out.print(c);
                }
            }
            System.out.println();
        } else {
            System.out.println(s);
        }
    }
}
