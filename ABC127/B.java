package ABC127;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int r = scanner.nextInt();
        final int d = scanner.nextInt();
        int x = scanner.nextInt();

        for (int i = 0;i<10;i++){
            x = r * x - d;
            System.out.println(x);
        }
    }
}
