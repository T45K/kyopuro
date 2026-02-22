package ABC.ABC128;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int queueLength = scanner.nextInt();
        final int operation = scanner.nextInt();

        final List<Integer> queue = new LinkedList<>();

        for (int i = 0; i < queueLength; i++) {
            queue.add(scanner.nextInt());
        }

        final int counter = Math.min(queueLength, operation);
        int res = 0;

        for (int i = 0; i <= counter; i++) {
            for (int j = 0; j <= i; j++) {
                final LinkedList<Integer> clonedQueue = new LinkedList<>(queue);
                final List<Integer> selection = new ArrayList<>();
                for (int k = 0; k < j; k++) {
                    selection.add(clonedQueue.pollFirst());
                }
                for (int k = j; k < i; k++) {
                    selection.add(clonedQueue.pollLast());
                }
                Collections.sort(selection);
                for (int l = 0; l < operation - i; l++) {
                    if (selection.isEmpty()) {
                        break;
                    }

                    if (selection.get(0) < 0) {
                        selection.remove(0);
                    } else {
                        break;
                    }
                }

                final int sum = selection.stream().mapToInt(Integer::intValue).sum();
                if (sum > res) {
                    res = sum;
                }
            }
        }
        System.out.println(res);
    }
}
