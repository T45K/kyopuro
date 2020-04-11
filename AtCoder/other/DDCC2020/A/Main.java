package AtCoder.other.DDCC2020.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();

        int answer = 0;
        switch (a) {
            case 3:
                answer += 100000;
                break;
            case 2:
                answer += 200000;
                break;
            case 1:
                answer += 300000;
        }

        switch (b) {
            case 3:
                answer += 100000;
                break;
            case 2:
                answer += 200000;
                break;
            case 1:
                answer += 300000;
        }

        if (a == 1 && b == 1) {
            answer += 400000;
        }

        System.out.println(answer);
    }
}
