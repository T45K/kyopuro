package ABC.ABC116;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class D {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int counter = scanner.nextInt();
        final int k = scanner.nextInt();
        final Sushi[] sushiArray = new Sushi[counter];

        for (int i = 0; i < counter; i++) {
            final Sushi sushi = new Sushi(scanner.nextInt(), scanner.nextLong());
            sushiArray[i] = sushi;
        }

        Arrays.sort(sushiArray, (o1, o2) -> (int) o2.getDeliciousness() - (int) o1.getDeliciousness());
        final Map<Integer, List<Long>> sushiMap = new HashMap<>();
        final Set<Integer> alreadyIncludedTopping = new HashSet<>();
        long partialAnswer = 0;
        final PriorityQueue<Sushi> allSushi = new PriorityQueue<>(Comparator.comparingInt(a -> (int) a.getDeliciousness()));
        for (int i = 0; i < k; i++) {
            final Sushi sushi = sushiArray[i];
            allSushi.add(sushi);
            partialAnswer += sushi.getDeliciousness();
            alreadyIncludedTopping.add(sushi.getTopping());
            sushiMap.computeIfAbsent(sushi.getTopping(), e -> new ArrayList<>())
                    .add(sushi.getDeliciousness());
        }

        long answer = partialAnswer + (long) alreadyIncludedTopping.size() * (long) alreadyIncludedTopping.size();

        for (int i = k; i < counter; i++) {
            final Sushi sushi = sushiArray[i];
            if (alreadyIncludedTopping.contains(sushi.getTopping())) {
                continue;
            }

            boolean endFlag = false;
            while (true) {
                if (allSushi.isEmpty()) {
                    endFlag = true;
                    break;
                }

                final Sushi poll = allSushi.poll();
                if (sushiMap.get(poll.getTopping()).size() > 1) {
                    alreadyIncludedTopping.add(sushi.getTopping());
                    final List<Long> list = sushiMap.get(poll.getTopping());
                    list.remove(list.size() - 1);
                    partialAnswer -= poll.getDeliciousness();
                    partialAnswer += sushi.getDeliciousness();
                    if (partialAnswer + (long) alreadyIncludedTopping.size() * (long) alreadyIncludedTopping.size() > answer) {
                        answer = partialAnswer + (long) alreadyIncludedTopping.size() * (long) alreadyIncludedTopping.size();
                    }
                    break;
                }
            }

            if (endFlag) {
                break;
            }
        }

        System.out.println(answer);
    }

    private static class Sushi {
        private final int topping;
        private final long deliciousness;

        Sushi(final int topping, final long deliciousness) {
            this.topping = topping;
            this.deliciousness = deliciousness;
        }

        int getTopping() {
            return topping;
        }

        long getDeliciousness() {
            return deliciousness;
        }
    }
}
