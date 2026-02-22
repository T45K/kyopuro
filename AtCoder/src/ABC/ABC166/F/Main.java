package ABC.ABC166.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
数列 気づくかどうか
A+B+C=2のとき対象が1,1だと，次の行動によってどちらを足すかが変わる
解説AC
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int[] array = new int[3];
        for (int i = 0; i < 3; i++) {
            array[i] = scanner.nextInt();
        }

        if (Arrays.stream(array).sum() == 2) {
            final List<String> list = IntStream.range(0, n)
                    .mapToObj(i -> scanner.next())
                    .collect(Collectors.toList());

            final String initial = list.get(0);
            if (array[initial.charAt(0) - 'A'] == 0 && array[initial.charAt(1) - 'A'] == 0) {
                System.out.println("No");
                return;
            }

            final List<Character> answer = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                final String current = list.get(i);
                final char charFormer = current.charAt(0);
                final char charLatter = current.charAt(1);
                final int intFormer = charFormer - 'A';
                final int intLatter = charLatter - 'A';
                if (array[intFormer] * array[intLatter] == 0) {
                    if (array[intFormer] == 0) {
                        array[intFormer]++;
                        array[intLatter]--;
                        answer.add(charFormer);
                    } else {
                        array[intFormer]--;
                        array[intLatter]++;
                        answer.add(charLatter);
                    }
                    continue;
                }

                final String next = list.get(i + 1);
                if (next.equals(current)) {
                    array[intFormer]++;
                    array[intLatter]--;
                    answer.add(charFormer);
                    continue;
                }

                if (next.contains(String.valueOf(charFormer))) {
                    array[intFormer]++;
                    array[intLatter]--;
                    answer.add(charFormer);
                } else {
                    array[intFormer]--;
                    array[intLatter]++;
                    answer.add(charLatter);
                }
            }
            final String s = list.get(list.size() - 1);
            if (array[s.charAt(0) - 'A'] >= 1) {
                answer.add(s.charAt(1));
            } else {
                answer.add(s.charAt(0));
            }
            System.out.println("Yes");
            answer.forEach(System.out::println);
            return;
        }

        final List<Character> answer = IntStream.range(0, n)
                .mapToObj(i -> {
                    final String s = scanner.next();
                    final int former = s.charAt(0) - 'A';
                    final int latter = s.charAt(1) - 'A';
                    if (array[former] == 0 && array[latter] == 0) {
                        System.out.println("No");
                        System.exit(0);
                    }
                    if (array[former] < array[latter]) {
                        array[former]++;
                        array[latter]--;
                        return (char) (former + 'A');
                    } else {
                        array[former]--;
                        array[latter]++;
                        return (char) (latter + 'A');
                    }
                })
                .collect(Collectors.toList());

        System.out.println("Yes");
        answer.forEach(System.out::println);
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
