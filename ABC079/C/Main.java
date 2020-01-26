package ABC079.C;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final char[] tmp = scanner.next().toCharArray();
        final Integer[] array = IntStream.range(0, 4)
                .mapToObj(i -> tmp[i] - '0')
                .toArray(Integer[]::new);

        System.out.println(recursive(array, 1, array[0], Integer.toString(array[0])) + "=7");
    }

    private static String recursive(final Integer[] array, final int index, final int sum, final String formula) {
        if (index == 3) {
            if (sum + array[3] == 7) {
                return formula + "+" + array[3];
            }
            if (sum - array[3] == 7) {
                return formula + "-" + array[3];
            }
            return null;
        }

        final String plus = recursive(array, index + 1, sum + array[index], formula + "+" + array[index]);
        if (plus != null) {
            return plus;
        }

        return recursive(array, index + 1, sum - array[index], formula + "-" + array[index]);
    }
}
