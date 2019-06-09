package ABC129;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int count = scanner.nextInt();
        final int[] point = new int[count];
        int all = 0;

        for (int i = 0; i < point.length; i++) {
            final int w = scanner.nextInt();
            point[i] = w;
            all += w;
        }

        int result = Integer.MAX_VALUE;
        int re = all;
        for (int i = 0; i < point.length; i++) {
            re -= point[i];
            if(Math.abs(all -re - re) < result){
                result = Math.abs(all - re - re);
            }
        }

        System.out.println(result);
    }
}
