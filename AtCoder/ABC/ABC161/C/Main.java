package AtCoder.ABC.ABC161.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final long n = scanner.nextLong();
        final long k = scanner.nextLong();

        final long mod1 = (n + k) % k;
        final long mod2 = (k - mod1) % k;
        System.out.println(Math.min(mod1, mod2));
    }
}
