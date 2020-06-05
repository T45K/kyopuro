package AtCoder.other.code_formula_2014_final.F;

import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final int[] xArray = new int[101];
        final int[] yArray = new int[101];
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                final int i = x * 5 + y + 1;
                xArray[i] = 300 * x + 50 + i;
                yArray[i] = 300 * (y + 1) - i;
                xArray[50 + i] = 300 * x + 50 + i;
                yArray[50 + i] = 300 * y + 50 + i;
                xArray[51 - i] = 300 * (x + 1) - (51 - i);
                yArray[51 - i] = 300 * y + 51 - i;
                xArray[101 - i] = 300 * (x + 1) - (101 - i);
                yArray[101 - i] = 300 * (y + 1) - (101 - i);
            }
        }

        IntStream.rangeClosed(1, 100)
            .forEach(i -> System.out.println(xArray[i] + " " + yArray[i]));
    }
}
