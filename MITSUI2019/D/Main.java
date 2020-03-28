package MITSUI2019.D;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final String s = scanner.next();

        final List<Integer> list = IntStream.range(0, n)
                .mapToObj(i -> s.charAt(i) - '0')
                .collect(Collectors.toList());

        final int[] bitArray = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            bitArray[i] = bitArray[i + 1] | 1 << list.get(i + 1);
        }

        final boolean[] isAnswer = new boolean[1000];
        for (int i = 0; i < list.size(); i++) {
            final boolean[] isUsed = new boolean[10];
            for (int j = i + 1; j < list.size(); j++) {
                if (isUsed[list.get(j)]) {
                    continue;
                }
                isUsed[list.get(j)] = true;

                int bit = bitArray[j];
                for (int k = 0; k < 10; k++) {
                    if ((bit & 1) == 1) {
                        isAnswer[100 * list.get(i) + 10 * list.get(j) + k] = true;
                    }
                    bit >>= 1;
                }
            }
        }

        final long answer = IntStream.range(0, 1000)
                .mapToObj(i -> isAnswer[i])
                .filter(Boolean::booleanValue)
                .count();
        System.out.println(answer);
    }
}
