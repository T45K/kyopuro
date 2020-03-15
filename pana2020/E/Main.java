package pana2020.E;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// TODO solve
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String a = scanner.next();
        final String b = scanner.next();
        final String c = scanner.next();

        int min = Integer.MAX_VALUE;
        for (final String value : Arrays.asList(count(a, b, c), count(a, c, b), count(b, a, c), count(b, c, a), count(c, a, b), count(c, b, a))) {
            min = Math.min(min, value.length());
        }
        System.out.println(min);
    }

    private static String count(final String a, final String b, final String c) {
        final String s = concat(a.toCharArray(), b.toCharArray());
        return concat(s.toCharArray(), c.toCharArray());
    }

    private static String concat(final char[] a, final char[] b) {
        for (int i = 0; i < a.length; i++) {
            boolean flag = true;
            final List<Pair> pairs = new ArrayList<>();
            for (int j = 0; j < a.length - i && j < b.length; j++) {
                if (a[i + j] == '?' || b[j] == '?') {
                    if (a[i + j] != '?') {
                        pairs.add(new Pair(j, a[i + j]));
                    } else if (b[j] != '?') {
                        pairs.add(new Pair(j, b[j]));
                    }
                    continue;
                }
                if (a[i + j] != b[j]) {
                    for (final Pair pair : pairs) {
                        a[i + pair.index] = pair.c;
                        b[pair.index] = pair.c;
                    }
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return new String(a).substring(0, i) + new String(b);
            }
        }

        return new String(a) + new String(b);
    }

    static class Pair {
        int index;
        char c;

        Pair(final int index, final char c) {
            this.index = index;
            this.c = c;
        }
    }
}
