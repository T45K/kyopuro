package ABC127.C;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        int min = 0;
        int max = n;

        for (int i = 0; i < m; i++) {
            min = Math.max(min, scanner.nextInt());
            max = Math.min(max,scanner.nextInt());
        }

        System.out.println(Math.max(0,max - min + 1));
    }
}
