package AtCoder.AtCoder.AGC.AGC043.B;

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
                .mapToObj(i -> s.charAt(i) - '1')
                .collect(Collectors.toList());

        final int parity = calcParity(list, n);

        if (parity == 1) {
            System.out.println(1);
            return;
        }

        final boolean isOneIncluded = list.stream()
                .anyMatch(i -> i == 1);

        if (isOneIncluded) {
            System.out.println(0);
            return;
        }

        final List<Integer> divedList = list.stream()
                .map(i -> i / 2)
                .collect(Collectors.toList());

        final int divedParity = calcParity(divedList, n);
        if (divedParity == 0) {
            System.out.println(0);
        } else {
            System.out.println(2);
        }
    }

    private static int calcParity(final List<Integer> list, final int n) {
        int parity = list.get(0) ^ list.get(n - 1) % 2;
        final String binary = new StringBuilder(Integer.toBinaryString(n - 1)).reverse().toString();
        for (int i = 1; i < n - 1; i++) {
            final int a = list.get(i);
            final String iString = new StringBuilder(Integer.toBinaryString(i)).reverse().toString();
            int coefficient = 1;
            for (int j = 0; j < binary.length(); j++) {
                if (coefficient == 0) {
                    break;
                }
                coefficient *= combine(binary.charAt(j) - '0', j < iString.length() ? iString.charAt(j) - '0' : 0);
            }
            parity ^= coefficient * a;
        }

        return parity % 2;
    }

    private static int combine(final int n, final int k) {
        return k > n ? 0 : 1;
    }
}
