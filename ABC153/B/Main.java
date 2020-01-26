package ABC153.B;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int h = scanner.nextInt();
        final int n = scanner.nextInt();

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += scanner.nextInt();
            if(sum >=h){
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
    }
}
