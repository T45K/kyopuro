package AtCoder.AGC.AGC022.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
数学 使える数字の最大値が30_000で入力が高々20_000なので，2と3の公倍数でまかなえる
2の倍数の和が3の倍数になることと，3の倍数の和が2の倍数になるようにするだけ
 */
public class Main {
    private static final int MAX = 30_000;

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        if (n == 3) {
            System.out.println("2 5 63");
            return;
        }


        final List<Integer> answers = new ArrayList<>();
        answers.add(2);
        answers.add(4);
        answers.add(3);
        answers.add(9);

        int index = 4;
        int two = 8;
        int three = 15;
        while (index < n / 2 * 2) {
            if (two < MAX) {
                answers.add(two);
                two += 2;
                answers.add(two);
                two += 4;
                index += 2;
            } else if (three < MAX) {
                answers.add(three);
                three += 6;
                answers.add(three);
                three += 6;
                index += 2;
            } else {
                break;
            }
        }

        int six = 6;
        while (index < n) {
            answers.add(six);
            six += 6;
            index++;
        }

        final String answer = answers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
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
