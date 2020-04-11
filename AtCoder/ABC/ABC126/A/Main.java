package AtCoder.ABC.ABC126.A;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        scanner.nextInt();
        final int i = scanner.nextInt();
        scanner.nextLine();
        final char[] chars = scanner.nextLine().toCharArray();

        if (chars[i - 1] == 'A') {
            chars[i - 1] = 'a';
        } else if (chars[i - 1] == 'B') {
            chars[i - 1] = 'b';
        } else {
            chars[i - 1] = 'c';
        }

        System.out.println(chars);
    }
}
