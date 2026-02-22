package other.tenka1_2013_qualB.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
数列操作系，数字の値と何回出てくるかを記録しておく
Pop操作時に二分探索するのがポイント
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int q = scanner.nextInt();
        final int l = scanner.nextInt();
        final int[] numberArray = new int[q + 1];
        final int[] endArray = new int[q + 1];

        int size = 0;
        int arrayPointer = 0;
        for (int i = 0; i < q; i++) {
            final String query = scanner.next();
            switch (query) {
                case "Push": {
                    final int n = scanner.nextInt();
                    final int m = scanner.nextInt();
                    if (size > l - n) {
                        System.out.println("FULL");
                        return;
                    }
                    size += n;
                    final int last = endArray[arrayPointer];
                    arrayPointer++;
                    numberArray[arrayPointer] = m;
                    endArray[arrayPointer] = last + n;
                    break;
                }

                case "Pop": {
                    final int n = scanner.nextInt();
                    if (n > size) {
                        System.out.println("EMPTY");
                        return;
                    }
                    size -= n;
                    int index = Arrays.binarySearch(endArray, 0, arrayPointer + 1, size);
                    index = index >= 0 ? index : ~index;
                    arrayPointer = index;
                    endArray[arrayPointer] = size;
                    break;
                }

                case "Top": {
                    if (size == 0) {
                        System.out.println("EMPTY");
                        return;
                    }
                    System.out.println(numberArray[arrayPointer]);
                    break;
                }

                case "Size": {
                    System.out.println(size);
                }
            }
        }

        System.out.println("SAFE");
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
    