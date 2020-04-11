package AtCoder.ABC.ABC140.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] b = new int[n - 1];

        for (int i = 0; i < n - 1; i++) {
            b[i] = scanner.nextInt();
        }

        int answer = b[0];

        for (int i = 1; i < n - 1; i++) {
            answer += Math.min(b[i], b[i - 1]);
        }
        answer += b[n - 2];
        System.out.println(answer);
    }
}
