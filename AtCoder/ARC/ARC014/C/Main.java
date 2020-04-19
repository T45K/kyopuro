package AtCoder.ARC.ARC014.C;

import java.util.Scanner;

/*
文字列操作 筒に入っているボールの数は高々3個までになる 簡単
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();
        final int[] count = new int[3];
        for (int i = 0; i < n; i++) {
            switch (s.charAt(i)) {
                case 'R':
                    count[0]++;
                    break;
                case 'G':
                    count[1]++;
                    break;
                default:
                    count[2]++;
                    break;
            }
        }
        int sum = 0;
        for (final int i : count) {
            sum += i % 2;
        }

        System.out.println(sum);
    }
}
