package AtCoder.ABC.ABC098.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();
        int right = 0;

        for (final char c : s.toCharArray()) {
            if (c == 'E') {
                right++;
            }
        }

        int answer = Integer.MAX_VALUE;
        int left = 0;
        for (int i = 0; i < n; i++) {
            final boolean isLeft = s.charAt(i) == 'W';
            if (!isLeft) {
                right--;
            }

            answer = Math.min(answer, right + left);

            if (isLeft) {
                left++;
            }
        }

        System.out.println(answer);
    }
}
