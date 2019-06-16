package ABC130;

import java.math.BigDecimal;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int w = scanner.nextInt();
        final int h = scanner.nextInt();
        final int x = scanner.nextInt();
        final int y = scanner.nextInt();

        final BigDecimal divide = BigDecimal.valueOf(w).multiply(BigDecimal.valueOf(h)).divide(BigDecimal.valueOf(2));
        final int able = x * 2 == w && y * 2 == h ? 1 : 0;
        System.out.println(divide + " " + able);
    }
}
