package ABC136.C;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int counter = scanner.nextInt();

        int left = 0;
        for (int i = 0; i < counter; i++) {
            final int stair = scanner.nextInt() - 1;
            if (stair >= left) {
                left = stair;
            } else {
                if (left > stair + 1) {
                    System.out.println("No");
                    return;
                }
            }
        }

        System.out.println("Yes");
    }
}
