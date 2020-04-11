package AtCoder.ABC.ABC109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class D {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int h = scanner.nextInt();
        final int w = scanner.nextInt();
        final int[][] table = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                table[i][j] = scanner.nextInt();
            }
        }

        final List<String> answers = new ArrayList<>();

        for (int i = 0; i < h; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < w; j++) {
                    if (i == h - 1 && j == w - 1) {
                        break;
                    }

                    if (table[i][j] % 2 == 0) {
                        continue;
                    }

                    if (j == w - 1) {
                        table[i][j]--;
                        table[i + 1][j]++;
                        answers.add((i + 1) + " " + (j + 1) + " " + (i + 2) + " " + (j + 1));
                        continue;
                    }

                    table[i][j]--;
                    table[i][j + 1]++;
                    answers.add((i + 1) + " " + (j + 1) + " " + (i + 1) + " " + (j + 2));
                }
            } else {
                for (int j = w - 1; j >= 0; j--) {
                    if (i == h - 1 && j == 0) {
                        break;
                    }

                    if (table[i][j] % 2 == 0) {
                        continue;
                    }

                    if (j == 0) {
                        table[i][j]--;
                        table[i + 1][j]++;
                        answers.add((i + 1) + " " + (j + 1) + " " + (i + 2) + " " + (j + 1));
                        continue;
                    }

                    table[i][j]--;
                    table[i][j - 1]++;
                    answers.add((i + 1) + " " + (j + 1) + " " + (i + 1) + " " + j);
                }
            }
        }

        System.out.println(answers.size());
        for (String answer : answers) {
            System.out.println(answer);
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
