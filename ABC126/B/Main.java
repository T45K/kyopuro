package ABC126.B;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char[] chars = scanner.nextLine().toCharArray();

        if (isMonth(chars[0], chars[1]) && isMonth(chars[2], chars[3])) {
            System.out.println("AMBIGUOUS");
        } else if (isMonth(chars[0], chars[1]) && !isMonth(chars[2], chars[3])) {
            System.out.println("MMYY");
        } else if (!isMonth(chars[0], chars[1]) && isMonth(chars[2], chars[3])) {
            System.out.println("YYMM");
        } else {
            System.out.println("NA");
        }
    }

    private static boolean isMonth(final char a, final char b) {
        if (a == '0') {
            return !(b == '0');
        } else if (a == '1') {
            return b == '0' || b == '1' || b == '2';
        } else {
            return false;
        }
    }
}
