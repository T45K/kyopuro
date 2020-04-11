package AtCoder.ABC.ABC040.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
グラフ 難しい Union-Findを改良する，質問の順番を入れ替えるという発想がポイント
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> -edge.cost));
        for (int i = 0; i < m; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();
            final int y = scanner.nextInt();

            queue.add(new Edge(a, b, y));
        }

        final int q = scanner.nextInt();
        final List<Question> questions = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            final int v = scanner.nextInt();
            final int w = scanner.nextInt();

            questions.add(new Question(i, v, w));
        }

        questions.sort(Comparator.comparingInt(question -> -question.year));
        final UnionFindTree tree = new UnionFindTree(n + 1);
        final int[] answer = new int[q];
        for (final Question question : questions) {
            while (!queue.isEmpty() && queue.peek().cost > question.year) {
                final Edge poll = Optional.ofNullable(queue.poll()).orElseThrow(RuntimeException::new);
                tree.unit(poll.a, poll.b);
            }

            answer[question.index] = tree.getConnected(question.city);
        }

        final String answerString = IntStream.range(0, q)
                .mapToObj(i -> new StringBuilder(Integer.toString(answer[i])))
                .reduce((sum, element) -> sum.append("\n").append(element))
                .get()
                .toString();
        System.out.println(answerString);
    }

    private static class Edge {
        final int a;
        final int b;
        final int cost;

        Edge(final int a, final int b, final int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    private static class Question {
        final int index;
        final int city;
        final int year;

        Question(final int index, final int city, final int year) {
            this.index = index;
            this.city = city;
            this.year = year;
        }
    }

    private static class UnionFindTree {
        private final List<Integer> indices = new ArrayList<>();
        private final int[] connected;
        private Integer[] nodes;

        UnionFindTree(final int numOfNodes) {
            this.nodes = init(numOfNodes);
            this.connected = new int[numOfNodes];
            Arrays.fill(connected, 1);
        }

        int getConnected(final int node) {
            return connected[getRoot(node)];
        }

        private Integer[] init(final int numOfNodes) {
            return IntStream.rangeClosed(0, numOfNodes)
                    .boxed()
                    .toArray(Integer[]::new);
        }

        int getRoot(final int nodeNumber) {
            final int rootNode = nodes[nodeNumber];
            if (rootNode == nodeNumber) {
                for (final Integer index : indices) {
                    nodes[index] = rootNode;
                }
                indices.clear();
                return nodeNumber;
            }

            indices.add(nodeNumber);
            return getRoot(rootNode);
        }

        void unit(final int nodeA, final int nodeB) {
            final int rootA = getRoot(nodeA);
            final int rootB = getRoot(nodeB);

            if (rootA == rootB) {
                return;
            }

            nodes[Math.max(rootA, rootB)] = Math.min(rootA, rootB);
            connected[Math.min(rootA, rootB)] = connected[rootA] + connected[rootB];
        }
    }
}
