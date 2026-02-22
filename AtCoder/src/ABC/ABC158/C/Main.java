package ABC.ABC158.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();

        for (int i = 1; i <= 200_000; i++) {
            if((int)(i * 0.08) == a && (int)(i * 0.10) == b){
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}
