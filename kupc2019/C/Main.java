package kupc2019.C;

import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int m = scanner.nextInt();
        final int k = scanner.nextInt();

        int answer = 0;
        long total = 0;
        while (total < m) {
            final long next = 2 * total + 1;
            total += next * k;
            answer++;
        }

        System.out.println(answer);
    }
}

/*
解説
最初の分銅は1gのものを用意．
つまりKgの重さまで量ることができる
次に用意する分銅は 2 * k + 1
なぜなら右側にK個の1g分銅を置くことで，
(K+1)g以降の重さを量ることができるから
以降，それの繰り返し
 */
