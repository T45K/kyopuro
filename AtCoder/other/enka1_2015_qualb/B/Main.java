package AtCoder.other.enka1_2015_qualb.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
文字列 比較的簡単 コーナーケースが多い
{{... int ...}} はsetになることに注意
 */
public class Main {
    private static final String DICTIONARY = "dict";
    private static final String SET = "set";

    public static void main(final String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final String s = scanner.next();
        if (s.length() == 2) {
            System.out.println(DICTIONARY);
            return;
        }

        int bracketCount = 0;
        for (int i = 1; i < s.length() - 1; i++) {
            switch (s.charAt(i)) {
                case '{':
                    bracketCount++;
                    break;
                case '}':
                    bracketCount--;
                    break;
                default:
                    if (bracketCount > 0 || s.charAt(i) != ':' && s.charAt(i) != ',') {
                        continue;
                    }
                    System.out.println(s.charAt(i) == ':' ? DICTIONARY : SET);
                    return;
            }
        }
        System.out.println(SET);
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
    }
}
    