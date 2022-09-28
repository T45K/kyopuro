package AtCoder.ABC.ABC270.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        final List<Integer> list = Stream.generate(scanner::nextInt)
            .limit(k)
            .collect(Collectors.toList());
        final int[] myTurn = new int[n + 1];
        final int[] yourTurn = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            final int index = i;
            final int takeCount = list.stream()
                .takeWhile(tmp -> tmp <= index)
                .max(Comparator.comparing(tmp -> yourTurn[index - tmp] + tmp))
                .orElseThrow(); // 今後もっとも多くの個数が取れる個数
            myTurn[i] = yourTurn[index - takeCount] + takeCount; // 自分のターンは「今後もっとも多くの個数が取れる個数」+「その個数を取った場合の相手の出方」
            yourTurn[i] = myTurn[index - takeCount]; // 相手のターンでは、相手も「今後もっとも多くの個数が取れる個数」を取る
        }

        System.out.println(myTurn[n]);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        String next() {
            if (!tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
