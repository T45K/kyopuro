package ABC.ABC197.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
広義単調増加から，同じ色のボールは同じタイミングで取らないといけない
同じ色のボールを最短で回収するのは
- 今いる地点から最も左のボールを拾ってから，最も右のボールを拾いに行く
- 今いる地点から最も右のボールを拾ってから，最も左のボールを拾いに行く
のどちらか
つまり，ある色を拾い終わった時，その色の一番左か一番右にいて，
次の色を拾いに行くためにその色の一番左か右に行くことになる
これを動的計画法で解くだけ
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final int x = scanner.nextInt();
            final int c = scanner.nextInt();
            map.computeIfAbsent(c, v -> new ArrayList<>()).add(x);
        }
        final long[][] distances = new long[map.size()][2]; // 0: right -> left, 1: left -> right
        final int[][] positions = new int[map.size()][2]; // 0: left position, 1 -> right position
        final List<Integer> keys = map.keySet().stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < keys.size(); i++) {
            final List<Integer> list = map.get(keys.get(i));
            Collections.sort(list);
            final int left = list.get(0);
            final int right = list.get(list.size() - 1);
            positions[i][0] = left;
            positions[i][1] = right;
            final int previousLeftPosition = (i == 0) ? 0 : positions[i - 1][0];
            final long previousLeftDistance = (i == 0) ? 0 : distances[i - 1][0];
            final int previousRightPosition = (i == 0) ? 0 : positions[i - 1][1];
            final long previousRightDistance = (i == 0) ? 0 : distances[i - 1][1];
            final int distance = right - left;
            distances[i][0] = distance + Math.min(
                Math.abs(right - previousLeftPosition) + previousLeftDistance,
                Math.abs(right - previousRightPosition) + previousRightDistance);
            distances[i][1] = distance + Math.min(
                Math.abs(left - previousLeftPosition) + previousLeftDistance,
                Math.abs(left - previousRightPosition) + previousRightDistance);
        }
        final long answer = Math.min(
            Math.abs(positions[map.size() - 1][0]) + distances[map.size() - 1][0],
            Math.abs(positions[map.size() - 1][1]) + distances[map.size() - 1][1]);
        System.out.println(answer);
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
