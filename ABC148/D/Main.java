package ABC148.D;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();

        int correct = 1;
        int wrong = 0;
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            if (a == correct) {
                correct++;
            } else {
                wrong++;
            }
        }

        if (correct == 1) {
            System.out.println(-1);
        } else {
            System.out.println(wrong);
        }
    }
}
