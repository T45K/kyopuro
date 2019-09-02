package student.C;

import java.util.Scanner;

public class Main {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        scanner.nextLine();
        final char[] s = scanner.nextLine().toCharArray();

        if (s[0] == 'W') {
            System.out.println(0);
            return;
        }

        final char[] lrs = new char[s.length];
        lrs[0] = 'L';

        long numOfL = 1;
        long numOfR = 0;
        long answer = 1;

        for (int i = 1; i < s.length; i++) {
            if (s[i] == s[i - 1]) {
                lrs[i] = lrs[i - 1] == 'L' ? 'R' : 'L';
            } else {
                lrs[i] = lrs[i - 1] == 'L' ? 'L' : 'R';
            }

            if (lrs[i] == 'L') {
                numOfL++;
            } else {
                answer = answer * (numOfL - numOfR) % MOD;
                numOfR++;
            }
        }

        if (numOfL != numOfR) {
            System.out.println(0);
            return;
        }

        for (int i = 2; i <= s.length / 2; i++) {
            answer = answer * (long) i % MOD;
        }

        System.out.println(answer);
    }
}
