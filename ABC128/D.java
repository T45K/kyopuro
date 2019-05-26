package ABC128;

import java.util.Arrays;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int queueLength = scanner.nextInt();
        final int operation = scanner.nextInt();

        final int[] queue = new int[queueLength];
        final int[] sort = new int[queueLength];
        int all = 0;
        for(int i = 0;i<queueLength;i++){
            final int tem = scanner.nextInt();
            queue[i] = tem;
            sort[i] = tem;
            all += tem;
        }

        Arrays.sort(sort);

        int result = Integer.MIN_VALUE;

        for(int i = 0;i<queueLength;i++){
        }
    }
}
