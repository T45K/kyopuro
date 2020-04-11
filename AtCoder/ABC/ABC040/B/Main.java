package AtCoder.ABC.ABC040.B;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// TODO fix
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pairs.add(new Pair(scanner.nextInt(), scanner.nextInt()));
        }

        pairs.sort((o1, o2) -> o2.b - o1.b);

        final List<Pair> resultA = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            final int former = pairs.get(i).a;
            final int latter = pairs.get(i).b;

            boolean flag = false;
            for (final Pair pair : resultA) {
                if (former >= pair.b || latter <= pair.a) {
                    continue;
                }
                if (pair.a <= former || latter <= pair.b) {
                    pair.a = Math.max(pair.a, former);
                    pair.b = Math.min(pair.b, latter);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }

            resultA.add(new Pair(former, latter));
            if (resultA.size() >= 3) {
                break;
            }
        }

        if (resultA.size() != 2) {
            int max = 0;
            int index = 0;

            for (int i = 0; i < n; i++) {
                final int former = pairs.get(i).a;
                final int latter = pairs.get(i).b;

                if (latter - former + 1 > max) {
                    max = latter - former + 1;
                    index = i;
                }
            }

            int former = 0;
            int latter = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (i == index) {
                    continue;
                }

                former = Math.max(former, pairs.get(i).a);
                latter = Math.min(latter, pairs.get(i).b);
            }

            System.out.println(max + Math.max(latter - former + 1, 0));
            return;
        }

        pairs.sort(Comparator.comparingInt(pair -> pair.a));

        final List<Pair> resultB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            final int former = pairs.get(i).a;
            final int latter = pairs.get(i).b;

            boolean flag = false;
            for (final Pair pair : resultB) {
                if (former >= pair.b || latter <= pair.a) {
                    continue;
                }
                if (pair.a <= former || latter <= pair.b) {
                    pair.a = Math.max(pair.a, former);
                    pair.b = Math.min(pair.b, latter);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }

            resultB.add(new Pair(former, latter));
            if (resultB.size() >= 3) {
                break;
            }
        }

        final Pair pairA1 = resultA.get(0);
        final Pair pairA2 = resultA.get(1);
        final Pair pairB1 = resultB.get(0);
        final Pair pairB2 = resultB.get(1);
        int max = 0;
        int index = 0;
        for (int i = 0; i < pairs.size(); i++) {
            final Pair pair = pairs.get(i);
            if (pair.b - pair.a + 1 > max) {
                max = pair.b - pair.a + 1;
                index = i;
            }
        }
        int former = 0;
        int latter = Integer.MAX_VALUE;
        for (int i = 0; i < pairs.size(); i++) {
            if (i == index) {
                continue;
            }

            former = Math.max(former, pairs.get(i).a);
            latter = Math.min(latter, pairs.get(i).b);
        }
        if (former < latter) {
            max += latter - former + 1;
        }

        System.out.println(Math.max(Math.max(pairA1.b + pairA2.b - pairA1.a - pairA2.a + 2, pairB1.b + pairB2.b - pairB1.a - pairB2.a + 2), max));
    }

    static class Pair {
        int a;
        int b;

        Pair(final int a, final int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return a + " " + b;
        }
    }
}
