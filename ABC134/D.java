package ABC134;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] boxes = new int[n];
        final int[] answerChoice = new int[n];

        for (int i = 0; i < n; i++) {
            boxes[i] = scanner.nextInt();
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i > n / 2) {
                answerChoice[i] = boxes[i];
                continue;
            }

            int sum = 0;
            for (int j = 2 * i + 1; j < n; j += i + 1) {
                sum += answerChoice[j];
            }

            if (sum % 2 == boxes[i]) {
                answerChoice[i] = 0;
            } else {
                answerChoice[i] = 1;
            }
        }

        final List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (answerChoice[i] == 1) {
                list.add(Integer.toString(i + 1));
            }
        }

        System.out.println(list.size());
        if (list.size() != 0)
            System.out.println(String.join(" ", list));
    }
}
