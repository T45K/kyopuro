package ABC.ABC181.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.Supplier;

/*
ある整数が8で割り切れる条件は（整数が2で割り切れる）かつ（2で割った後の整数が4で割り切れる=下2桁が4の倍数）
なので，下3桁が8で割り切れれば良い
3桁以下の8の倍数を事前に求めて起き，与えられた整数を移動させてそれらを満たせるかを確認する
 */
public class Main {
    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        final int[] givenDigits = new int[10];
        for (final char c : s.toCharArray()) {
            givenDigits[c - '0']++;
        }

        for (int i = 8; i < Math.min(1000, Math.pow(10, s.length())); i += 8) {
            final int[] targetDigits = new int[10];
            final String target;
            switch (s.length()) {
                case 1:
                    target = Integer.toString(i);
                    break;
                case 2:
                    target = String.format("%02d", i);
                    break;
                default:
                    target = String.format("%03d", i);
            }
            for (final char c : target.toCharArray()) {
                targetDigits[c - '0']++;
            }
            final Supplier<Boolean> check = () -> {
                for (int j = 0; j < targetDigits.length; j++) {
                    if (givenDigits[j] < targetDigits[j]) {
                        return false;
                    }
                }
                return true;
            };
            final boolean isSuccess = check.get();
            if (isSuccess) {
                System.out.println("Yes");
                return;
            }
        }
        System.out.println("No");
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
    }
}
