package ABC161.D;

import java.util.Arrays;
import java.util.Scanner;

/*
文字列操作 考察はすぐできるけど実装が重め
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int k = scanner.nextInt();

        long answer = 0;
        for (int i = 1; i <= k; i++) {
            if (answer <= 10) {
                answer++;
                continue;
            }

            final char[] array = Long.toString(answer).toCharArray();
            boolean flag = false;
            for (int j = array.length - 1; j > 0; j--) {
                final int latter = array[j] - '0';
                final int former = array[j - 1] - '0';
                if (latter == 9) {
                    continue;
                }
                if (latter <= former) {
                    array[j] += 1;
                    int prev = latter + 1;
                    for (int l = j + 1; l < array.length; l++) {
                        array[l] = (char) (Math.max(--prev, 0) + '0');
                    }
                    answer = Long.parseLong(new String(array));
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }

            if (array[0] < '9') {
                array[0]++;
                int prev = array[0] - '0';
                for (int l = 1; l < array.length; l++) {
                    array[l] = (char) (Math.max(--prev, 0) + '0');
                }
                answer = Long.parseLong(new String(array));
            } else {
                final char[] next = new char[array.length + 1];
                Arrays.fill(next, '0');
                next[0] = '1';
                answer = Long.parseLong(new String(next));
            }
        }

        System.out.println(answer);
    }
}
