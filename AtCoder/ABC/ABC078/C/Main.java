package AtCoder.ABC.ABC078.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        System.out.println(((n - m) * 100 + m * 1900) * (int) Math.pow(2, m));
    }
}
