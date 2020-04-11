package AtCoder.ABC.ABC113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class C {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        final Map<Integer, Integer> indexMap = new HashMap<>();
        final Map<Integer, List<Integer>> prefectureCityMap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            final int prefecture = scanner.nextInt();
            final int city = scanner.nextInt();
            indexMap.put(city, i);
            prefectureCityMap.computeIfAbsent(prefecture, list -> new ArrayList<>())
                    .add(city);
        }

        final String[] answer = new String[m];

        for (final Map.Entry<Integer, List<Integer>> entry : prefectureCityMap.entrySet()) {
            Collections.sort(entry.getValue());
            for (int i = 0; i < entry.getValue().size(); i++) {
                final String id = String.format("%06d", entry.getKey()) + String.format("%06d", i + 1);
                answer[indexMap.get(entry.getValue().get(i))] = id;
            }
        }

        for (final String s : answer) {
            System.out.println(s);
        }
    }

    static class FastScanner {
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

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            return tokenizer.nextToken("\n");
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
