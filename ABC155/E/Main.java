package ABC155.E;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    // TODO solve
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.next();
        final int n = s.length();
        final Integer[] array = IntStream.range(0, n)
                .mapToObj(i -> s.charAt(i) - '0')
                .toArray(Integer[]::new);

        final List<Integer> sequence = new ArrayList<>();
        boolean greaterThan5 = false;
        int sum = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 5) {
                if (greaterThan5) {
                    sequence.add(5);
                } else {
                    sum += 5;
                }
            } else if (array[i] < 5) {
                sum += array[i];
                if (greaterThan5) {
                    greaterThan5 = false;
                    sum += 10 - sequence.get(0) + 1;
                    for (int j = 1; j < sequence.size(); j++) {
                        sum += 9 - sequence.get(j);
                    }
                    sequence.clear();
                }
            } else {
                greaterThan5 = true;
                sequence.add(array[i]);
            }
        }

        if (greaterThan5) {
            sum += 1 + 10 - sequence.get(0);
            for (int i = 1; i < sequence.size(); i++) {
                sum += 9 - sequence.get(i);
            }
        }

        System.out.println(sum);
        /*final int[] answer = new int[n];
        answer[n - 1] = Math.min(array[n - 1], 10 - array[n - 1] + 1);
        for (int i = array.length - 2; i >= 0; i--) {
            int sum = 10 - array[n - 1] + 1;
            for (int j = i + 1; j < n - 1; j++) {
                sum += 9 - array[j];
            }

            int tmp = sum;
            for (int j = i + 1; j < n; j++) {
                tmp = Math.max(answer[i-1],)
            }
        }*/
    }
}
