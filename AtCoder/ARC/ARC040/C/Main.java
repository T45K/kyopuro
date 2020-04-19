package AtCoder.ARC.ARC040.C;

import java.util.Scanner;

/*
盤面操作 上の行の一番右の塗るべき場所を塗っていく感じで貪欲にやる
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        int prev = n;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            final int search = search(scanner.next().toCharArray(), prev - 1);
            if (search == -1) {
                prev = n;
            } else {
                prev = search;
                sum++;
            }
        }
        System.out.println(sum);
    }

    private static int search(final char[] array, final int end) {
        for (int i = end; i >= 0; i--) {
            if (array[i] == '.') {
                return i;
            }
        }
        return -1;
    }
}
