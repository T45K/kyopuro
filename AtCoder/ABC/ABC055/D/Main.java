package AtCoder.ABC.ABC055.D;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
矛盾がない配列を生成する系 方針がすぐ立つ 簡単
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();

        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> s.charAt(i) == 'o' ? 1 : -1)
                .collect(Collectors.toList());

        System.out.println(answer(list));
    }

    private static String answer(final List<Integer> list) {
        final int[] a1 = {1, -1};
        final int[] a2 = {1, -1};
        final Function<int[], String> answerGenerator = array -> IntStream.range(0, list.size())
                .mapToObj(i -> new StringBuilder(array[i] == 1 ? "S" : "W"))
                .collect(Collectors.joining());

        final int[] array = new int[list.size()];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                array[0] = a1[i];
                array[1] = a2[j];
                if (simulate(array, list)) {
                    return answerGenerator.apply(array);
                }
            }
        }
        return "-1";
    }

    private static boolean simulate(final int[] array, final List<Integer> list) {
        for (int i = 1; i < array.length - 1; i++) {
            array[i + 1] = array[i] * list.get(i) * array[i - 1];
        }
        return isCorrect(array, list, array.length - 1) && isCorrect(array, list, 0);
    }

    private static boolean isCorrect(final int[] array, final List<Integer> list, final int index) {
        final int former = (array.length + index - 1) % array.length;
        final int latter = (index + 1) % array.length;
        return array[index] * list.get(index) * array[former] * array[latter] == 1;
    }
}
