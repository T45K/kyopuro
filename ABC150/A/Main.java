package ABC150.A;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int k = scanner.nextInt();
        final int x = scanner.nextInt();
        System.out.println(k * 500 >= x ? "Yes" : "No");
    }
}
