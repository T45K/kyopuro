package AtCoder.ABC.ABC120;

import java.util.Scanner;

public class C {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char[] cubes = scanner.nextLine().toCharArray();

        int zeroCounter = 0;
        int oneCounter = 0;
        for (final char cube : cubes) {
            if (cube == '1') {
                oneCounter++;
            } else {
                zeroCounter++;
            }
        }

        System.out.println(2 * Math.min(zeroCounter, oneCounter));
    }
}
