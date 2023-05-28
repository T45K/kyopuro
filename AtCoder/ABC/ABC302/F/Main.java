package AtCoder.ABC.ABC302.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
あるノードで繋がっているセットで最短経路を計算する
一度計算したノード、セットは二度目以降は不要
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final List<List<Integer>> list = new ArrayList<>();
        final Map<Integer, List<Integer>> inverted = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int a = scanner.nextInt();
            final List<Integer> tmp = Stream.generate(scanner::nextInt)
                .limit(a)
                .collect(Collectors.toList());
            list.add(tmp);
            for (final Integer v : tmp) {
                inverted.computeIfAbsent(v, ignored -> new ArrayList<>()).add(i);
            }
        }

        final int[] distances = new int[m + 1];
        Arrays.fill(distances, -1);
        distances[1] = 0;
        final ArrayDeque<Item> queue = inverted.getOrDefault(1, Collections.emptyList()).stream()
            .map(groupNum -> new Item(0, groupNum))
            .collect(Collectors.toCollection(ArrayDeque::new));

        final boolean[] hasBeenCheckedSet = new boolean[n];
        for (final int groupNum : inverted.getOrDefault(1, Collections.emptyList())) {
            hasBeenCheckedSet[groupNum] = true;
        }

        while (!queue.isEmpty()) {
            final Item poll = queue.pollFirst();
            final int distance = distances[poll.fromNode] + 1;
            for (final Integer nextNode : list.get(poll.groupNum)) {
                if (distances[nextNode] != -1) {
                    continue;
                }
                distances[nextNode] = distance;
                for (final int nextGroupNum : inverted.getOrDefault(nextNode, Collections.emptyList())) {
                    if (hasBeenCheckedSet[nextGroupNum]) {
                        continue;
                    }
                    queue.addLast(new Item(nextNode, nextGroupNum));
                    hasBeenCheckedSet[nextGroupNum] = true;
                }
            }
            if (distances[m] != -1) {
                System.out.println(distances[m]);
                return;
            }
        }

        System.out.println(-1);
    }

    private static class Item {
        final int fromNode;
        final int groupNum;

        Item(final int fromNode, final int groupNum) {
            this.fromNode = fromNode;
            this.groupNum = groupNum;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
