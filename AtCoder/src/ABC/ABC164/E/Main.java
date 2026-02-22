package ABC.ABC164.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/*
ダイクストラ 通常の頂点数に加えて持っている金額という次元も加えた二次元配列のでの移動を考える
解説AC こういうダイクストラもあるんやって感じ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final int s = scanner.nextInt();

        final Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int u = scanner.nextInt();
            final int v = scanner.nextInt();
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            graph.computeIfAbsent(u, value -> new ArrayList<>()).add(new Edge(v, a, b));
            graph.computeIfAbsent(v, value -> new ArrayList<>()).add(new Edge(u, a, b));
        }

        final City[] cities = new City[n + 1];
        for (int i = 1; i <= n; i++) {
            final int c = scanner.nextInt();
            final int d = scanner.nextInt();
            cities[i] = new City(c, d);
        }

        final long[][] answers = new long[n + 1][2501];
        for (final long[] array : answers) {
            Arrays.fill(array, Long.MAX_VALUE);
        }
        final PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingLong(state -> state.time));

        int index;
        for (index = 0; s + cities[1].rate * index < 2500; index++) {
            final int currentMoney = (int) (s + cities[1].rate * index);
            final long currentTime = cities[1].time * index;
            answers[1][currentMoney] = currentTime;
            for (final Edge next : graph.get(1)) {
                if (currentMoney >= next.cost) {
                    queue.add(new State(next.to, currentMoney - next.cost, currentTime + next.time));
                }
            }
        }
        answers[1][2500] = cities[1].time * index;
        for (final Edge next : graph.get(1)) {
            queue.add(new State(next.to, 2500 - next.cost, cities[1].time * index + next.time));
        }

        final Set<Integer> set = new HashSet<>();
        set.add(1);
        while (!queue.isEmpty()) {
            final State state = queue.poll();
            if (state.money < 0 || state.time >= answers[state.position][state.money]) {
                continue;
            }

            final List<Edge> nextList = graph.get(state.position);
            boolean flag = true;
            for (index = 0; state.money + cities[state.position].rate * index < 2500; index++) {
                final int currentMoney = (int) (state.money + cities[state.position].rate * index);
                final long currentTime = state.time + cities[state.position].time * index;
                if (currentTime >= answers[state.position][currentMoney]) {
                    flag = false;
                    break;
                }

                answers[state.position][currentMoney] = currentTime;
                for (final Edge next : nextList) {
                    queue.add(new State(next.to, currentMoney - next.cost, currentTime + next.time));
                }
            }
            if (flag && state.time + cities[state.position].time * index < answers[state.position][2500]) {
                answers[state.position][2500] = state.time + cities[state.position].time * index;
                for (final Edge next : nextList) {
                    queue.add(new State(next.to, 2500 - next.cost, answers[state.position][2500] + next.time));
                }
            }

            set.add(state.position);
            if (set.size() == n) {
                break;
            }
        }

        IntStream.rangeClosed(2, n)
                .mapToObj(i -> Arrays.stream(answers[i]))
                .map(LongStream::min)
                .map(OptionalLong::orElseThrow)
                .forEach(System.out::println);
    }

    private static class State {
        final int position;
        final int money;
        final long time;

        State(final int position, final int money, final long time) {
            this.position = position;
            this.money = money;
            this.time = time;
        }
    }

    private static class Edge {
        final int to;
        final int cost;
        final long time;

        Edge(final int to, final int cost, final long time) {
            this.to = to;
            this.cost = cost;
            this.time = time;
        }
    }

    private static class City {
        final long rate;
        final long time;

        City(final long rate, final long time) {
            this.rate = rate;
            this.time = time;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
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
    