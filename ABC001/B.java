package ABC001;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int m = scanner.nextInt();

        if (m < 100) {
            System.out.println("00");
        } else if (m <= 5000) {
            final int m2 = m * 10 / 1000;
            if (m2 < 10) {
                System.out.println("0" + m2);
            } else {
                System.out.println(m2);
            }
        } else if (m <= 30000) {
            System.out.println(m / 1000 + 50);
        } else if (m <= 70000) {
            System.out.println((m / 1000 - 30) / 5 + 80);
        } else {
            System.out.println(89);
        }
    }
}
