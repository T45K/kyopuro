package ABC125;

import java.util.Scanner;

public class B {
    static int[] kachi;
    static int[] cost;
    private static int result;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int counter = scanner.nextInt();

        kachi = new int[counter];
        cost = new int[counter];

        int maxKachi = 0;
        for (int i = 0; i < counter; i++) {
            kachi[i] = scanner.nextInt();
            maxKachi += kachi[i];
        }

        int maxCost = 0;
        for (int i = 0; i < counter; i++) {
            cost[i] = scanner.nextInt();
            maxCost += cost[i];
        }
        result = maxKachi - maxCost;

        select(0,counter,0);
        System.out.println(result);

    }

    public static void select(int index, final int counter, int tmp) {
        if (index == counter - 1) {
            if (tmp + kachi[index] - cost[index] > result) {
                result = tmp + kachi[index] - cost[index];
            }

            if (tmp > result) {
                result = tmp;
            }

            return;
        }

        select(index + 1, counter, tmp);
        select(index + 1, counter, tmp + kachi[index] - cost[index]);
    }
}
