package ABC154.E;

import java.util.Scanner;

public class Main {
    // TODO solve
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String n = scanner.next();
        final int k = scanner.nextInt();

        if (k > n.length()) {
            System.out.println(0);
            return;
        }

        long answer = 0;
        if (k == 1) {
            answer = 9 * (n.length() - 1);
            answer += (n.charAt(0) - '0');
        } else if (k == 2) {
            answer = 81 * (n.length() - 1) * (n.length() - 2) / 2;
            answer += getAnswer(n, 0);
        } else if (k == 3) {
        }

        System.out.println(answer);
    }

    private static long getAnswer(final String n, final int begin) {
        long answer = 0;
        boolean existingZero = n.charAt(begin + 1) == '0';
        final int base = n.charAt(begin) - '0';
        for (int i = begin + 1; i < n.length(); i++) {
            if (!existingZero) {
                if (i == 1)
                    answer += (base - 1) * 9 + n.charAt(i) - '0';
                else
                    answer += base * 9;
                continue;
            }

            if (n.charAt(i) != '0') {
                existingZero = false;
            }
            answer += (base - 1) * 9;
        }
        return answer;
    }
}
