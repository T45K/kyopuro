package ABC.ABC035.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
グラフ 前後からダイクストラ 実装を見落としがち 有向グラフでダイクストラをする場合，隣接行列をMapで表現してるとぬるぽになる可能性に注意
有向グラフのときはリストの配列の方がええかも
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        final long t = scanner.nextInt();

        final long[] array = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = scanner.nextInt();
        }

        final List<Node>[] naturalGraph = new List[n + 1];
        final List<Node>[] reverseGraph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            naturalGraph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int c = scanner.nextInt();

            naturalGraph[a].add(new Node(b, c));
            reverseGraph[b].add(new Node(a, c));
        }

        final long[] naturalDistances = dijkstra(naturalGraph, n, t);
        final long[] reverseDistances = dijkstra(reverseGraph, n, t);

        long max = t * array[1];
        for (int i = 2; i <= n; i++) {
            if (naturalDistances[i] + reverseDistances[i] > t) {
                continue;
            }

            max = Math.max(max, (t - naturalDistances[i] - reverseDistances[i]) * array[i]);
        }
        System.out.println(max);
    }

    private static long[] dijkstra(final List<Node>[] graph, final int n, final long t) {
        final long[] distances = new long[n + 1];
        Arrays.fill(distances, t);
        distances[1] = 0;
        final PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingLong(node -> node.cost));
        queue.addAll(graph[1]);
        while (!queue.isEmpty()) {
            final Node next = queue.poll();
            if (next.cost >= distances[next.to]) {
                continue;
            }

            distances[next.to] = next.cost;
            final List<Node> nextList = graph[next.to].stream()
                    .map(node -> new Node(node.to, node.cost + next.cost))
                    .collect(Collectors.toList());
            queue.addAll(nextList);
        }
        return distances;
    }

    static class Node {
        final int to;
        final int cost;

        Node(final int to, final int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
