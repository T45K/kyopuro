package nikkei2019.C;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // TODO fix
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        final int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if(a[i] > b[i]){
                count++;
            }
        }

        if(count > n-2){
            System.out.println("No");
            return;
        }

        Arrays.sort(a);
        Arrays.sort(b);

        for (int i = 0; i < n; i++) {
            if(a[i] > b[i]){
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }
}
