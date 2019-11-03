package AGC040.A;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char[] s = scanner.nextLine().toCharArray();
        List<Long> words = new ArrayList<>();

        char current = s[0];
        long tmp = 0;
        for (final char c : s) {
            if (c == current) {
                tmp++;
            } else {
                words.add(tmp);
                current = c;
                tmp = 1;
            }
        }
        words.add(tmp);

        long result = 0;
        if (s[0] == '>') {
            result += words.get(0) * (words.get(0) + 1) / 2;
            words.remove(0);
        }
        for (int i = 1; i < words.size(); i = i + 2) {
            final long max = Math.max(words.get(i - 1), words.get(i));
            final long min = Math.min(words.get(i - 1), words.get(i));
            result += max * (max + 1) / 2;
            result += min * (min - 1) / 2;
        }

        if (s[s.length - 1] == '<') {
            final long end = words.get(words.size() - 1);
            result += end * (end + 1) / 2;
        }

        System.out.println(result);
    }
}
