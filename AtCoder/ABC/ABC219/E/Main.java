package AtCoder.ABC.ABC219.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
以下の条件を含むマスの集合をbit全探索で求める
1. 全ての家を含む
2. 連結
3. 内部に空白を含まない．つまり，全ての空白が一番外側と接続している
 */
public class Main {
    private static final int[] xArray = {1, 2};
    private static final int[] yArray = {0, 3};

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final boolean[][] houses = new boolean[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                houses[i][j] = scanner.nextInt() == 1;
            }
        }

        final long count = IntStream.range(0, 1 << 16)
            .filter(bit -> {
                final boolean[][] bitTable = toTable(bit);
                if (!includeHouse(bitTable, houses)) {
                    return false;
                }
                final int bitCount = Integer.bitCount(bit);
                if (!isConnected(bitTable, bitCount)) {
                    return false;
                }
                return !existsRemoteBlank(bitTable);
            })
            .count();
        System.out.println(count);
    }

    private static boolean existsRemoteBlank(final boolean[][] bitTable) {
        final boolean[][] isVisited = new boolean[4][4];
        for (int i = 0; i < 4; i++) {
            if (!bitTable[0][i] && !isVisited[0][i]) {
                isVisited[0][i] = true;
                dfs(0, i, bitTable, isVisited, false);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (!bitTable[xArray[i]][yArray[j]] && !isVisited[xArray[i]][yArray[j]]) {
                    isVisited[xArray[i]][yArray[j]] = true;
                    dfs(xArray[i], yArray[j], bitTable, isVisited, false);
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if (!bitTable[3][i] && !isVisited[3][i]) {
                isVisited[3][i] = true;
                dfs(3, i, bitTable, isVisited, false);
            }
        }
        return IntStream.range(0, 16)
            .anyMatch(i -> !bitTable[i / 4][i % 4] && !isVisited[i / 4][i % 4]);
    }

    private static boolean isConnected(final boolean[][] bitTable, final int bitCount) {
        final boolean[][] isVisited = new boolean[4][4];
        loop:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (bitTable[i][j]) {
                    isVisited[i][j] = true;
                    dfs(i, j, bitTable, isVisited, true);
                    break loop;
                }
            }
        }
        final long count = IntStream.range(0, 16)
            .filter(i -> isVisited[i / 4][i % 4])
            .count();
        return count == bitCount;
    }

    private static void dfs(final int x, final int y, final boolean[][] bitTable, final boolean[][] isVisited, final boolean isInside) {
        if (x > 0 && bitTable[x - 1][y] == isInside && !isVisited[x - 1][y]) {
            isVisited[x - 1][y] = true;
            dfs(x - 1, y, bitTable, isVisited, isInside);
        }
        if (x < 3 && bitTable[x + 1][y] == isInside && !isVisited[x + 1][y]) {
            isVisited[x + 1][y] = true;
            dfs(x + 1, y, bitTable, isVisited, isInside);
        }
        if (y > 0 && bitTable[x][y - 1] == isInside && !isVisited[x][y - 1]) {
            isVisited[x][y - 1] = true;
            dfs(x, y - 1, bitTable, isVisited, isInside);
        }
        if (y < 3 && bitTable[x][y + 1] == isInside && !isVisited[x][y + 1]) {
            isVisited[x][y + 1] = true;
            dfs(x, y + 1, bitTable, isVisited, isInside);
        }
    }

    private static boolean[][] toTable(final int bit) {
        final boolean[][] table = new boolean[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                table[i][j] = ((1 << (i * 4 + j)) & bit) > 0;
            }
        }
        return table;
    }

    private static boolean includeHouse(final boolean[][] bitTable, final boolean[][] houses) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (houses[i][j] && !bitTable[i][j]) {
                    return false;
                }
            }
        }
        return true;
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
