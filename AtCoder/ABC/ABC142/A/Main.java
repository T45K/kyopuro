package AtCoder.ABC.ABC142.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int div = n / 2;

        System.out.println((float) (n-div) / (float) n);
    }
}
