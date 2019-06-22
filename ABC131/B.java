package ABC131;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int l = scanner.nextInt();

        int all = 0;
        int select = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            all += l + i - 1;
            if(Math.abs(l + i - 1) < Math.abs(select)){
                select = l + i - 1;
            }
        }

        System.out.println(all - select);
    }
}
