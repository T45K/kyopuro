package AtCoder.ABC.ABC176.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
最初に行，列それぞれの爆破対象を数える
次に，爆破対象が最大である行，列にしぼりこむ
絞り込んだ行と列から一つずつ選んだ時の爆破対象の和が答えとなる
また，行と列が交差する箇所に爆破対象があるとき，和は重複する
なので，交差する箇所に爆破対象があるかを確認する
このとき，爆破対象の数は高々M個なので，行数*列数がMを超過する場合，
必ず爆破対象が存在しない交差箇所があるので答えは和となる．
そうでない場合，全ての交差箇所を調べればよい
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        scanner.nextInt();
        scanner.nextInt();
        final int m = scanner.nextInt();

        final Map<Integer, Integer> hCounts = new HashMap<>();
        final Map<Integer, Integer> wCounts = new HashMap<>();
        final Map<Integer, Set<Integer>> points = new HashMap<>();
        for (int i = 0; i < m; i++) {
            final int tmpH = scanner.nextInt();
            final int tmpW = scanner.nextInt();
            hCounts.compute(tmpH, (k, v) -> v == null ? 1 : v + 1);
            wCounts.compute(tmpW, (k, v) -> v == null ? 1 : v + 1);
            points.computeIfAbsent(tmpH, v -> new HashSet<>()).add(tmpW);
        }

        final int maxH = hCounts.values().stream().max(Integer::compare).orElseThrow();
        final int maxW = wCounts.values().stream().max(Integer::compare).orElseThrow();

        final Map<Integer, Integer> filteredH = hCounts.entrySet().stream()
            .filter(e -> e.getValue() == maxH)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        final Map<Integer, Integer> filteredW = wCounts.entrySet().stream()
            .filter(e -> e.getValue() == maxW)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        final int base = maxH + maxW;
        if ((long) filteredH.size() * (long) filteredW.size() > m) {
            System.out.println(base);
            return;
        }


        for (final Map.Entry<Integer, Integer> entryH : filteredH.entrySet()) {
            for (final Map.Entry<Integer, Integer> entryW : filteredW.entrySet()) {
                if (points.containsKey(entryH.getKey()) && points.get(entryH.getKey()).contains(entryW.getKey())) {
                    continue;
                }

                System.out.println(base);
                return;
            }
        }

        System.out.println(base - 1);
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
