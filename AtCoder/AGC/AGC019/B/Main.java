package AtCoder.AGC.AGC019.B;

import java.util.Arrays;
import java.util.Scanner;

/*
文字列 解説AC 回文かどうかを判定することしか考えていなかった
Ai = Aj のとき，i ~ j　ひっくり返した結果と i+1 ~ j-1 をひっくり返した結果が一緒になることに気付けるかがポイント
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String a = scanner.next();
        final long[] count = new long[26];

        for (final char c : a.toCharArray()) {
            count[c - 'a']++;
        }

        final long length = a.length();
        final long base = length * (length - 1) / 2 + 1;
        final long answer = base - Arrays.stream(count)
                .map(l -> l * (l - 1) / 2)
                .sum();

        System.out.println(answer);
    }
}
