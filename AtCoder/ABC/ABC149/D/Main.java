package AtCoder.ABC.ABC149.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final int rock = scanner.nextInt();
        final int scissors = scanner.nextInt();
        final int paper = scanner.nextInt();
        final String t = scanner.next();

        int answer = 0;
        for (int i = 0; i < k; i++) {
            char before = ' ';
            for (int j = i; j < n; j += k) {
                final char current = t.charAt(j);
                if (current == before) {
                    before = ' ';
                    continue;
                }

                switch (current) {
                    case 'r':
                        answer += paper;
                        break;
                    case 's':
                        answer += rock;
                        break;
                    case 'p':
                        answer += scissors;
                        break;
                }

                before = current;
            }
        }

        System.out.println(answer);
    }
}
