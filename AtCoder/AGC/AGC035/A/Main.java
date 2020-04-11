package AtCoder.AGC.AGC035.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            tmp ^= scanner.nextInt();
        }

        System.out.println(tmp == 0 ? "Yes" : "No");
    }
}
